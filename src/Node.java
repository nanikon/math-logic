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
            case VARIABLE: return this.value;
            case DENIAL: return "(!" + this.right.toString() + ")";
            case CONJUNCTION: return "(&," + this.left.toString() + "," + this.right.toString() + ")";
            case DISJUNCTION: return "(|," + this.left.toString() + "," + this.right.toString() + ")";
            case EXPRESSION: return "(->," + this.left.toString() + "," + this.right.toString() + ")";
            default: return "????";
        }
    }

    public String linealView(boolean isRoot) {
        String startString = "", endString = "";
        if (!isRoot) {
            startString = "(";
            endString = ")";
        }
        switch (this.type) {
            case VARIABLE: return value;
            case DENIAL: return "!" + this.right.linealView(false);
            case CONJUNCTION: return startString + this.left.linealView(false) + " & " + this.right.linealView(false) + endString;
            case DISJUNCTION: return startString + this.left.linealView(false) + " | " + this.right.linealView(false) + endString;
            case EXPRESSION: return startString + this.left.linealView(false) + " -> " + this.right.linealView(false) + endString;
            default: return "???";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        else if (obj instanceof Node) {
            Node other = (Node) obj;
            if (!this.type.equals(other.type)) { return false; }
            if (this.value != null) {
                if (!this.value.equals(other.value)) { return false; }
            } else if (other.value != null) { return false; }
            if (this.left != null) {
                if (!this.left.equals(other.left)) { return false; }
            } else if (other.left != null) { return false; }
            if (this.right != null) {
                return this.right.equals(other.right);
            } else return other.right == null;
        } else return false;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getValue() {
        return value;
    }

    public NodeType getType() {
        return type;
    }
}
