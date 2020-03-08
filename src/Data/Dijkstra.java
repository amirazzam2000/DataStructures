package Data;

public class Dijkstra {
    private AdjacencyList graph;
    private int[] walk;
    private double[] distance;
    private double[] notFindingAnEnemy;
    private char[] visited;
    private int[] finalWalk;
    private int counter;

    public Dijkstra(AdjacencyList graph) {
        this.graph = graph;
        walk = new int[graph.getSize()];
        finalWalk = new int[graph.getSize()];
        distance = new double[graph.getSize()];
        visited = new char[graph.getSize()];
        notFindingAnEnemy = new double[graph.getSize()];

        for (int i = 0; i <walk.length; i++) {
            walk[i] = Integer.MAX_VALUE;
            finalWalk[i] = Integer.MAX_VALUE;
            distance[i] = Integer.MAX_VALUE;
            visited[i] = 'f';
            notFindingAnEnemy[i] = Float.MAX_VALUE;
        }

        counter = 0;

    }

    public void shortestPath(int start, int end){
        int current = start;
        notFindingAnEnemy[current] = 1;
        distance[current] = 0;
        walk[current] = -1; // to know that this is the first room
        while( thereAreMoreNodesToVisit(walk) && visited[end] == 'f'){
            for (int adj: graph.getAdjacentNodes(current)) {
                if(visited[adj] == 'f'){
                    double aux1 =
                            notFindingAnEnemy[current] * graph.getConnection(current
                            ,adj)/100;
                    double aux = 1 - aux1;

                    if(distance[adj] > aux){
                        distance[adj] = aux;
                        notFindingAnEnemy[adj] = aux1;
                        walk[adj] = current;
                    }
                }
            }
            visited[current] = 't';
            current = getMinimum(distance , visited);
        }
        int f =end;
        getWalk(walk, f);

        int count = 1;
        for (int step : finalWalk) {
            if(step != Integer.MAX_VALUE) {
                System.out.println(step + " , " + distance[step] );
                count++;
            }
        }
        System.out.println(end+ " , "+ distance[end]);
    }

    private void getWalk(int[] walk, int end) {
        if(walk[end] != -1){
            end = walk[end];
            getWalk(walk, end );
        }
        // add the node
        finalWalk[counter++] = end;
    }

    private int getMinimum(double[] distance, char[] visited) {
        int pos = -1;
        double minDis = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length ; i++) {
            if(visited[i] == 'f'){
                if(distance[i] < minDis){
                    minDis = distance[i];
                    pos = i;
                }
            }
        }
        return pos;
    }

    private boolean thereAreMoreNodesToVisit(int[] walk) {
        for (int i : walk) {
            if(i == Integer.MAX_VALUE)
                return true;
        }
        return false;
    }

}
