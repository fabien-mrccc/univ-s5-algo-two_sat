import java.util.LinkedList;

public class TwoSat {

    public static boolean checkConsistency(LinkedList<Graph<Integer>.Edge> components) {
        /*
        LinkedList<Integer> literalsIndexes = componentLiteralsIndexes(components);

        for (int literalIndex : literalsIndexes) {
            if (containsOpposite(literalsIndexes,literalIndex)) { return false; }
        }
        return true;

         */
        return false;
    }

    private static LinkedList<Graph<Integer>> buildGraphsFromComponents(LinkedList<Graph<Integer>.Edge> components) {

        LinkedList<Graph<Integer>.Edge> componentsCopy = new LinkedList<>(components);
        Graph<Integer> graph = new Graph<>(components.size());

        for (Graph<Integer>.Edge edge : componentsCopy) {
            try {
                graph.addEdge(edge.source, edge.destination, edge.label);
            }
            catch (Exception e) {
                throw new RuntimeException();
            }
        }

        while (!componentsCopy.isEmpty()) {
            Search search = new Search(graph);
            search.iterativeDFS(graph.getIndexes());
        }
        return null;
    }

    /*
    private static LinkedList<Integer> componentLiteralsIndexes(LinkedList<Graph<Integer>.Edge> components) {
        LinkedList<Integer> componentLiteralsIndexes = new LinkedList<>();
        for (Graph<Integer>.Edge edge: components) {
            if (edge != null) {
                if (!componentLiteralsIndexes.contains(edge.source)) {
                    componentLiteralsIndexes.add(edge.source);
                }
                if (!componentLiteralsIndexes.contains(edge.destination)) {
                    componentLiteralsIndexes.add(edge.destination);
                }
            }
        }
        return componentLiteralsIndexes;
    }

    private static boolean containsOpposite(LinkedList<Integer> literalsIndexes, int literalIndex){
        for (int index : literalsIndexes) {
            if (index == -literalIndex-1) { return true; }
        }
        return false;
    }

     */
}
