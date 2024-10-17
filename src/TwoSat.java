import java.util.ArrayList;
import java.util.HashSet;

public class TwoSat {

    private final ImplicationGraph implicationGraph;
    private final ArrayList<Edge<Integer>> unidentifiedComponents;

    public TwoSat(ImplicationGraph implicationGraph, ArrayList<Edge<Integer>> unidentifiedComponents) {
        this.implicationGraph = implicationGraph;
        this.unidentifiedComponents = unidentifiedComponents;
    }

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

    private HashSet<Integer> literalsFromLiteralsIndexes(ArrayList<Integer> literalsIndexes) {

        HashSet<Integer> literals = new HashSet<>();

        for (int literalIndex : literalsIndexes) {
            literals.add(getImplicationGraph().getLiteral(literalIndex));
        }
        return literals;
    }

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
