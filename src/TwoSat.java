import java.util.ArrayList;

public class TwoSat {

    private final ImplicationGraph implicationGraph;

    public TwoSat(ImplicationGraph implicationGraph) {
        this.implicationGraph = implicationGraph;
    }

    //TODO ... traiter chaque component séparément
    public boolean checkConsistency(ArrayList<Edge<Integer>> components) {

        for (ArrayList<Integer> component : findComponents(components)) {
            ArrayList<Integer> literals = literalsFromLiteralsIndexes(component);
            for(Integer literal : literals){
                if (containsOpposite(literal, literals)) { return false; }
            }
        }
        return true;

    }

    public static ArrayList<ArrayList<Integer>> findComponents(ArrayList<Edge<Integer>> predecessors) {
        ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[predecessors.size()];

        for (int i = 0; i < predecessors.size() ; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, predecessors, visited, component);
                connectedComponents.add(component);
            }
        }
        System.out.println("components: "+ connectedComponents);
        return connectedComponents;
    }

    private static void dfs(int node, ArrayList<Edge<Integer>> predecessors, boolean[] visited, ArrayList<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (Edge edge : predecessors) {
            if (edge != null) {
                if (edge.getSource() == node && !visited[edge.getDestination()]) {
                    dfs(edge.getDestination(), predecessors, visited, component);
                }
                if (edge.getDestination() == node && !visited[edge.getSource()]) {
                    dfs(edge.getSource(), predecessors, visited, component);
                }
            }
        }
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
            if (literalToCompare == -literal) { return true; }
        }
        return false;
    }

    private ImplicationGraph getImplicationGraph() {
        return implicationGraph;
    }
}
