/**
 * @author Natalia Nikonova
 */
public class Parser {
    // в корне всегда импликация,
    // из неё в дизъюнкцию, и потом может опять импликация
    // из неё в конъюнкцию (но потом может понадобится пересчитать на дизъюнкцию и потом конъюнкцию),
    // в ней первый знак смотрим, и либо отрицание, либо обратно конъюнкция
    // в отрицании если ! - отрицание, скобочка открывающаяся - импликация, буква или число - переменная
    int position;
    char[] input;
    int length;

    public Parser(char[] input) {
        this.input = input;
        this.length = input.length;
    }

    public Node parse() {
        this.position = 0;
        return parseExpr();
    }

    private boolean notEnd() {
        return position < length;
    }

    public Node parseExpr() {
        Node left = parseDisjunction(parseConjunction(parseDenial()));
        if (notEnd() && (input[position] == '-') && (input[position + 1] == '>')) {
            position = position + 2;
            Node right = parseExpr();
            return new Node(left, right, null, NodeType.EXPRESSION);
        }
        return left;
    }

    public Node parseDisjunction(Node left) {
        if (!notEnd()) { return left; }
        if (input[position] != '|') { return left; }
        position++;
        Node right = parseConjunction(parseDenial());
        return parseDisjunction(new Node(left, right, null, NodeType.DISJUNCTION));
    }

    public Node parseConjunction(Node left) {
        if (!notEnd()) { return left; }
        if (input[position] != '&') { return left; }
        position++;
        Node right = parseDenial();
        return parseConjunction(new Node(left, right, null, NodeType.CONJUNCTION));
    }

    public Node parseDenial() {
        Node right;
        if (notEnd() && input[position] == '!'){
            position++;
            right = new Node(null, parseDenial(), null, NodeType.DENIAL);
        } else if (notEnd() && input[position] == '(') {
            position++;
            right = parseExpr();
            position++;
        } else {
            right = parseVariable();
        }
        return right;
    }

    public Node parseVariable() {
        StringBuilder result = new StringBuilder();
        while (notEnd() && canBeVariableName(input[position])) {
            result.append(input[position]);
            position++;
        }
        return new Node(null, null, result.toString(), NodeType.VARIABLE);
    }

    private boolean canBeVariableName(char c) {
        return ((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || (c == 39);
    }
}
