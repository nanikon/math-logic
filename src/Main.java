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
        System.out.println(result.toString());
        counter.fullVariables();
        counter.printValues();
    }
}
