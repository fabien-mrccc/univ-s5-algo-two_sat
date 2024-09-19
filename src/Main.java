import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        String filename = "formulas/formula.txt";
        if (0 < args.length) {
            filename = args[0];
        }

        try (BufferedReader lecteur = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = lecteur.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Parser parser = new Parser();
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
