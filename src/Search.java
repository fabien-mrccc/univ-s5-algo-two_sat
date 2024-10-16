import java.util.LinkedList;

public class Search {

    private final Graph<Integer> graph;

    private final LinkedList<Graph<Integer>.Edge> predecessors;
    private final LinkedList<Integer> visitedIndex;
    private final LinkedList<Integer> entryTime;
    private final LinkedList<Integer> iterationTime;
    private final LinkedList<Integer> exitTime;

    private int currentTime;
    private int currentIteration;

    public Search(Graph<Integer> graph) {
        this.graph = graph;
        this.predecessors = new LinkedList<>();
        this.visitedIndex = new LinkedList<>();
        this.entryTime = new LinkedList<>();
        this.iterationTime = new LinkedList<>();
        this.exitTime = new LinkedList<>();
        initLinkedListAttributesWithEmptyValues();
        this.currentTime = 0;
        this.currentIteration = 0;
    }

    /**
     * Initializes linked list attributes for graph traversal with default values.
     */
    private void initLinkedListAttributesWithEmptyValues() {
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
    public LinkedList<Graph<Integer>.Edge> iterativeDFS(LinkedList<Integer> indexes) {

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
    private void explore(int index, Graph<Integer>.Edge predecessor, int iterationTime) {

        if (indexNotVisited(index)) {
            registerVisitedIndex(index);
            updateEnteringTime(index);
            updateIterationTime(index, iterationTime);
            updatePredecessor(index, predecessor);

            for (Graph<Integer>.Edge indexEdge : getGraph().getEdges(index)) {
                explore(indexEdge.destination, indexEdge, getCurrentIteration());
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

    private void updatePredecessor(int index, Graph<Integer>.Edge edge) {
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

    public LinkedList<Integer> getExitTime() {
        return exitTime;
    }

    private int getCurrentIteration() {
        return currentIteration;
    }

    private Graph<Integer> getGraph() {
        return graph;
    }

    private LinkedList<Graph<Integer>.Edge> getPredecessors() {
        return predecessors;
    }

    private LinkedList<Integer> getIterationTime() {
        return iterationTime;
    }

    private LinkedList<Integer> getEntryTime() {
        return entryTime;
    }

    private LinkedList<Integer> getVisitedIndex() {
        return visitedIndex;
    }
}
