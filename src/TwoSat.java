import java.util.ArrayList;
import java.util.HashSet;

public class TwoSat {

    private final ImplicationGraph implicationGraph;
    private final ArrayList<Edge<Integer>> unidentifiedComponents;

    public TwoSat(ImplicationGraph implicationGraph, ArrayList<Edge<Integer>> unidentifiedComponents) {
        this.implicationGraph = implicationGraph;
        this.unidentifiedComponents = unidentifiedComponents;
    }

    /**
     * Checks the consistency of the system by ensuring no component contains a literal
     * and its opposite.
     *
     * @return true if the system is consistent (no literal and its opposite appear in the same component),
     *         otherwise false.
     *
     */

    public boolean checkConsistency() {

        ComponentsSearch componentsSearch = new ComponentsSearch(this);

        for (ArrayList<Integer> component : componentsSearch.identifyComponents()) {

            HashSet<Integer> literals = literalsFromLiteralsIndexes(component);

            for (Integer literal : literals) {
                if (containsOpposite(literal, literals)) { return false; }
            }
        }
        return true;
    }

    /**
     * Converts a list of literals indexes into a set of corresponding literals.
     *
     * <p>This method iterates over the 'literalsIndexes' list, retrieves the corresponding
     * literal for each index by calling 'getLiteral()' from the implication graph, and adds
     * each literal to a HashSet. The result is a set of literals that corresponds to the input indexes.</p>
     * @param literalsIndexes An ArrayList of integer indexes representing literals.
     * @return A HashSet of literals corresponding to the provided indexes.
     *
     */

    private HashSet<Integer> literalsFromLiteralsIndexes(ArrayList<Integer> literalsIndexes) {

        HashSet<Integer> literals = new HashSet<>();

        for (int literalIndex : literalsIndexes) {
            literals.add(getImplicationGraph().getLiteral(literalIndex));
        }
        return literals;
    }

    /**
     * Checks if the given set contains the opposite of the specified literal.
     *
     * @param literalToCompare The literal to compare.
     * @param literals A set of literals in which to search for the opposite.
     * @return true if the set contains the opposite of the given literal, otherwise false.
     *
     */

    private boolean containsOpposite(Integer literalToCompare, HashSet<Integer> literals) {

        for (int literal : literals) {
            if (literalToCompare == -literal) { return true; }
        }
        return false;
    }

    private ImplicationGraph getImplicationGraph() {
        return implicationGraph;
    }

    public ArrayList<Edge<Integer>> getUnidentifiedComponents() {
        return unidentifiedComponents;
    }
}
