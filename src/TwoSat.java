import java.util.ArrayList;
import java.util.LinkedList;

public class TwoSat {
    //TODO: à partir de destinationIndex remonter jusqu'en haut et voir s'il y'a son opposé (trouver
    // une meilleure methode)
    public static boolean checkConsistency(ArrayList<Graph<Integer>.Edge> components){
        ArrayList<Integer> literalsIndexes = componentLiteralsIndexes(components);
        for(int literalIndex: literalsIndexes){
            if(containsOpposite(literalsIndexes,literalIndex)){return false;}
        }
        return true;
    }

    private static ArrayList<Integer> componentLiteralsIndexes(ArrayList<Graph<Integer>.Edge> component){
        ArrayList<Integer> componentLiteralsIndexes = new ArrayList<>();
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

    private static boolean containsOpposite(ArrayList<Integer> literalsIndexes, int literalIndex){
        for(int index : literalsIndexes){
            if(index == -literalIndex-1){return true;}
        }
        return false;
    }
}
