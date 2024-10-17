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
        this.cardinal = size;
        this.incidence = new ArrayList<>(size + 1);

        for (int i = 0; i < getCardinal(); i++) {
            getIncidence().add(i, new LinkedList<>());
        }
    }

    @Override
    public String toString() {
        String result = "";
        result = result.concat("\nVERTICES NUMBER : " + getCardinal() + "\n\n");
        result = result.concat("Vertices : ");

        for (int i = 0; i < getCardinal(); i++) {
            result = result.concat(i + " ");
        }

        result = result.concat("\n\nEDGES : \n");

        for (int i = 0; i < getCardinal(); i++) {
            for (Edge<Label> edge : getEdges(i)) {
                result = result.concat(edge.getSource() + " -> " + edge.getDestination() + ", label : "
                        + edge.getLabel().toString() + "\n");
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

        if (Math.max(source,destination) >= this.getCardinal()) {
	        throw new Exception("Vertices value to high for the graph size.");
	    }
        getEdges(source).addLast(new Edge<>(source, destination, label));
    }

    /**
     * Creates and returns a new implication graph that is the mirror image of the current graph.
     * The mirror image is a graph with all the edges reversed.
     *
     * @return A new {@code ImplicationGraph} object that represents the mirrored graph.
     */
    public Graph<Label> mirror() {

        try {
            Graph<Label> mirror = new Graph<>(getCardinal());

            for (int index = 0; index < getCardinal(); index++) {
                addReversedEdges(mirror, index);
            }
            return mirror;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds reversed edges from the node at the given index to the mirror graph.
     *
     * @param mirror the graph to which reversed edges are added
     * @param index the node index whose edges are reversed
     * @throws Exception if an error occurs during edge processing
     */
    private void addReversedEdges(Graph<Label> mirror, int index) throws Exception {

        for (Edge<Label> edge : getEdges(index)) {
            mirror.addEdge(edge.getDestination(), edge.getSource(), edge.getLabel());
        }
    }

    /**
     * Returns a list of indexes from 0 to the cardinality of the graph.
     *
     * @return an {@link ArrayList} of integer indexes
     */
    public ArrayList<Integer> getIndexes() {

        ArrayList<Integer> indexes = new ArrayList<>();

        for (int index = 0; index < getCardinal(); index++){
            indexes.add(index);
        }
        return indexes;
    }

    /**
     * Retrieves the edges associated with the specified node index.
     *
     * @param nodeIndex the index of the node
     * @return a {@link LinkedList} of edges for the given node
     */
    protected LinkedList<Edge<Label>> getEdges(int nodeIndex) {
        return getIncidence().get(nodeIndex);
    }

    protected int getCardinal() {
        return cardinal;
    }

    private ArrayList<LinkedList<Edge<Label>>> getIncidence() {
        return incidence;
    }
}
