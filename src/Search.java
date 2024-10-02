import java.util.LinkedList;

public class Search {
    private final Graph<Integer> graph;

    private final LinkedList<Graph<Integer>.Edge> predecessor;

    private final LinkedList<Integer> visitedIndex;

    public Search(Graph<Integer> graph){
        this.graph = graph;
        this.predecessor = new LinkedList<>();
        this.visitedIndex = new LinkedList<>();
    }

    /**
     * Recursively explores the graph from the given edge, marking visited indexes and
     * recording their predecessors.
     * @param edge the edge from which begin the exploration.
     */
    public void explore(Graph<Integer>.Edge edge){
        int destinationIndex = edge.destination;
        if(!visitedIndex.contains(destinationIndex)){
            visitedIndex.add(destinationIndex);
            predecessor.set(destinationIndex, edge);
        }

        for( Graph<Integer>.Edge destinationEdge : graph.getIncidents(destinationIndex)){
            explore(destinationEdge);
        }
    }

    /**
     * Performs an iterative depth-first search (DFS) on the graph, marking visited indexes
     * and recording their predecessors, and returns the predecessor list.
     * @return the predecessor list.
     */
    public LinkedList<Graph<Integer>.Edge> iterativeDFS(){
        for(int index : graph.getIndexes()){
            if(!visitedIndex.contains(index)){
                visitedIndex.add(index);
                predecessor.set(index,null);
            }
            for(Graph<Integer>.Edge edge : graph.getIncidents(index)){
                explore(edge);
            }
        }
        return predecessor;
    }
}
