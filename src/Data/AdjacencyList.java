package Data;

import java.util.BitSet;
import java.util.HashMap;

public class AdjacencyList {

    // this attribute will represent the Adjacency matrix as each key of the
    // first hash map will represent a room and each one of these room will
    // store a hash map of it's adjacent room with the probability of getting
    // there
    //private HashMap<Integer,HashMap<Integer, Integer>> listImplementation;

    private RoomsList[] listImplementation;
    public AdjacencyList(ConnectionManager connections,
                         RoomManager rooms){
        listImplementation = new RoomsList[rooms.getRooms().length];

        for (Connection c: connections.getConnections()) {         // scanning all the connections in the connections json file

            for (int i = 0; i < c.getRoom_connected().length; i++) {
                for (int j = 0; j < c.getRoom_connected().length; j++) {
                    if(j != i){

                        // if the room is not in the map then add it to the
                        // map with its probability
                        if(listImplementation[c.getRoom_connected()[i]] == null){
                            listImplementation[c.getRoom_connected()[i]] =
                                    new RoomsList();
                            listImplementation[c.getRoom_connected()[i]].add(
                                    c.getRoom_connected()[j], 100 -
                                            c.getEnemy_probability());
                        }
                        else{
                            // if the room exists in the map compare its
                            // probability with the current probability if
                            // it's less then replace the current probability
                            // with that probability
                            Integer aux =
                                    listImplementation[
                                            c.getRoom_connected()[i]].get(
                                                    c.getRoom_connected()[j]);
                            if (aux == null ){
                                listImplementation[c.getRoom_connected()[i]].add(
                                        c.getRoom_connected()[j],100 -
                                                c.getEnemy_probability());
                            }
                            else if(aux > (100 - c.getEnemy_probability())){
                                listImplementation[c.getRoom_connected()[i]].add(
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
        return listImplementation[x].get(y);
    }

    public int getSize(){
        return listImplementation.length;
    }

    public int[] getAdjacentNodes(int current) {
        int[] adjacentNodes =
                new int[listImplementation[current].size()];
        int i = 0;
        for (Integer key: listImplementation[current].keySet()) {
            adjacentNodes[i++] = key;
        }
        return adjacentNodes;
    }
}
