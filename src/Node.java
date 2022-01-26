/**
 * @author Natalia Nikonova
 */
public class Node {
    private Node left;
    private Node right;
    private String value;
    private NodeType type;

    public Node(Node left, Node right, String value, NodeType type) {
        this.left = left;
        this.right= right;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        switch (this.type) {
            case VARIABLE:
                return this.value;
            case DENIAL:
                return "(!" + this.right.toString() + ")";
            case CONJUNCTION:
                return "(&," + this.left.toString() + "," + this.right.toString() + ")";
            case DISJUNCTION:
                return "(|," + this.left.toString() + "," + this.right.toString() + ")";
            case EXPRESSION:
                return "(->," + this.left.toString() + "," + this.right.toString() + ")";
            default:
                return "????";
        }
    }
}
