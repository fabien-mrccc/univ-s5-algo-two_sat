import java.util.LinkedList;

public class TwoSat {

    private final ImplicationGraph implicationGraph;

    public TwoSat(ImplicationGraph implicationGraph) {
        this.implicationGraph = implicationGraph;
    }

    public boolean checkConsistency(LinkedList<Edge<Integer>> components) {

        LinkedList<LinkedList<ImplicationGraph>> componentsGraphs = componentsGraphs(components);

        //for (ImplicationGraph component : componentsGraphs) {
            //TODO ... inclure les commentaires ci-dessous dans cette boucle
        //}

        /*
        LinkedList<Integer> literalsIndexes = literalsIndexesFromComponents(components);

        LinkedList<Integer> literals = literalsFromLiteralsIndexes(literalsIndexes);

        for (int literal : literals) {
            if (containsOpposite(literal, literals)) { return false; }
        }
        return true;
         */
        return false;
    }

    //TODO ...
    private LinkedList<LinkedList<ImplicationGraph>> componentsGraphs (LinkedList<Edge<Integer>> components) {
        return null;
    }

    private LinkedList<Integer> literalsIndexesFromComponents(LinkedList<Edge<Integer>> components) {

        LinkedList<Integer> literalsIndexes = new LinkedList<>();

        for (Edge<Integer> edge : components) {

            if (edge != null) {
                if (!literalsIndexes.contains(edge.getSource())) {
                    literalsIndexes.add(edge.getSource());
                }
                if (!literalsIndexes.contains(edge.getDestination())) {
                    literalsIndexes.add(edge.getDestination());
                }
            }
        }
        return literalsIndexes;
    }

    private LinkedList<Integer> literalsFromLiteralsIndexes(LinkedList<Integer> literalsIndexes) {

        LinkedList<Integer> literals = new LinkedList<>();

        for (int literalIndex : literalsIndexes) {

            Integer literal = getImplicationGraph().getLiteral(literalIndex);

            if (!literals.contains(literal)) {
                literals.add(literal);
            }
        }
        return literals;
    }

    private boolean containsOpposite(Integer literal, LinkedList<Integer> literals) {

        for (int literalToCompare : literals) {
            if (literalToCompare == -literal) { return true; }
        }
        return false;
    }

    private ImplicationGraph getImplicationGraph() {
        return implicationGraph;
    }
}
