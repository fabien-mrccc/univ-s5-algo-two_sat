import java.util.LinkedList;

public class ImplicationGraph extends Graph {

    public ImplicationGraph(int size) {
        super(size);
    }

    ImplicationGraph mirror () {
        ImplicationGraph mirror = new ImplicationGraph(super.cardinal);

        for (int index = 0; index < super.cardinal; index++){

        }
        return mirror;
    }
}
