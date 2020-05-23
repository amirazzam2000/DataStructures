import Data.*;
import Hash.HashItem;
import Hash.HashItemManager;
import Hash.HashMapFriends;
import Hash.List;
import MapObjects.*;
import Shop.BTree;
import Shop.ShopObject;
import Shop.ShopObjectManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean skip = false, flag = true;
        int option = 0;

        do{
            System.out.println("1. dijkstra menu");
            System.out.println("2. RTree menu");
            System.out.println("3. BTree menu");
            System.out.println("4. Hash Players");
            System.out.println("5. exit.");
            do {
                flag = false;
                try {
                    option = sc.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("you can only select one of the " +
                            "options above.");
                    flag = true;
                    sc = new Scanner(System.in);
                }
            }while(flag);

            switch (option){
                case 1:
                    dijkstraMenu();
                    break;
                case 2:
                    rTreeMenu();
                    break;
                case 3:
                    bTreeMenu();
                    break;
                case 4:
                    hashMenu();
                    break;
                case 5:
                    skip = true;
                    break;
            }

        }while (!skip);
    }

    static void dijkstraMenu(){
        String rooms_file = null;
        String connections_file = null;
        Scanner sc = new Scanner(System.in);
        boolean skip ;
        System.out.print("select file size: ");
        do {
            String input = sc.next();
            skip = true;
            switch (input) {
                case "S":
                case "s":
                    rooms_file = "data/RoomS.json";
                    connections_file = "data/ConnectionS.json";
                    System.out.println("loading the small files...");
                    break;
                case "L":
                case "l":
                    rooms_file = "data/RoomL.json";
                    connections_file = "data/ConnectionL.json";
                    System.out.println("loading the large files...");
                    break;
                case "M":
                case "m":
                    rooms_file = "data/RoomM.json";
                    connections_file = "data/ConnectionM.json";
                    System.out.println("loading the medium files...");
                    break;
                case "T":
                case "t":
                    rooms_file = "data/RoomT.json";
                    connections_file = "data/ConnectionT.json";
                    System.out.println("loading the test files...");
                    break;
                default:
                    skip = false ;
                    System.out.println("pick S, L, or M only !");
                    break;
            }
        }while(!skip);

        RoomManager rooms = new RoomManager(rooms_file);
        ConnectionManager connections =
                new ConnectionManager(connections_file);

        AdjacencyList graph = new AdjacencyList(connections, rooms);
        Dijkstra dijkstra = new Dijkstra(graph);

        int start, end;
        System.out.println("enter your starting point (only numbers): ");
        start = sc.nextInt();

        System.out.println("enter your ending point (only numbers): ");
        end = sc.nextInt();

        dijkstra.shortestPath(start, end);
    }

    static void rTreeMenu(){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        boolean flag = false;
        boolean skip = false;
        long t1 , t2;

        System.out.println("loading data set...");
        t1 = System.nanoTime();
        ObjectManger objects = new ObjectManger();
        objects.addCenters();
        RTree rTree = new RTree(3);
        for (MapObject object: objects.getObjects()) {
            rTree.addObject(object, rTree.getRoot());
        }
        t2 = System.nanoTime();
        System.out.println("time taken to load the data set : " + (t2 -t1)/Math.pow(10,9) + " sec" );

        do{
            System.out.println("1. visualise data set.");
            System.out.println("2. see surrounding objects and pick them up. " +
                    "(the element will be delete from the tree)");
            System.out.println("3. go back to menu.");
            System.out.println("select option : ");
            do {
                flag = false;
                try {
                    option = sc.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("you can only select one of the " +
                            "options above.");
                    flag = true;
                    sc = new Scanner(System.in);
                }
            }while(flag);
            switch (option){
                case 1:
                    rTree.showGraph();
                    break;
                case 2:
                    System.out.println("please enter your current coordinates" +
                            " on the map: ");
                    Point p = new Point(0,0);
                    System.out.print("X coordinate(integers only): ");
                    p.setX(sc.nextInt());
                    System.out.print("Y coordinate(integers only): ");
                    p.setY(sc.nextInt());

                    Queue<MapObject> objectQueue = rTree.findObject(p);
                    if (!objectQueue.isEmpty()){
                        int id;
                        boolean exit = false;
                        System.out.println("which element would you like to pic up " +
                                "(please select the id of the element or write a " +
                                "character to exit )");
                        do {
                            try {
                                id = sc.nextInt();

                                MapObject aux =
                                        objectQueue.popObjectByValue(objects.getObjectsById(id));
                                if (aux != null) {
                                    rTree.deleteElement(aux);
                                    System.out.println("deleted successfully!");
                                }
                                else
                                    System.out.println("you are only allowed to pick " +
                                            "element from the list above!");
                                if(objectQueue.isEmpty()) {
                                    exit = true;
                                    System.out.println(System.lineSeparator() +
                                            "you don't have any more objects around you ");

                                }
                            }catch (InputMismatchException e){
                                exit = true;
                                sc = new Scanner(System.in);
                            }
                        }while(!exit);
                    }
                    else {
                        System.out.println("Sorry No objects where found!");
                    }
                    break;
                case 3:
                    skip = true;
                    break;
            }

        }while(!skip);
    }

    static void bTreeMenu(){
        boolean skip = false;
        boolean flag = false;
        int option = 0;
        int i =0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Loading dataset into tree...");
        String shopfile = "data/dataset_ObjectS.json";

        ShopObjectManager sm = new ShopObjectManager(shopfile);
        ShopObject[] items = sm.getShopObjects();
        BTree tree = new BTree();

        //add all the items to the tree
        for (int j = 0; j < items.length; j++) {
            tree.addItem(items[j]);
        }

        System.out.println("Data loaded into the tree successfully");

        do{
            System.out.println("1. Visualize tree ");
            System.out.println("2. See if an object is in the shop");
            System.out.println("3. Remove an object in the shop");
            System.out.println("4. Go back to menu.");

            System.out.println("Select option : ");
            do {
                flag = false;
                try {
                    option = sc.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("you can only select one of the " +
                            "options above.");
                    flag = true;
                    sc = new Scanner(System.in);
                }
            }while(flag);


            switch (option){
                case 1:
                    BTree.drawTree(BTree.root, 0);
                    break;

                case 2:
                    System.out.println("Would you like to find the item by price or by name? " +
                            "(0 for price, 1 for name)");

                    int optionFind = -1;

                    do {
                        flag = false;
                        try {
                            optionFind = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Please insert an int");
                            flag = true;
                            sc = new Scanner(System.in);
                        }
                    }while(flag);

                    switch (optionFind){
                        case 0:

                            System.out.println("Insert the price of the object to find");

                            int findPrice = 0;

                            do {
                                flag = false;
                                try {
                                    findPrice = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please insert an int");
                                    flag = true;
                                    sc = new Scanner(System.in);
                                }
                            }while(flag);

                            //call function with the inserted value

                            i=0;

                            while(i < items.length){
                                if(items[i].getPrice() == findPrice){
                                    System.out.println("The item found has name: " + tree.search(items[i]).getName());
                                    break;
                                }
                                i++;
                            }

                            if(i == items.length){
                                System.out.println("Item was not found in the shop :(");
                            }
                            
                            break;

                        case 1:
                            System.out.println("Insert the name of the object to find");

                            String findName = null;

                            do {
                                flag = false;
                                try {
                                    sc.nextLine();
                                    findName = sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please insert a String");
                                    flag = true;
                                    sc = new Scanner(System.in);
                                }
                            }while(flag);

                            //call function with the inserted value

                            i=0;

                            while(i < items.length){
                                if(items[i].getName().equals(findName)){
                                    System.out.println("The item found has price: " + tree.search(items[i]).getPrice());
                                    tree.search(items[i]);
                                    break;
                                }
                                i++;
                            }

                            if(i == items.length){
                                System.out.println("Item was not found in the shop :(");
                            }

                            break;

                        default:
                            System.out.println("0 or 1 only!");
                            break;
                    }

                    break;

                case 3:
                    System.out.println("Would you like to delete the item by price or by name? " +
                            "(0 for price, 1 for name)");

                    int optionDelete = -1;

                    do {
                        try {
                            optionDelete = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Please insert an int");
                            flag = true;
                            sc = new Scanner(System.in);
                        }
                    }while(flag);

                    switch (optionDelete){
                        case 0:

                            System.out.println("Insert the price of the object to delete");

                            int deletePrice = 0;

                            do {
                                flag = false;
                                try {
                                    deletePrice = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please insert an int");
                                    flag = true;
                                    sc = new Scanner(System.in);
                                }
                            }while(flag);

                            //call function with the inserted value
                            i=0;

                            while(i < items.length){
                                if(items[i].getPrice() == deletePrice){
                                    tree.delete(items[i]);
                                    System.out.println("Item deleted");
                                    break;
                                }
                                i++;
                            }

                            if(i == items.length){
                                System.out.println("Item was not found in the shop :(");
                            }

                            break;

                        case 1:
                            System.out.println("Insert the name of the object to delete");

                            String deleteName = null;

                            do {
                                flag = false;
                                try {
                                    sc.nextLine();
                                    deleteName = sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please insert a String");
                                    flag = true;
                                    sc = new Scanner(System.in);
                                }
                            }while(flag);

                            //call function with the inserted value
                            i=0;

                            while(i < items.length){
                                if(items[i].getName().equals(deleteName)){
                                    tree.delete(items[i]);
                                    System.out.println("Item deleted");
                                    break;
                                }
                                i++;
                            }

                            if(i == items.length){
                                System.out.println("Item was not found in the shop :(");
                            }

                            break;

                        default:
                            System.out.println("0 or 1 only!");
                            break;
                    }
                    break;

                case 4:
                    skip = true;
                    break;
            }

        }while(!skip);
    }
    
    
    static void hashMenu(){
        HashItemManager hashItemManager = null;
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        boolean skip = false;
        String name;
        long t1 , t2;
        int option = 0;
        System.out.print("select file size: ");
        do {
            String input = sc.next();
            skip = true;
            switch (input) {
                case "S":
                case "s":
                    hashItemManager = new HashItemManager("S");
                    System.out.println("loading the small files...");
                    break;
                case "L":
                case "l":
                    hashItemManager = new HashItemManager("L");
                    System.out.println("loading the large files...");
                    break;
                case "M":
                case "m":
                    hashItemManager = new HashItemManager("M");
                    System.out.println("loading the medium files...");
                    break;

                default:
                    skip = false ;
                    System.out.println("pick S, L, or M only !");
                    break;
            }
        }while(!skip);

        skip = false;
        t1 = System.nanoTime();
        HashMapFriends hashMapFriends = new HashMapFriends(hashItemManager);
        t2 = System.nanoTime();
        System.out.println("Time taken to load the file: " + (t2 -t1)/Math.pow(10,9) + " seconds" + System.lineSeparator());



        do{
            System.out.println("1. Search for a Player");
            System.out.println("2. Delete a Player");
            System.out.println("3. Print HashMap");
            System.out.println("4. Back");
            System.out.println("select option : ");

            do {
                flag = false;
                try {
                    option = sc.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("you can only select one of the " +
                            "options above.");
                    flag = true;
                    sc = new Scanner(System.in);
                }
            }while(flag);


            switch (option){
                case 1:
                    System.out.println("enter username : ");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    t1 = System.nanoTime();
                    HashItem item = hashMapFriends.find(name);
                    t2 = System.nanoTime();
                    if (item == null){
                        System.out.println("user not found!");
                    }
                    else {
                        System.out.println(item.getName()+ " , " +
                                item.getKda()+ " , " +
                                item.getGames());
                    }
                    System.out.println("Time taken to search: " + (t2 -t1)/Math.pow(10,9) + " seconds" + System.lineSeparator());

                    break;
                case 2:
                    System.out.println("enter username : ");
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    t1 = System.nanoTime();
                    boolean deleted = hashMapFriends.delete(name);
                    t2 = System.nanoTime();
                    if (!deleted){
                        System.out.println("user not found!");
                    }
                    else {
                        System.out.println("user deleted successfuly!");
                    }
                    System.out.println("Time taken to search: " + (t2 -t1)/Math.pow(10,9) + " seconds" + System.lineSeparator());

                    break;
                case 3:
                    hashMapFriends.print();
                    System.out.println();
                    break;
                case 4:
                    skip = true;
                    break;
            }

        }while(!skip);

    }

}
