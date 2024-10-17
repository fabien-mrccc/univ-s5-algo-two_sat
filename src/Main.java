import java.util.ArrayList;
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        String filePath = "formulas/testSet1/formula2.txt";
        if (args.length > 0) {
            filePath = args[0];
        }

        System.out.println("\nFILEPATH: " + filePath);

        ImplicationGraph implicationGraph = Parser.buildImplicationGraph(filePath);
        System.out.println(implicationGraph);

        ArrayList<Edge<Integer>> components = Kosaraju.process(implicationGraph);
        printComponents(components, implicationGraph);

        TwoSat twoSat = new TwoSat(implicationGraph, components);
        printSatisfiability(twoSat, filePath);
    }

    private static void printComponents(ArrayList<Edge<Integer>> components, ImplicationGraph implicationGraph) {
        System.out.println("ALL UNIDENTIFIED COMPONENTS EDGES: " + "[index -> index], [literal -> literal]");
        for (Edge<Integer> edge : components) {
            if (edge != null)
                System.out.println(edge.print(implicationGraph));
        }
        System.out.println();
    }
    
    private static void printSatisfiability(TwoSat twoSat, String filePath) {

        boolean isConsistent = twoSat.checkConsistency();
        System.out.println("\nSATISFIABILITY: ");

        if (isConsistent) {
            System.out.println("Formula " + filePath + ": satisfiable");
            exit(0);
        }
        else {
            System.out.println("Formula " + filePath + ": unsatisfiable");
            exit(-1);
        }
    }
}
