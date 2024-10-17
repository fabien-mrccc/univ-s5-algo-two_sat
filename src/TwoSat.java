import java.util.LinkedList;

public class TwoSat {

    //TODO: utiliser getLiteral dans TwoSat et différencier les components pour l'évaluation de checkConsistency

    public static boolean checkConsistency(ArrayList<Edge<Integer>> components) {

        ArrayList<Integer> literalsIndexes = componentLiteralsIndexes(components);

        for (int literalIndex : literalsIndexes) {
            if (containsOpposite(literalsIndexes,literalIndex)) { return false; }
        }
        return true;
    }

    private static ArrayList<Integer> componentLiteralsIndexes(ArrayList<Edge<Integer>> components){
        ArrayList<Integer> componentLiteralsIndexes = new ArrayList<>();
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

    private static boolean containsOpposite(ArrayList<Integer> literalsIndexes, int literalIndex){
        for (int index : literalsIndexes) {
            if (index == -literalIndex-1) {return true; }
        }
        return false;
    }
}
