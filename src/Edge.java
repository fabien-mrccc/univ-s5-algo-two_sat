public class Edge<Label> {

    private final int source;
    private final int destination;
    private final Label label;

    public Edge(int from, int to, Label label) {
        this.source = from;
        this.destination = to;
        this.label = label;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public Label getLabel() {
        return label;
    }
}
