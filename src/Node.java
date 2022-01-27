/**
 * @author Natalia Nikonova
 */
public class Node {
    private final Node left;
    private final Node right;
    private final String value;
    private final NodeType type;

    public Node(Node left, Node right, String value, NodeType type) {
        this.left = left;
        this.right= right;
        this.value = value;
        this.type = type;
    }

    public String fullStruct() {
        return "(" + left.toString() + "," + right.toString() + "," + value + "," + type.name() + ")";
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
