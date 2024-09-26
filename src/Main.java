import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        String filename = "formulas/formula.txt";
        if (args.length > 0) {
            filename = args[0];
        }

        Parser graphParser = new Parser();
        ImplicationGraph implicationGraph = graphParser.buildImplicationGraph(filename);

        /*
        Kosaraju k = new Kosaraju(graph);
        int[] composantes = k.sccs();

        if (TwoSat.checkConsistency(composantes)) {
            System.out.println("Formula " + filename + ": satisfiable");
            exit(0);
        } else {
            System.out.println("Formula " + filename + ": unsatisfiable");
            exit(-1);
        }
        */
        exit(0);
    }
}
