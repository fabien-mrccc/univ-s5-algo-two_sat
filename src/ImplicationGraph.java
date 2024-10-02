import java.util.LinkedList;
import java.util.Stack;

public class ImplicationGraph extends Graph<Integer> {

    public ImplicationGraph(int size) {
        super(size);
    }

    public void addEdgesFromClause(String[] clauseLine) throws Exception {
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[0])),
                getLiteralIndex(Integer.parseInt(clauseLine[1])),Integer.parseInt(clauseLine[2]));
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[1])),
                getLiteralIndex(Integer.parseInt(clauseLine[0])),Integer.parseInt(clauseLine[2]));
    }

    /**
     * Return the index of the literal we gave in parameters.
     * @param literal the literal we want the index of.
     * @return the index of the literal
     */
    private int getLiteralIndex(int literal){
        if(literal < 0){
            return literal + getCardinal()/2;
        }
        return literal + getCardinal()/2 -1;
    }

    protected int getLiteral(int index){
        if(index < getCardinal()/2){
            return index - getCardinal()/2;
        }
        return index - getCardinal()/2 +1;
    }

    public String toString() {
        String result = "";
        result = result.concat(STR."Nombre sommets : \{getCardinal()}\n");
        result = result.concat("Litteral/Index : \n");

        for (int i = 0; i<getCardinal();i++) {
            result = result.concat(STR."\{getLiteral(i)}/\{i} ");
        }

        result = result.concat("\nArcs : \n");

        for (int i = 0; i<getCardinal();i++) {
            for (Edge e : getEdges(i)) {
                result = result.concat(STR."\{getLiteral(e.source)} -> \{getLiteral(e.destination)}, étiquette : \{e.label.toString()}\n");
            }
        }
        return result;
    }





}
