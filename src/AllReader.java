import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Natalia Nikonova
 */
public class AllReader {
    private Scanner input;

    public AllReader(InputStream input) {
        this.input = new Scanner(input);
    }

    public char[] readStream() {
        StringBuilder result = new StringBuilder();
        while (input.hasNextLine()) {
            result.append(
                    input.nextLine()
                    .replace(" ", "")
                    .replace("\t", "")
            );
        }
        return result.toString().toCharArray();
    }
}
