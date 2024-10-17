import java.util.ArrayList;

public class IterativeDFSearch {

    private final Graph<Integer> graph;

    private final ArrayList<Edge<Integer>> predecessors;
    private final ArrayList<Integer> visitedIndex;
    private final ArrayList<Integer> entryTime;
    private final ArrayList<Integer> iterationTime;
    private final ArrayList<Integer> exitTime;

    private int currentTime;
    private int currentIteration;

    public IterativeDFSearch(Graph<Integer> graph) {
        this.graph = graph;
        this.predecessors = new ArrayList<>();
        this.visitedIndex = new ArrayList<>();
        this.entryTime = new ArrayList<>();
        this.iterationTime = new ArrayList<>();
        this.exitTime = new ArrayList<>();
        initArrayListAttributesWithEmptyValues();
        this.currentTime = 0;
        this.currentIteration = 0;
    }

    /**
     * Initializes ArrayList attributes for graph traversal with default values.
     */
    private void initArrayListAttributesWithEmptyValues() {

        for (int index = 0; index < getGraph().getCardinal(); index++) {
            getPredecessors().add(null);
            getEntryTime().add(0);
            getIterationTime().add(0);
            getExitTime().add(0);
        }
    }

    /**
     * Performs an iterative depth-first search (DFS) on the graph, marking visited indexes
     * and recording their predecessors, and returns the predecessor list.
     *
     * @param indexes a list of indexes representing the order in which the traversal should be executed.
     *                Each index corresponds to a vertex in the graph that will be explored in the given order.
     * @return the predecessor list, which contains the predecessors of each visited vertex.
     */
    public ArrayList<Edge<Integer>> iterativeDFS(ArrayList<Integer> indexes) {

        for (Integer index : indexes) {
            explore(index, null, incrementCurrentIteration());
        }
        return getPredecessors();
    }

    /**
     * Recursively explores the graph starting from the specified index.
     * <p>
     * This method marks the current vertex as visited, updates its entering time,
     * iteration time, and predecessor, then recursively explores its adjacent edges.
     * The exit time is updated after all adjacent vertices have been explored.
     * </p>
     *
     * @param index          The index of the vertex to explore.
     * @param predecessor    The predecessor leading to the current vertex.
     * @param iterationTime  The current iteration time for the exploration.
     */
    private void explore(int index, Edge<Integer> predecessor, int iterationTime) {

        if (indexNotVisited(index)) {
            registerVisitedIndex(index);
            updateEnteringTime(index);
            updateIterationTime(index, iterationTime);
            updatePredecessor(index, predecessor);

            for (Edge<Integer> indexEdge : getGraph().getEdges(index)) {
                explore(indexEdge.getDestination(), indexEdge, getCurrentIteration());
            }
            updateExitTime(index);
        }
    }

    private boolean indexNotVisited(int index) {
        return !getVisitedIndex().contains(index);
    }

    private void updateEnteringTime(int index) {
        getEntryTime().set(index, incrementTime());
    }

    private void registerVisitedIndex(int index) {
        getVisitedIndex().add(index);
    }

    private void updateIterationTime(int index, int iterationTime) {
        getIterationTime().set(index, iterationTime);
    }

    private void updatePredecessor(int index, Edge<Integer> edge) {
        getPredecessors().set(index, edge);
    }

    private void updateExitTime(int index) {
        getExitTime().set(index, incrementTime());
    }

    private int incrementTime() {
        return currentTime++;
    }

    private int incrementCurrentIteration() {
        return currentIteration++;
    }

    public ArrayList<Integer> getExitTime() {
        return exitTime;
    }

    private int getCurrentIteration() {
        return currentIteration;
    }

    private Graph<Integer> getGraph() {
        return graph;
    }

    private ArrayList<Edge<Integer>> getPredecessors() {
        return predecessors;
    }

    private ArrayList<Integer> getIterationTime() {
        return iterationTime;
    }

    private ArrayList<Integer> getEntryTime() {
        return entryTime;
    }

    private ArrayList<Integer> getVisitedIndex() {
        return visitedIndex;
    }
}
