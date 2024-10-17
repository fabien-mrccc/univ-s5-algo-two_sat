import java.util.ArrayList;

public class TwoSat {

    private final ImplicationGraph implicationGraph;

    public TwoSat(ImplicationGraph implicationGraph) {
        this.implicationGraph = implicationGraph;
    }

    //TODO ... traiter chaque component séparément
    public boolean checkConsistency(ArrayList<Edge<Integer>> components) {

        ArrayList<Integer> literalsIndexes = literalsIndexesFromComponents(components);

        while (!literalsIndexes.isEmpty()) {

            Edge<Integer> edge = components.removeFirst();
            // ...
        }

        ArrayList<Integer> literals = literalsFromLiteralsIndexes(literalsIndexes);

        for (int literal : literals) {
            if (containsOpposite(literal, literals)) { return false; }
        }
        return true;
    }

    private ArrayList<Integer> literalsIndexesFromComponents(ArrayList<Edge<Integer>> components) {

        ArrayList<Integer> literalsIndexes = new ArrayList<>();

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

    private ArrayList<Integer> literalsFromLiteralsIndexes(ArrayList<Integer> literalsIndexes) {

        ArrayList<Integer> literals = new ArrayList<>();

        for (int literalIndex : literalsIndexes) {

            Integer literal = getImplicationGraph().getLiteral(literalIndex);

            if (!literals.contains(literal)) {
                literals.add(literal);
            }
        }
        return literals;
    }

    private boolean containsOpposite(Integer literalToCompare, ArrayList<Integer> literals) {

        for (int literal : literals) {
            if (-literalToCompare == -literal) { return true; }
        }
        return false;
    }

    private ImplicationGraph getImplicationGraph() {
        return implicationGraph;
    }
}
