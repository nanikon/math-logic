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

    public Parser(char[] input) {
        this.input = input;
    }

    public Node parse() {
        this.position = 0;
        return parseExpr();
    }

    public Node parseExpr() {
        Node left = parseDisjunction();
        if ((input[position] == '-') && (input[position + 1] == '>')) {
            position = position + 2;
            Node right = parseExpr();
            return new Node(left, right, null, NodeType.EXPRESSION);
        }
        return left;
    }

    public Node parseDisjunction() {
        int old_position = position;
        Node left = parseConjunction();
        if (input[position] == '|') {
            position = old_position;
            left = parseDisjunction();
            position++;
            Node right = parseConjunction();
            return new Node(left, right, null, NodeType.DISJUNCTION);
        }
        return left;
    }

    public Node parseConjunction() {
        if (input[position] == '!') {
            position++;
            return parseDenial();
        }
        Node left = parseConjunction();
        position++;
        Node right = parseDenial();
        return new Node(left, right, null, NodeType.CONJUNCTION);
    }

    public Node parseDenial() {
        Node right;
        if (input[position] == '!'){
            position++;
            right = parseDenial();
        } else if (input[position] == '(') {
            position++;
            right = parseExpr();
        } else {
            right = parseVariable();
            position++;
        }
        return new Node(null, right, null, NodeType.DENIAL);
    }

    public Node parseVariable() {
        StringBuilder result = new StringBuilder();
        while (canBeVariableName(input[position])) {
            result.append(input[position]);
            position++;
        }
        return new Node(null, null, result.toString(), NodeType.VARIABLE);
    }

    private boolean canBeVariableName(char c) {
        return ((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || (c == '’');
    }

    private void skipProbels() {}
}
