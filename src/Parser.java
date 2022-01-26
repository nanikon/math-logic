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
        return null;
    }

    public Node parse_disjunction() {
        return null;
    }

    public Node parse_conjunction() {
        return null;
    }

    public Node parse_denial() {
        return null;
    }

    public Node parse_variable() {
        StringBuilder result = new StringBuilder();
        while (isDigitOrBigLetter(input[position])) {
            result.append(input[position]);
            position++;
        }
        return new Node(null, null, result.toString(), NodeType.VARIABLE);
    }

    private boolean isDigitOrBigLetter(char c) {
        return ((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z'));
    }
}
