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
        LinkedList<Integer> sortedIndexesByExitTime;
        Search mirrorGraphSearch = new Search(mirror);

        originalGraphSearch.iterativeDFS(originalGraph.getIndexes());
        sortedIndexesByExitTime = sortIndexesByExitTimeDescending(originalGraphSearch.getExitTime());
        return mirrorGraphSearch.iterativeDFS(sortedIndexesByExitTime);
    }

    /**
     * Sorts the indexes by their exit times in descending order.
     *
     * @param exitTime A linked list of exit times to be sorted.
     * @return A linked list of indexes corresponding order by the sorted exit times in descending order.
     */
    private static LinkedList<Integer> sortIndexesByExitTimeDescending(LinkedList<Integer> exitTime) {
        LinkedList<Integer> sortedIndexesByExitTime = new LinkedList<>();
        LinkedList<Integer> exitTimeSorted = new LinkedList<>(exitTime);
        exitTimeSorted.sort(Comparator.reverseOrder());
        for(int exit: exitTimeSorted){
            sortedIndexesByExitTime.add(exitTime.indexOf(exit));
        }
        return sortedIndexesByExitTime;
    }
}
