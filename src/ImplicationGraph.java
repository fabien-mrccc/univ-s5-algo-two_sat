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
}
