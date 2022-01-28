import java.util.regex.Pattern;

/**
 * @author Natalia Nikonova
 */
public class Main {
    public static void main(String[] args) {
        checkAxiomChecker();

        /*Proof input = new Proof();
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
        output.printAllProof();*/
    }

    public static void checkAxiomChecker() {
        AxiomChecker checker = new AxiomChecker();
        AllReader reader = new AllReader(System.in);
        char[] input = reader.readStream();
        Parser parser = new Parser();
        Node result = parser.parse(input);
        System.out.println(result.toString());
        System.out.println(checker.checkFirst(result));
        System.out.println(checker.checkSecond(result));
        System.out.println(checker.checkThird(result));
        System.out.println(checker.checkFourth(result));
        System.out.println(checker.checkFifth(result));
        System.out.println(checker.checkSixth(result));
        System.out.println(checker.checkSeventh(result));
        System.out.println(checker.checkEighth(result));
        System.out.println(checker.checkNinth(result));
        System.out.println(checker.checkTenth(result));
    }
}
