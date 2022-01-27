/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        AllReader reader = new AllReader(System.in);
        Counter counter = new Counter();
        char[] input = reader.readStream();
        Parser parser = new Parser(input, counter);
        Node result = parser.parse();
        counter.fullVariables();
        int trueCount = counter.runAllValues(result);
        if (trueCount == 0) {
            System.out.println("Unsatisfiable");
        } else if (trueCount == counter.getLenValues()) {
            System.out.println("Valid");
        } else {
            System.out.println("Satisfiable and invalid, " + trueCount + " true and " + (counter.getLenValues() - trueCount) + " false cases");
        }
    }
}
