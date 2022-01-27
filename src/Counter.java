import java.util.HashMap;
import java.util.Map;

/**
 * @author Natalia Nikonova
 */
public class Counter {
    private Map<String, boolean[]> variables = new HashMap<>();
    boolean[] stub = {false};

    public void reset() {
        variables = new HashMap<>();
    }

    public int getUnicCountVariables() { return variables.size(); }

    public int countAllVariables(Node node) {
        switch (node.getType()) {
            case VARIABLE:
                variables.put(node.getValue(), stub);
                return 1;
            case DENIAL:
                return countAllVariables(node.getRight());
            case EXPRESSION:
            case DISJUNCTION:
            case CONJUNCTION:
                int left = 0, right = 0;
                if (node.getLeft() != null) { left = countAllVariables(node.getLeft()); }
                if (node.getRight() != null) { right = countAllVariables(node.getRight()); }
                return left + right;
            default:
                return 0;
        }
    }
}
