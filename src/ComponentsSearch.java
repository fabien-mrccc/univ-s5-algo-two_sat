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

    private void initVisitedNodes() {

        for (int index = 0; index < getTwoSat().getUnidentifiedComponents().size(); index++) {
            getVisitedNodes().add(Boolean.FALSE);
        }
    }

    public ArrayList<ArrayList<Integer>> identifyComponents() {

        for (int nodeIndex = 0; nodeIndex < getVisitedNodes().size() ; nodeIndex++) {

            if (!getVisitedNodes().get(nodeIndex)) {
                getIdentifiedComponents().addLast(new ArrayList<>());
                iterativeDFS(nodeIndex);
            }
        }
        return getIdentifiedComponents();
    }

    public void iterativeDFS(Integer currentNodeIndex) {

        setVisitedNodes(currentNodeIndex, Boolean.TRUE);
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

    public ArrayList<Boolean> getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(int index, Boolean value) {
        visitedNodes.set(index, value);
    }

    public ArrayList<ArrayList<Integer>> getIdentifiedComponents() {
        return identifiedComponents;
    }
}
