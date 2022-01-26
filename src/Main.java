/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("!A&!B->!(A|B)".toCharArray());
        Node result = parser.parse();
        System.out.println(result.toString());
    }
}
