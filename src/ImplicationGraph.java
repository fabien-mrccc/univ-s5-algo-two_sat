import java.util.LinkedList;
import java.util.Stack;

public class ImplicationGraph extends Graph<Integer> {

    public ImplicationGraph(int size) {
        super(size);
    }

    public void addEdgesFromClause(String[] clauseLine) throws Exception {
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[0])),
                getLiteralIndex(Integer.parseInt(clauseLine[1])),Integer.parseInt(clauseLine[2]));
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[1])),
                getLiteralIndex(Integer.parseInt(clauseLine[0])),Integer.parseInt(clauseLine[2]));
    }

    /**
     * Return the index of the literal we gave in parameters.
     * @param literal the literal we want the index of.
     * @return the index of the literal
     */
    private int getLiteralIndex(int literal){
        if(literal < 0){
            return literal + getCardinal()/2;
        }
        return literal + getCardinal()/2 -1;
    }

    protected int getLiteral(int index){
        if(index < getCardinal()/2){
            return index - getCardinal()/2;
        }
        return index - getCardinal()/2 +1;
    }

    public String toString() {
        String result = "";
        result = result.concat(STR."Nombre sommets : \{getCardinal()}\n");
        result = result.concat("Litteral/Index : \n");

        for (int i = 0; i<getCardinal();i++) {
            result = result.concat(STR."\{getLiteral(i)}/\{i} ");
        }

        result = result.concat("\nArcs : \n");

        for (int i = 0; i<getCardinal();i++) {
            for (Edge e : getEdges(i)) {
                result = result.concat(STR."\{getLiteral(e.source)} -> \{getLiteral(e.destination)}, étiquette : \{e.label.toString()}\n");
            }
        }
        return result;
    }

    /**
     * Performs a depth-first search (DFS) from an unvisited vertex of the graph. When it finishes the vertex exploration,
     * means all its neighbors are visited, the vertex is pushed onto the processingOrderStack.
     * @return the processing order stack.
     */
    //TODO: change stack par queue et mettre le code dans une autre classe (eventuellement kosaraju)
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
