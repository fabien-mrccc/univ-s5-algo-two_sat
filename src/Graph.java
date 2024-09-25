import java.util.ArrayList;
import java.util.LinkedList;

public class Graph<Label>  {

    protected class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }

    }

    private final int cardinal;
    private final ArrayList<LinkedList<Edge>> incidency;

    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<>(size + 1);
        for (int i = 0; i<cardinal; i++) {
            incidency.add(i, new LinkedList<>());
        }
    }

    public void addEdge(int source, int dest, Label label) throws Exception {
	if (Math.max(source,dest) >= this.cardinal){
	    throw new Exception("Sommets trop gros pour la taille du graphe");
	}
        incidency.get(source).addLast(new Edge(source,dest,label));
    }

    public String toString() {
        String result = "";
        result = result.concat("Nombre sommets : " + cardinal + "\n");
        result = result.concat("literal/index : \n");
        for (int i = 0; i<cardinal;i++) {
	    result = result.concat(getVertex(i,cardinal) + "/"+ i + " ");
		}
        result = result.concat("\nArcs : \n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(getVertex(e.source, cardinal) + " -> " + getVertex(e.destination, cardinal) + ", étiquette : "
				       + e.label.toString() + "\n");
            }
        }
        return result;
    }

    protected LinkedList<Edge> getEdges(int index) {
        return incidency.get(index);
    }

    protected int getCardinal() {
        return cardinal;
    }

    protected int getVertex(int index, int size){
        if(index < size/2){
            return index - size/2;
        }
        return index - size/2 +1;
    }
}
