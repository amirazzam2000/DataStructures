import Data.Connection;
import Data.ConnectionManager;
import Data.Room;
import Data.RoomManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                default:
                    skip = false ;
                    System.out.println("pick S, L, or M only !");
                    break;
            }
        }while(!skip);

        RoomManager rooms = new RoomManager(rooms_file);
        ConnectionManager connections =
                new ConnectionManager(connections_file);



    }
}