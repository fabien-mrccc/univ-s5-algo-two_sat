public class Main {
    public static void main(String[] args) {

        Graph<String> c3;

        c3 = new Graph<String>(3);
        c3.addArc(0,1,"");
        c3.addArc(1,2,"");
        c3.addArc(2,3,"");

        System.out.print(c3.toString());

    }
}
