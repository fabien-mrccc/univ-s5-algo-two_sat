import java.lang.classfile.Label;
import java.util.LinkedList;

public class Search {
    private final Graph<Label> graph;

    private LinkedList<Graph<Label>.Edge> predecessor;

    private LinkedList<Integer> visitedIndex;

    public Search(Graph<Label> graph){
        this.graph = graph;
        this.predecessor = new LinkedList<>();
        this.visitedIndex = new LinkedList<>();
    }

    public void explore(Graph<Label>.Edge edge){
        int destinationIndex = edge.destination;
        if(!visitedIndex.contains(destinationIndex)){
            visitedIndex.add(destinationIndex);
            predecessor.set(destinationIndex, edge);
        }

        for( Graph<Label>.Edge destinationEdge : graph.getIncidents(destinationIndex)){
            explore(destinationEdge);
        }
    }

    public LinkedList<Graph<Label>.Edge> iterativeDFS(){
        for(int index : graph.getIndexes()){
            if(!visitedIndex.contains(index)){
                visitedIndex.add(index);
                predecessor.set(index,null);
            }
            for(Graph<Label>.Edge edge : graph.getIncidents(index)){
                explore(edge);
            }
        }
        return predecessor;
    }
}
