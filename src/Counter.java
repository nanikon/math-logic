import java.util.HashMap;
import java.util.Map;

/**
 * @author Natalia Nikonova
 */
public class Counter {
    private Map<String, boolean[]> variables = new HashMap<>();
    private final boolean[] stub = {false};
    private int countVariables = 0;
    private int lenValues = 0;

    public void reset() {
        variables = new HashMap<>();
    }

    public void addVariable(String name) {
        variables.put(name, stub);
    }

    public int getLenValues() { return lenValues; }

    private void countCommonValues() {
        countVariables = variables.size();
        lenValues = (int) Math.pow(2, countVariables);
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

    private boolean parseValue(Node node, int i) {
        switch (node.getType()) {
            case VARIABLE:
                return variables.get(node.getValue())[i];
            case DENIAL:
                return !parseValue(node.getRight(), i);
            case CONJUNCTION:
                return parseValue(node.getLeft(), i) && parseValue(node.getRight(), i);
            case DISJUNCTION:
                return parseValue(node.getLeft(), i) || parseValue(node.getRight(), i);
            case EXPRESSION:
                return !parseValue(node.getLeft(), i) || parseValue(node.getRight(), i);
            default:
                return false;
        }
    }

    public int runAllValues(Node node) {
        int result = 0;
        for (int i = 0; i < lenValues; i++) {
            if (parseValue(node, i)) {result++; }
        }
        return result;
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
