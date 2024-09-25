import java.util.LinkedList;
import java.util.Stack;

public class ImplicationGraph extends Graph<Integer> {

    public ImplicationGraph(int size) {
        super(size);
    }

    public ImplicationGraph mirror () throws Exception {
        ImplicationGraph mirror = new ImplicationGraph(super.getCardinal());

        for (int index = 0; index < super.getCardinal(); index++){
            addReversedEdges(mirror, index);
        }
        return mirror;
    }

    private void addReversedEdges(ImplicationGraph mirror, int index) throws Exception {
        for(Edge edge : getEdges(index)) {
            mirror.addEdge(edge.destination, edge.source, edge.label);
        }
    }

    /**
     * Performs a depth-first search (DFS) from an unvisited vertex of the graph. When it finishes the vertex exploration,
     * means all its neighbors are visited, the vertex is pushed onto the processingOrderStack.
     * @return the processing order stack.
     */
    public Stack<Integer> getVertexProcessingOrder(){
        Stack<Integer> processingOrderStack = new Stack<>();
        boolean[] visitedVertexes = new boolean[getCardinal()];
        for (int index = 0; index < getCardinal(); index++) {
            if (!visitedVertexes[index]) {
                dfsFirstPass(getEdges(index),index, visitedVertexes, processingOrderStack);
            }
        }
        return processingOrderStack;
    }

    private void dfsFirstPass(LinkedList<Edge> edges, int index,boolean[] visitedVertexes, Stack<Integer> processingOrderStack) {
        visitedVertexes[index] = true;
        for (Edge edge : edges) {
            if (!visitedVertexes[edge.destination]) {
                dfsFirstPass(edges, edge.destination, visitedVertexes, processingOrderStack);
            }
        }
        processingOrderStack.push(index); // Empiler le nœud après avoir visité tous ses voisins
    }


}
