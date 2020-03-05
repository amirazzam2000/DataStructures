package Data;

import java.util.BitSet;
import java.util.HashMap;

public class AdjacencyMatrix {
    private int[][] matrix;
    private HashMap<Integer,HashMap<Integer, Integer>> listImplementation;

    /*public AdjacencyMatrix(ConnectionManager connections, RoomManager rooms){
       matrix = new int [rooms.getRooms().length][rooms.getRooms().length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }

        for (Connection c: connections.getConnections()) {
            for (int i = 0; i < c.getRoom_connected().length; i++) {
                for (int j = 0; j < c.getRoom_connected().length; j++) {
                    if(j != i){
                       *//* int aux =
                                (int) -(Math.log((100.0 - c.getEnemy_probability())/100) * 100);*//*
                        if(matrix[c.getRoom_connected()[i]][c.getRoom_connected()[j]] > c.getEnemy_probability()){
                            matrix[c.getRoom_connected()[i]][c.getRoom_connected()[j]] = c.getEnemy_probability();
                        }
                    }
                }
            }
        }
    }*/

    public AdjacencyMatrix(ConnectionManager connections,
                           RoomManager rooms){
        listImplementation = new HashMap<>();

        for (Connection c: connections.getConnections()) {
            for (int i = 0; i < c.getRoom_connected().length; i++) {
                for (int j = 0; j < c.getRoom_connected().length; j++) {
                    if(j != i){
                        if(listImplementation.get(c.getRoom_connected()[i]) == null){
                            listImplementation.put(c.getRoom_connected()[i],
                                    new HashMap<>());
                            listImplementation.get(c.getRoom_connected()[i]).put(c.getRoom_connected()[j], 100 - c.getEnemy_probability());
                        }
                        else{
                            Integer aux =
                                    listImplementation.get(c.getRoom_connected()[i]).get(c.getRoom_connected()[j]);
                            if (aux == null ){
                                listImplementation.get(c.getRoom_connected()[i]).put(c.getRoom_connected()[j],100 - c.getEnemy_probability());
                            }
                            else if(aux > (100 - c.getEnemy_probability())){
                                listImplementation.get(c.getRoom_connected()[i]).put(c.getRoom_connected()[j],100- c.getEnemy_probability());
                            }
                        }

                        /*StringBuilder values = new StringBuilder();
                        if(listImplementation.get(""+c.getRoom_connected()[i]) == null){
                            values = new StringBuilder("[");
                        }
                        if(listImplementation.get(""+c.getRoom_connected()[i]) != null){
                            values = new StringBuilder(listImplementation.get("" + c.getRoom_connected()[i]));
                        }
                        values.append(c.getRoom_connected()[j]).append(":").append(c.getEnemy_probability()).append(System.);
                        listImplementation.put(""+c.getRoom_connected()[i],
                                values.toString());*/

                    }
                }
            }
        }
    }


    //TODO : change in case of change
    public int getConnection(int x ,int y){
        //return matrix[x][y];
        return listImplementation.get(x).get(y);
    }

    //TODO : change in case of change
    public int getSize(){
        //return matrix.length;
        return listImplementation.size();
    }

    //TODO : change in case of change
    public int[] getAdjacentNodes(int current) {
        //int[] adjacentNodes = new int[getSize()];
        int[] adjacentNodes =
                new int[listImplementation.get(current).size()];
        int i = 0;

        /*for (int j = 0; j < getSize(); j++) {
            if (matrix[current][j] != Integer.MAX_VALUE) {
                adjacentNodes[i++] = j;
            }
        }
        int[] finalAdj = new int[i];
        for (int j = 0; j < i; j++) {
            finalAdj[j] = adjacentNodes[j];
        }*/

        for (Integer key: listImplementation.get(current).keySet()) {
            adjacentNodes[i++] = key;
        }



        return adjacentNodes;
    }
}
