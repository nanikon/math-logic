/**
 * @author Natalia Nikonova
 */
public class AxiomChecker {
    public boolean checkFirst(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getRight().getType().equals(NodeType.EXPRESSION)
                && node.getLeft().equals(node.getRight().getRight());
    }

    public boolean checkSecond(Node node) {
        if (!node.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node left1 = node.getLeft();
        Node right1 = node.getRight();
        if (!left1.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right1.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node alpha = left1.getLeft();
        Node beta = left1.getRight();
        Node left2 = right1.getLeft();
        Node right2 = right1.getRight();
        if (!left2.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right2.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node maybeAlpha1 = left2.getLeft();
        Node right3 = left2.getRight();
        if (!right3.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node maybeBeta = right3.getLeft();
        Node gamma = right3.getRight();
        Node maybeAlpha2 = right2.getLeft();
        Node maybeGamma= right2.getRight();
        return maybeAlpha1.equals(alpha) && maybeAlpha2.equals(alpha) && maybeBeta.equals(beta) && maybeGamma.equals(gamma);
    }

    public boolean checkThird(Node node) {
        if (!node.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node alpha = node.getLeft();
        Node right1 = node.getRight();
        if (!right1.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node beta = right1.getLeft();
        Node right2 = right1.getRight();
        if (!right2.getType().equals(NodeType.CONJUNCTION)) { return false; }
        return alpha.equals(right2.getLeft()) && beta.equals(right2.getRight());
    }

    public boolean checkFourth(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getLeft().getType().equals(NodeType.CONJUNCTION)
                && node.getRight().equals(node.getLeft().getLeft());
    }

    public boolean checkFifth(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getLeft().getType().equals(NodeType.CONJUNCTION)
                && node.getRight().equals(node.getLeft().getRight());
    }

    public boolean checkSixth(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getRight().getType().equals(NodeType.DISJUNCTION)
                && node.getLeft().equals(node.getRight().getLeft());
    }

    public boolean checkSeventh(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getRight().getType().equals(NodeType.DISJUNCTION)
                && node.getLeft().equals(node.getRight().getRight());
    }

    public boolean checkEighth(Node node) {
        if (!node.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node left1 = node.getLeft();
        Node right1 = node.getRight();
        if (!left1.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right1.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node alpha = left1.getLeft();
        Node gamma = left1.getRight();
        Node left2 = right1.getLeft();
        Node right2 = right1.getRight();
        if (!left2.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right2.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node beta = left2.getLeft();
        Node maybeGamma1 = left2.getRight();
        Node maybeGamma2 = right2.getRight();
        if (!right2.getLeft().getType().equals(NodeType.DISJUNCTION)) { return false; }
        Node maybeAlpha = right2.getLeft().getLeft();
        Node maybeBeta = right2.getLeft().getRight();
        return maybeAlpha.equals(alpha) && maybeBeta.equals(beta) && maybeGamma1.equals(gamma) && maybeGamma2.equals(gamma);
    }

    public boolean checkNinth(Node node) {
        if (!node.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node left1 = node.getLeft();
        Node right1 = node.getRight();
        if (!left1.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right1.getType().equals(NodeType.EXPRESSION)) { return false; }
        Node alpha = left1.getLeft();
        Node beta = left1.getRight();
        Node left2 = right1.getLeft();
        Node right2 = right1.getRight();
        if (!left2.getType().equals(NodeType.EXPRESSION)) { return false; }
        if (!right2.getType().equals(NodeType.DENIAL)) { return false; }
        Node maybeAlpha1 = right2.getRight();
        Node maybeAlpha2 = left2.getLeft();
        Node right3 = left2.getRight();
        if (!right3.getType().equals(NodeType.DENIAL)) { return false; }
        Node maybeBeta = right3.getRight();
        return maybeAlpha1.equals(alpha) && maybeAlpha2.equals(alpha) && maybeBeta.equals(beta);
    }

    public boolean checkTenth(Node node) {
        return node.getType().equals(NodeType.EXPRESSION)
                && node.getLeft().getType().equals(NodeType.DENIAL)
                && node.getLeft().getRight().getType().equals(NodeType.DENIAL)
                && node.getLeft().getRight().getRight().equals(node.getRight());
    }

    public boolean checkAllAxiom(Node node) {
        return checkFirst(node)
                || checkSecond(node)
                || checkThird(node)
                || checkFourth(node)
                || checkFifth(node)
                || checkSixth(node)
                || checkSeventh(node)
                || checkEighth(node)
                || checkNinth(node)
                || checkTenth(node);
    }
}
