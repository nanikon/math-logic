import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Natalia Nikonova
 */
public class AllReader {
    private final Scanner input;

    public AllReader(InputStream input) {
        this.input = new Scanner(input);
    }

    public char[] readStream() {
        StringBuilder result = new StringBuilder();
        while (input.hasNextLine()) {
            result.append(input.nextLine().replace("\\s+", "").trim());
        }
        return result.toString().toCharArray();
    }

    public String readLine() {
        return input.hasNextLine() ? input.nextLine() : null;
    }
}
