/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("P1’->!QQ->!R10&S|!T&U&V".toCharArray());
        Node result = parser.parse();
        System.out.println(result.toString());
    }
}
