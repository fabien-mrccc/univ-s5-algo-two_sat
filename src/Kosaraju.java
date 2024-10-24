import java.util.ArrayList;
import java.util.Comparator;

public class Kosaraju {

    /**
     * Processes the given implication graph to identify strongly connected components.
     *
     * @param originalGraph The implication graph to be processed.
     * @return A linked list of edges resulting from the depth-first search on the mirrored graph.
     */
    public static ArrayList<Edge<Integer>> process(ImplicationGraph originalGraph) {

        Graph<Integer> mirror = originalGraph.mirror();
        GraphSearch<Integer> originalGraphSearch = new GraphSearch<>(originalGraph);
        ArrayList<Integer> sortedIndexesByExitTime;
        GraphSearch<Integer> mirrorGraphSearch = new GraphSearch<>(mirror);

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
    private static ArrayList<Integer> sortIndexesByExitTimeDescending(ArrayList<Integer> exitTime) {

        ArrayList<Integer> sortedIndexesByExitTime = new ArrayList<>();
        ArrayList<Integer> exitTimeSorted = new ArrayList<>(exitTime);
        exitTimeSorted.sort(Comparator.reverseOrder());

        for (int exit : exitTimeSorted) {
            sortedIndexesByExitTime.add(exitTime.indexOf(exit));
        }
        return sortedIndexesByExitTime;
    }
}
