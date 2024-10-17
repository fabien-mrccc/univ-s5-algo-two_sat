import java.util.ArrayList;

public class ComponentsSearch {

    private final TwoSat twoSat;
    private final ArrayList<Boolean> visitedNodes;
    private final ArrayList<ArrayList<Integer>> identifiedComponents;

    public ComponentsSearch(TwoSat twoSat) {
        this.twoSat = twoSat;
        this.visitedNodes = new ArrayList<>();
        initVisitedNodes();
        this.identifiedComponents = new ArrayList<>();
    }

    /**
     * Initializes the list of visited nodes, marking all as unvisited.
     */
    private void initVisitedNodes() {

        for (int index = 0; index < getTwoSat().getUnidentifiedComponents().size(); index++) {
            getVisitedNodes().add(Boolean.FALSE);
        }
    }

    /**
     * Identifies all strongly connected components in the graph using depth-first search (DFS).
     *
     * @return An ArrayList of components, where each component is represented as an ArrayList of indexes.
     *
     */
    public ArrayList<ArrayList<Integer>> identifyComponents() {

        for (int nodeIndex = 0; nodeIndex < getVisitedNodes().size() ; nodeIndex++) {

            if (!getVisitedNodes().get(nodeIndex)) {
                getIdentifiedComponents().addLast(new ArrayList<>());
                iterativeDFS(nodeIndex);
            }
        }
        return getIdentifiedComponents();
    }

    /**
     * Performs a depth-first search (DFS) starting from the given node index to explore a
     * strongly connected component iteratively.
     *
     * @param currentNodeIndex The index of the node from which to start the DFS.
     *
     */
    public void iterativeDFS(Integer currentNodeIndex) {

        setVisitedNodes(currentNodeIndex);
        getIdentifiedComponents().getLast().add(currentNodeIndex);

        for (Edge<Integer> edge : getTwoSat().getUnidentifiedComponents()) {

            if (edge != null) {
                if (edge.getSource() == currentNodeIndex && !getVisitedNodes().get(edge.getDestination())) {
                    iterativeDFS(edge.getDestination());
                }
                if (edge.getDestination() == currentNodeIndex && !getVisitedNodes().get(edge.getSource())) {
                    iterativeDFS(edge.getSource());
                }
            }
        }
    }

    private TwoSat getTwoSat() {
        return twoSat;
    }

    private ArrayList<Boolean> getVisitedNodes() {
        return visitedNodes;
    }

    private void setVisitedNodes(int index) {
        visitedNodes.set(index, Boolean.TRUE);
    }

    private ArrayList<ArrayList<Integer>> getIdentifiedComponents() {
        return identifiedComponents;
    }
}
