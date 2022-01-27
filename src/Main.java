/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        AllReader reader = new AllReader(System.in);
        char[] input = reader.readStream();
        Parser parser = new Parser(input);
        Node result = parser.parse();
        System.out.println(result.toString());
        Counter counter = new Counter();
        System.out.println(counter.countAllVariables(result));
        System.out.println(counter.getUnicCountVariables());
    }
}
