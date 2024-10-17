import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label>  {

    private final int cardinal;
    private final ArrayList<LinkedList<Edge<Label>>> incidence;

    /**
     * Initializes a graph with the specified number of nodes.
     *
     * @param size the number of nodes in the graph
     */
    public Graph(int size) {
        cardinal = size;
        incidence = new ArrayList<>(size + 1);
        for (int i = 0; i<cardinal; i++) {
            incidence.add(i, new LinkedList<>());
        }
    }

    @Override
    public String toString() {
        String result = "";
        result = result.concat("\nVERTICES NUMBER : "+ getCardinal() + "\n\n");
        result = result.concat("Vertices : ");

        for (int i = 0; i<getCardinal(); i++) {
            result = result.concat(i + " ");
        }

        result = result.concat("\n\nEdges : \n");

        for (int i = 0; i<getCardinal(); i++) {
            for (Edge<Label> e : getEdges(i)) {
                result = result.concat(e.getSource() + " -> " + e.getDestination() + ", label : "
                        + e.getLabel().toString() + "\n");
            }
        }
        return result;
    }

    /**
     * Adds an edge from the source node to the destination node with a given label.
     *
     * @param source the source node index
     * @param destination the destination node index
     * @param label the label of the edge
     * @throws Exception if the source or destination exceeds the graph's size
     */
    public void addEdge(int source, int destination, Label label) throws Exception {
	    if (Math.max(source,destination) >= this.cardinal) {
	        throw new Exception("Vertices value to high for the graph size.");
	    }
        incidence.get(source).addLast(new Edge<>(source,destination,label));
    }

    protected LinkedList<Edge<Label>> getEdges(int index) {
        return incidence.get(index);
    }

    protected int getCardinal() {
        return cardinal;
    }

    public ArrayList<Integer> getIndexes(){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int index = 0; index < cardinal; index++){
            indexes.add(index);
        }
        return indexes;
    }
}
