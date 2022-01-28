import java.util.regex.Pattern;

/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        Proof input = new Proof();
        AllReader reader = new AllReader(System.in);
        String firstLine = reader.readLine();
        String[] firstSplit = firstLine.split(Pattern.quote("|-"));
        Parser parser = new Parser();
        input.setBetta(parser.parse(firstSplit[1].toCharArray()));
        String[] context = firstSplit[0].split(",");
        input.setAlfa(parser.parse(context[context.length - 1].toCharArray()));
        for (int i = 0; i < context.length - 1; i++) {
            input.addToContext(parser.parse(context[i].toCharArray()));
        }
        String exprProof = reader.readLine();
        while (exprProof != null) {
            input.addToProofEnd(parser.parse(exprProof.toCharArray()));
            exprProof = reader.readLine();
        }
        input.printAllProof();
        System.out.println("result:");
        Maker maker = new Maker();
        Proof output = maker.proofDeductionTheorem(input);
        output.printAllProof();
        /*char[] input = reader.readStream();
        Node result = parser.parse();
        System.out.println(result.toString());*/
    }
}
