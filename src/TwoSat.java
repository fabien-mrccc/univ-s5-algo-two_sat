import java.util.LinkedList;

public class TwoSat {
    //TODO: à partir de destinationIndex remonter jusqu'en haut et voir s'il y'a son opposé (trouver
    // une meilleure methode)
    public static boolean checkConsistency(LinkedList<Graph<Integer>.Edge> components){
        LinkedList<Integer> literalsIndexes = componentLiteralsIndexes(components);
        for(int literalIndex: literalsIndexes){
            if(containsOpposite(literalsIndexes,literalIndex)){return false;}
        }
        return true;
    }

    private static LinkedList<Integer> componentLiteralsIndexes(LinkedList<Graph<Integer>.Edge> component){
        LinkedList<Integer> componentLiteralsIndexes = new LinkedList<>();
        for(Graph<Integer>.Edge edge: component){
            if(edge != null){
                if(!componentLiteralsIndexes.contains(edge.source)){
                    componentLiteralsIndexes.add(edge.source);
                }
                if(!componentLiteralsIndexes.contains(edge.destination)){
                    componentLiteralsIndexes.add(edge.destination);
                }
            }
        }
        return componentLiteralsIndexes;
    }

    private static boolean containsOpposite(LinkedList<Integer> literalsIndexes, int literalIndex){
        for(int index : literalsIndexes){
            if(index == -literalIndex-1){return true;}
        }
        return false;
    }
}
