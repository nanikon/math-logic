/**
 * @author Natalia Nikonova
 */
public class Maker {
    public Proof proofDeductionTheorem(Proof input) {
        Proof output = new Proof();
        output.setBetta(new Node(input.getAlfa(), input.getBetta(), null, NodeType.EXPRESSION));
        for (Node expr : input.getProof()) {
            if (expr.equals(input.getAlfa())) {
                handleAsAlpha(expr, output);
            }
        }
        return output;
    }

    public void handleAsAlpha(Node expr, Proof output) {
        Node impl = new Node(expr, expr, null, NodeType.EXPRESSION); // последнее на добавление i
        Node firstAx = new Node(expr, impl, null, NodeType.EXPRESSION); // первое на добавление, i - 0.8
        Node reverseFirstAx = new Node(impl, expr, null, NodeType.EXPRESSION);
        Node firstAx2 = new Node(expr, reverseFirstAx, null, NodeType.EXPRESSION); // второе или предпоследнее, i - 0.2
        Node mp = new Node(firstAx2, impl, null, NodeType.EXPRESSION); // i - 0.4
        Node secondAx = new Node(firstAx, mp, null, NodeType.EXPRESSION); // i - 0.6

        output.addToProofEnd(firstAx);
        output.addToProofEnd(firstAx2);
        output.addToProofEnd(secondAx);
        output.addToProofEnd(mp);
        output.addToProofEnd(impl);
    }
}
