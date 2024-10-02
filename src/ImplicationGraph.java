import java.util.LinkedList;
import java.util.Stack;

public class ImplicationGraph extends Graph<Integer> {

    public ImplicationGraph(int size) {
        super(size);
    }

    @Override
    public String toString() {
        String result = "";
        result = result.concat("\nNombre sommets : "+ getCardinal() + "\n\n");
        result = result.concat("Littéral/Index : ");

        for (int i = 0; i<getCardinal(); i++) {
            result = result.concat(getLiteral(i) + "/" + i + " ");
        }

        result = result.concat("\n\nArcs : \n");

        for (int i = 0; i<getCardinal(); i++) {
            for (Edge e : getEdges(i)) {
                result = result.concat(getLiteral(e.source) + " -> " + getLiteral(e.destination) + ", étiquette : " + e.label.toString() + "\n");
            }
        }
        return result;
    }

    /**
     * Adds edges to the graph based on the given clause.
     *
     * <p>The clause represents a disjunction (l1 ∨ l2), which is logically equivalent to
     * two implications: ¬l1 → l2 and ¬l2 → l1. These implications are represented as
     * directed edges in the graph.</p>
     *
     * @param clauseLine an array representing a clause, where each element is a literal or label
     * @throws Exception if an error occurs while adding edges
     */
    public void addEdgesFromClause(String[] clauseLine) throws Exception {
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[0])),
                getLiteralIndex(Integer.parseInt(clauseLine[1])),Integer.parseInt(clauseLine[2]));
        addEdge(getLiteralIndex(- Integer.parseInt(clauseLine[1])),
                getLiteralIndex(Integer.parseInt(clauseLine[0])),Integer.parseInt(clauseLine[2]));
    }

    /**
     * Returns the index of the given literal, ensuring it is within the range [0, 2k-1].
     *
     * <p>For negative literals, the index is adjusted to ensure it is positive. The index
     * is calculated based on a graph representation where literals range from -k to k except 0, and
     * are mapped to indices from 0 to 2k-1.</p>
     *
     * @param literal the literal to be converted to an index (ranging from -k to k except 0)
     * @return the corresponding positive index in the range [0, 2k-1]
     */
    private int getLiteralIndex(int literal){
        if(literal < 0){
            return literal + getCardinal()/2;
        }
        return literal + getCardinal()/2 -1;
    }

    /**
     * Returns the literal corresponding to the given index, converting it back from the range [0, 2k-1].
     *
     * <p>This method reverses the mapping applied by {@code getLiteralIndex}, converting an index
     * back into its corresponding literal. The index is expected to be in the range [0, 2k-1].</p>
     *
     * @param index the index to be converted back to a literal
     * @return the corresponding literal (ranging from -k to k except 0)
     */
    private int getLiteral(int index) {
        if (index < getCardinal() / 2) {
            return index - getCardinal() / 2;
        }
        return index - getCardinal() / 2 + 1;
    }
}
