import java.util.LinkedList;
import java.util.List;

/**
 * @author Natalia Nikonova
 */
public class Proof {
    private Node alfa;
    private Node betta;
    private List<Node> context = new LinkedList<>();
    private List<Node> proof = new LinkedList<>();

    public Node getAlfa() {
        return alfa;
    }

    public void setAlfa(Node alfa) {
        this.alfa = alfa;
    }

    public Node getBetta() {
        return betta;
    }

    public void setBetta(Node betta) {
        this.betta = betta;
    }

    public List<Node> getContext() {
        return context;
    }

    public List<Node> getProof() {
        return proof;
    }

    public void addToContext(Node node) {
        this.context.add(node);
    }

    public void addToProofEnd(Node node) {
        this.proof.add(node);
    }

    public void addTpProofBefore(Node before, Node insert) {
        this.proof.add(this.proof.indexOf(before), insert);
    }

    public void printAllProof() {
        for (Node expr: context) {
            System.out.print(expr.toString() + ",");
        }
        System.out.println(alfa.toString() + "|-" + betta.toString());
        printProof();
    }

    public void printProof() {
        for (Node expr: proof) {
            System.out.println(expr.toString());
        }
    }
}
