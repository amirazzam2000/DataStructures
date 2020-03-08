package Data;

import java.util.BitSet;
import java.util.HashMap;

public class AdjacencyList {

    // this attribute will represent the Adjacency matrix as each key of the
    // first hash map will represent a room and each one of these room will
    // store a hash map of it's adjacent room with the probability of getting
    // there
    private HashMap<Integer,HashMap<Integer, Integer>> listImplementation;

    public AdjacencyList(ConnectionManager connections,
                         RoomManager rooms){
        listImplementation = new HashMap<>();

        for (Connection c: connections.getConnections()) {         // scanning all the connections in the connections json file

            for (int i = 0; i < c.getRoom_connected().length; i++) {
                for (int j = 0; j < c.getRoom_connected().length; j++) {
                    if(j != i){

                        // if the room is not in the map then add it to the
                        // map with its probability
                        if(listImplementation.get(c.getRoom_connected()[i]) == null){
                            listImplementation.put(c.getRoom_connected()[i],
                                    new HashMap<>());
                            listImplementation.get(c.getRoom_connected()[i]).put(
                                    c.getRoom_connected()[j], 100 -
                                            c.getEnemy_probability());
                        }
                        else{
                            // if the room exists in the map compare its
                            // probability with the current probability if
                            // it's less then replace the current probability
                            // with that probability
                            Integer aux =
                                    listImplementation.get(
                                            c.getRoom_connected()[i]).get(
                                                    c.getRoom_connected()[j]);
                            if (aux == null ){
                                listImplementation.get(c.getRoom_connected()[i]).put(
                                        c.getRoom_connected()[j],100 -
                                                c.getEnemy_probability());
                            }
                            else if(aux > (100 - c.getEnemy_probability())){
                                listImplementation.get(c.getRoom_connected()[i]).put(
                                        c.getRoom_connected()[j],100 -
                                                c.getEnemy_probability());
                            }
                        }
                    }
                }
            }
        }
    }


    public int getConnection(int x ,int y){
        return listImplementation.get(x).get(y);
    }

    public int getSize(){
        return listImplementation.size();
    }

    public int[] getAdjacentNodes(int current) {
        int[] adjacentNodes =
                new int[listImplementation.get(current).size()];
        int i = 0;
        for (Integer key: listImplementation.get(current).keySet()) {
            adjacentNodes[i++] = key;
        }
        return adjacentNodes;
    }
}
