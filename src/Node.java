import java.util.HashMap;

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

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
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
}
