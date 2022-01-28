import java.util.ArrayList;
import java.util.List;

/**
 * @author Natalia Nikonova
 */
public class Maker {
    public Proof proofDeductionTheorem(Proof input) {
        Proof output = new Proof();
        output.setBetta(new Node(input.getAlfa(), input.getBetta(), null, NodeType.EXPRESSION));
        ArrayList<Node> inputProof = new ArrayList<>(input.getProof());
        for (int i = 0; i < inputProof.size(); i++) {
            Node expr = inputProof.get(i);
            if (expr.equals(input.getAlfa())) {
                handleAsAlpha(expr, output);
                System.out.println(i + " обработалась как совпадающая с alpha");
            } else {
                int index_delta_j = foundIndexToMP(inputProof, expr, i);
                if (index_delta_j >= 0 ) {
                    System.out.println(i + " обработалась как mp");
                    handleAsMP(expr, inputProof.get(index_delta_j), input.getAlfa(), output);
                } else {
                    System.out.println(i + " не нашла mp. index_delta_j: " + index_delta_j);
                    output.addToProofEnd(expr);
                }
            }
        }
        return output;
    }

    private int foundIndexToMP(List<Node> proof, Node expr, int i) {
        for (int j = 0; j < i - 1; j++) {
            Node delta_j = proof.get(j);
            for (int k = j; k < i; k++) {
                Node delta_k = proof.get(k);
                if (delta_k.equals(new Node(delta_j, expr, null, NodeType.EXPRESSION))) {
                    return j;
                }
                if (delta_j.equals(new Node(delta_k, expr, null, NodeType.EXPRESSION))) {
                    return k;
                }
            }
        }
        return -1;
    }

    private void handleAsAlpha(Node expr, Proof output) {
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

    private void handleAsMP(Node delta_i, Node delta_j, Node alpha, Proof output) {
        Node alphaToDeltaI = new Node(alpha, delta_i, null, NodeType.EXPRESSION);
        Node alphaToDeltaJ = new Node(alpha, delta_j, null, NodeType.EXPRESSION);
        Node jToI = new Node(delta_j, delta_i, null, NodeType.EXPRESSION);
        Node alphaToJToI = new Node(alpha, jToI, null, NodeType.EXPRESSION);
        Node mp = new Node(alphaToJToI, alphaToDeltaI, null, NodeType.EXPRESSION);
        Node secondAx = new Node(alphaToDeltaJ, mp, null, NodeType.EXPRESSION);

        output.addToProofEnd(secondAx);
        output.addToProofEnd(mp);
        output.addToProofEnd(alphaToDeltaI);
    }
}
