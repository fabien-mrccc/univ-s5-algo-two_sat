import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        String filename = "formulas/formula.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        Parser graphParser = new Parser();
        ImplicationGraph implicationGraph = graphParser.buildImplicationGraph( filename);

        /*
        Graph<String> graph = parser.parse(filename);

        Kosaraju k = new Kosaraju(graph);
        int[] composantes = k.sccs();

        if (TwoSat.checkConsistency(composantes)) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }*/
        exit(0);
    }
}
