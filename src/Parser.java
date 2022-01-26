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
        return parse_expr();
    }

    public Node parse_expr() {
        Node left = parse_disjunction();
        if ((input[position] == '-') && (input[position + 1] == '>')) {
            position = position + 2;
            Node right = parse_expr();
            return new Node(left, right, null, NodeType.EXPRESSION);
        }
        return left;
    }

    public Node parse_disjunction() {
        int old_position = position;
        Node left = parse_conjunction();
        if (input[position] == '|') {
            position = old_position;
            left = parse_disjunction();
            position++;
            Node right = parse_conjunction();
            return new Node(left, right, null, NodeType.DISJUNCTION);
        }
        return left;
    }

    public Node parse_conjunction() {
        return null;
    }

    public Node parse_denial() {
        return null;
    }

    public Node parse_variable() {
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
}
