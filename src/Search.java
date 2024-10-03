import java.util.LinkedList;

public class Search {

    private final Graph<Integer> graph;

    private final LinkedList<Graph<Integer>.Edge> predecessors;
    private final LinkedList<Integer> visitedIndex;
    private final LinkedList<Integer> enteringTime;
    private final LinkedList<Integer> iterationTime;
    private final LinkedList<Integer> exitTime;

    private int currentTime;
    private int currentIteration;

    public Search(Graph<Integer> graph) {
        this.graph = graph;
        this.predecessors = new LinkedList<>();
        this.visitedIndex = new LinkedList<>();
        this.enteringTime = new LinkedList<>();
        this.iterationTime = new LinkedList<>();
        this.exitTime = new LinkedList<>();
        this.currentTime = 0;
        this.currentIteration = 0;
    }

    /**
     * Performs an iterative depth-first search (DFS) on the graph, marking visited indexes
     * and recording their predecessors, and returns the predecessor list.
     * @return the predecessor list.
     */
    public LinkedList<Graph<Integer>.Edge> iterativeDFS(LinkedList<Integer> indexes) {

        for (int index : indexes) {
            if (indexNotVisited(index)) {
                updateEnteringTime(index);
                updateIterationTime(index, incrementCurrentIteration());
                registerVisitedIndex(index);
                updatePredecessor(index, null);

                for (Graph<Integer>.Edge edge : getGraph().getEdges(index)) {
                    explore(edge);
                }
                updateExitTime(index);
            }
        }
        return getPredecessors();
    }

    /**
     * Recursively explores the graph from the given edge, marking visited indexes and
     * recording their predecessors.
     * @param edge the edge from which begin the exploration.
     */
    public void explore(Graph<Integer>.Edge edge) {
        int destinationIndex = edge.destination;

        if (indexNotVisited(destinationIndex)) {
            updateEnteringTime(destinationIndex);
            updateIterationTime(destinationIndex, getCurrentIteration());
            registerVisitedIndex(destinationIndex);
            updatePredecessor(destinationIndex, edge);

            for (Graph<Integer>.Edge destinationEdge : getGraph().getEdges(destinationIndex)) {
                explore(destinationEdge);
            }
            updateExitTime(destinationIndex);
        }
    }

    private boolean indexNotVisited(int index) {
        return !visitedIndex.contains(index);
    }

    private void updateEnteringTime(int index) {
        enteringTime.set(index, incrementTime());
    }

    private void registerVisitedIndex(int index) {
        visitedIndex.add(index);
    }

    private void updateIterationTime(int index, int iterationTime) {
        this.iterationTime.set(index, iterationTime);
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
}
