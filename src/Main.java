import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        String filePath = "formulas/testSet1/formula9.txt";
        if (args.length > 0) {
            filePath = args[0];
        }

        ImplicationGraph implicationGraph = Parser.buildImplicationGraph(filePath);
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
            System.out.println("Formula " + filePath + ": satisfiable");
            exit(0);
        }
        else {
            System.out.println("Formula " + filePath + ": unsatisfiable");
            exit(-1);
        }
    }
}
