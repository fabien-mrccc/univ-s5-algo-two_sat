import java.util.LinkedList;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        String filename = "formulas/testSet0/formula3.txt";
        if (args.length > 0) {
            filename = args[0];
        }

        Parser graphParser = new Parser();
        ImplicationGraph implicationGraph = graphParser.buildImplicationGraph(filename);
        System.out.println(implicationGraph);

        LinkedList<Edge<Integer>> components = Kosaraju.process(implicationGraph);
        System.out.println(components);

        for (Edge<Integer> edge : components) {
            if (edge != null)
                System.out.println(edge.getSource() + " " + edge.getDestination());
        }

        if (TwoSat.checkConsistency(components)) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        }
        else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }
        exit(0);
    }
}
