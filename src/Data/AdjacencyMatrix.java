package Data;

public class AdjacencyMatrix {
    private int[][] matrix;
    public AdjacencyMatrix(ConnectionManager connections, RoomManager rooms){
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
                        if(matrix[c.getRoom_connected()[i]][c.getRoom_connected()[j]] > c.getEnemy_probability()){
                            matrix[c.getRoom_connected()[i]][c.getRoom_connected()[j]] = c.getEnemy_probability();
                        }
                    }
                }
            }
        }

    }

    public void printMatrix (){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                if(matrix[i][j] == Integer.MAX_VALUE)
                    System.out.print("inf, ");
                else
                    System.out.print(matrix[i][j]+ ", ");
            }
            System.out.println();
        }
    }
    public int[][] getMatrix() {
        return matrix;
    }
}
