import java.util.Comparator;
import java.util.LinkedList;

public class Kosaraju {

    /**
     * Processes the given implication graph to identify strongly connected components.
     *
     * @param originalGraph The implication graph to be processed.
     * @return A linked list of edges resulting from the depth-first search on the mirrored graph.
     */
    public static LinkedList<Graph<Integer>.Edge> process(ImplicationGraph originalGraph) {
        ImplicationGraph mirror = originalGraph.mirror();
        Search originalGraphSearch = new Search(originalGraph);
        LinkedList<Integer> sortedExitTime;
        Search mirrorGraphSearch = new Search(mirror);

        originalGraphSearch.iterativeDFS(originalGraph.getIndexes());
        sortedExitTime = sortVerticesByExitTimeDescending(originalGraphSearch.getExitTime());
        return mirrorGraphSearch.iterativeDFS(sortedExitTime);
    }

    /**
     * Sorts the exit times of the vertices in descending order and returns a new sorted list.
     *
     * @param exitTime A linked list of exit times to be sorted.
     * @return A new linked list containing the exit times sorted in descending order.
     */
    private static LinkedList<Integer> sortVerticesByExitTimeDescending(LinkedList<Integer> exitTime) {
        LinkedList<Integer> sortedExitTime = new LinkedList<>(exitTime);
        sortedExitTime.sort(Comparator.reverseOrder());
        return sortedExitTime;
    }
}
