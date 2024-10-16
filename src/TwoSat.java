import java.util.LinkedList;

public class TwoSat {

    //TODO: utiliser getLiteral dans TwoSat et différencier les components pour l'évaluation de checkConsistency

    public static boolean checkConsistency(LinkedList<Edge<Integer>> components) {

        LinkedList<Integer> literalsIndexes = componentLiteralsIndexes(components);

        for (int literalIndex : literalsIndexes) {
            if (containsOpposite(literalsIndexes,literalIndex)) { return false; }
        }
        return true;
    }

    private static LinkedList<Integer> componentLiteralsIndexes(LinkedList<Edge<Integer>> components) {
        LinkedList<Integer> componentLiteralsIndexes = new LinkedList<>();
        for (Edge<Integer> edge: components) {
            if (edge != null) {
                if (!componentLiteralsIndexes.contains(edge.getSource())) {
                    componentLiteralsIndexes.add(edge.getSource());
                }
                if (!componentLiteralsIndexes.contains(edge.getDestination())) {
                    componentLiteralsIndexes.add(edge.getDestination());
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
}
