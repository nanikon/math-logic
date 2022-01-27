import java.util.HashMap;
import java.util.Map;

/**
 * @author Natalia Nikonova
 */
public class Counter {
    private Map<String, boolean[]> variables = new HashMap<>();
    boolean[] stub = {false};
    int countVariables = 0;
    int lenValues = 0;

    public void reset() {
        variables = new HashMap<>();
    }

    public void addVariable(String name) {
        variables.put(name, stub);
    }

    public int getUnicCountVariables() {
        return countVariables;
    }

    private void countCommonValues() {
        countVariables = variables.size();
        lenValues = (int) Math.pow(2, countVariables);
    }

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

    public void fullVariables() {
        int i = 0;
        countCommonValues();
        for (String key: variables.keySet()) {
            boolean[] values = new boolean[lenValues];
            boolean value = false;
            int changeMarker = (int) Math.pow(2, i);
            for (int j = 0; j < lenValues; j++) {
                values[j] = value;
                if (j % changeMarker == (changeMarker - 1)){
                    value = !value;
                }
            }
            variables.put(key,values);
            i++;
        }
    }

    public void printValues() {
        for (String key: variables.keySet()) {
            System.out.print(key + "\t");
        }
        System.out.println();
        for (int i = 0; i < lenValues; i++) {
            for (String key: variables.keySet()) {
                System.out.print(variables.get(key)[i] + "\t");
            }
            System.out.println();
        }
    }
}
