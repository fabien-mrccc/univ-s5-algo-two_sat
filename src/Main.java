import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        String filename = "formulas/testSet1/formula9.txt";
        if (args.length > 0) {
            filename = args[0];
        }

        ImplicationGraph implicationGraph = Parser.buildImplicationGraph(filename);
        System.out.println(implicationGraph);
        System.out.println(implicationGraph.mirror());

        ArrayList<Edge<Integer>> components = Kosaraju.process(implicationGraph);
        System.out.println(components);

        for (Edge<Integer> edge : components) {
            if (edge != null)
                System.out.println("component edge - " + edge.getSource() + " " + edge.getDestination());
        }

        TwoSat twoSat = new TwoSat(implicationGraph, components);

        if (twoSat.checkConsistency()) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        }
        else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }
    }
}
