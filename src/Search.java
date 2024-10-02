import java.util.LinkedList;

public class Search {

    private final Graph<Integer> graph;

    private final LinkedList<Graph<Integer>.Edge> predecessor;

    private final LinkedList<Integer> visitedIndex;
    private LinkedList<Integer> enteringTime;
    private LinkedList<Integer> iterationTime;
    private LinkedList<Integer> exitTime;
    private int time;
    private int iteration;

    public Search(Graph<Integer> graph){
        this.graph = graph;
        this.predecessor = new LinkedList<>();
        this.visitedIndex = new LinkedList<>();
        this.enteringTime = new LinkedList<>();
        this.iterationTime = new LinkedList<>();
        this.exitTime = new LinkedList<>();
        this.time = 0;
        this.iteration = 0;
    }

    /**
     * Performs an iterative depth-first search (DFS) on the graph, marking visited indexes
     * and recording their predecessors, and returns the predecessor list.
     * @return the predecessor list.
     */
    public LinkedList<Graph<Integer>.Edge> iterativeDFS(LinkedList<Integer> indexes){
        for(int index : indexes){

            if(!visitedIndex.contains(index)){
                enteringTime.set(index, time++);
                iterationTime.set(index, iteration++);
                visitedIndex.add(index);
                predecessor.set(index,null);

                for(Graph<Integer>.Edge edge : graph.getEdges(index)){
                    explore(edge);
                }
                exitTime.set(index, time++);
            }
        }
        return predecessor;
    }

    /**
     * Recursively explores the graph from the given edge, marking visited indexes and
     * recording their predecessors.
     * @param edge the edge from which begin the exploration.
     */
    public void explore(Graph<Integer>.Edge edge){
        int destinationIndex = edge.destination;

        if(!visitedIndex.contains(destinationIndex)){
            enteringTime.set(destinationIndex, time++);
            iterationTime.set(destinationIndex, iteration);
            visitedIndex.add(destinationIndex);
            predecessor.set(destinationIndex, edge);

            for( Graph<Integer>.Edge destinationEdge : graph.getEdges(destinationIndex)){
                explore(destinationEdge);
            }
            exitTime.set(destinationIndex, time++);
        }
    }

    public LinkedList<Integer> getExitTime(){
        return exitTime;
    }

}
