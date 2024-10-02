import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label>  {

    protected class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private final int cardinal;
    private final ArrayList<LinkedList<Edge>> incidence;

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
        result = result.concat("\nNombre sommets : "+ getCardinal() + "\n\n");
        result = result.concat("Sommets : ");

        for (int i = 0; i<getCardinal(); i++) {
            result = result.concat(i + " ");
        }

        result = result.concat("\n\nArcs : \n");

        for (int i = 0; i<getCardinal(); i++) {
            for (Edge e : getEdges(i)) {
                result = result.concat(e.source + " -> " + e.destination + ", étiquette : "
                        + e.label.toString() + "\n");
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
	        throw new Exception("Sommets trop gros pour la taille du graphe");
	    }
        incidence.get(source).addLast(new Edge(source,destination,label));
    }

    /**
     * Creates and returns a new graph that is the mirror image of the current graph.
     * The mirror image is a graph with all the edges reversed.
     *
     * @return A new {@code Graph<Label>} object that represents the mirrored graph.
     *
     * @throws Exception if an error occurs during the graph creation or edge reversal process.
     */
    public Graph<Label> mirror () throws Exception {
        Graph<Label> mirror = new Graph<>(getCardinal());

        for (int index = 0; index < getCardinal(); index++){
            addReversedEdges(mirror, index);
        }
        return mirror;
    }

    /**
     * Adds reversed edges from the node at the given index to the mirror graph.
     *
     * @param mirror the graph to which reversed edges are added
     * @param index the node index whose edges are reversed
     * @throws Exception if an error occurs during edge processing
     */
    private void addReversedEdges(Graph<Label> mirror, int index) throws Exception {
        for(Edge edge : getEdges(index)) {
            mirror.addEdge(edge.destination, edge.source, edge.label);
        }
    }

    protected LinkedList<Edge> getEdges(int index) {
        return incidence.get(index);
    }

    protected int getCardinal() {
        return cardinal;
    }

    public  LinkedList<Edge> getIncidents(int index) {
        return incidence.get(index);
    }

    public LinkedList<Integer> getIndexes(){
        LinkedList<Integer> indexes = new LinkedList<>();
        for(int index = 0; index < cardinal; index++){
            indexes.add(index);
        }
        return indexes;
    }
}
