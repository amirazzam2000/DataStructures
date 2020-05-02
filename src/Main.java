import Data.*;
import MapObjects.*;

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
            System.out.println("4. exit.");
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
                    break;
                case 4:
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
            System.out.println("2. see grounding objects and pick them up. " +
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
}
