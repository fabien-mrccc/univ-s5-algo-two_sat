import java.util.ArrayList;

public class TwoSat {

    //TODO: utiliser getLiteral dans TwoSat et différencier les components pour l'évaluation de checkConsistency

    public static boolean checkConsistency(ArrayList<Edge<Integer>> components) {

        for (ArrayList<Integer> component : findConnectedComponents(components)) {
            for(Integer literalIndex : component){
                if (containsOpposite(component,literalIndex)) { return false; }
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<Integer>> findConnectedComponents(ArrayList<Edge<Integer>> predecessors) {
        // Initialiser les composantes connexes
        ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[predecessors.size()];

        // Parcours des sommets
        for (int i = 0; i < predecessors.size() ; i++) {
            // Si le sommet n'a pas encore été visité
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, predecessors, visited, component);
                connectedComponents.add(component);  // Ajouter la composante trouvée
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
