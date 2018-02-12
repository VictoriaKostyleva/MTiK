import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private Lexeme currentLexeme;
//    private Boolean flag = false;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public int calculate() throws IOException, WrongExpression {
        int result = parseExpression();

//        System.out.println(currentLexeme.getLexemeType());

        if(currentLexeme.getLexemeType() != Types.LexemeType.EOF) {
            throw new WrongExpression("Too many right brunches");
        }

        return result;
    }

    public int parseExpression() throws IOException, WrongExpression {
        int term = parseTerm();

        while ((currentLexeme.getLexemeType() == Types.LexemeType.PLUS) || (currentLexeme.getLexemeType() == Types.LexemeType.MINUS)) {
            if (currentLexeme.getLexemeType() == Types.LexemeType.PLUS) {
                term += parseTerm();
            } else {
                term -= parseTerm();
            }
        }
        currentLexeme = lexer.getLexeme();
        return term;
    }

    private int parseTerm() throws IOException, WrongExpression {
        int temp = parseFactor();

        while ((currentLexeme.getLexemeType() == Types.LexemeType.MULTI) || (currentLexeme.getLexemeType() == Types.LexemeType.DIVIDE)) {
            if (currentLexeme.getLexemeType() == Types.LexemeType.MULTI) {
                temp *= parseTerm();
            } else {
                temp /= parseTerm();
            }
        }
        return temp;
    }

    private int parseFactor() throws IOException, WrongExpression {
        int power = parsePower();

        if (currentLexeme.getLexemeType() == Types.LexemeType.POWER) {
            int factor = parseFactor();
//            currentLexeme = lexer.getLexeme();
            return (int) Math.pow(power, factor);
        } else {
            return power;
        }
    }

    private int parsePower() throws IOException, WrongExpression {
        currentLexeme = lexer.getLexeme();

        if (currentLexeme.getLexemeType() == Types.LexemeType.MINUS) {
            return -1 * parseAtom();
        } else {
            return parseAtom();
        }
    }

    private int parseAtom() throws IOException, WrongExpression {
        if (currentLexeme.getLexemeType() == Types.LexemeType.MINUS) {
            Lexeme lexeme = lexer.getLexeme();
            if (lexeme.getLexemeType() == Types.LexemeType.LEFT_BR) {

                int res = parseExpression();

                if(currentLexeme.getLexemeType() != Types.LexemeType.RIGHT_BR) {
                    throw new WrongExpression("No right brunch");
                } else {
                    return res;
                }

            } else {
                if (lexeme.getLexemeType() == Types.LexemeType.NUMBER) {
                    currentLexeme = lexer.getLexeme();
                    return Integer.parseInt(lexeme.getLexemeText());
                }
            }

        } else {
            if (currentLexeme.getLexemeType() == Types.LexemeType.LEFT_BR) {

                int res = parseExpression();

                if(currentLexeme.getLexemeType() != Types.LexemeType.RIGHT_BR) {
                    throw new WrongExpression("No right brunch");
                } else {
                    return res;
                }

            } else {
                if (currentLexeme.getLexemeType() == Types.LexemeType.NUMBER) {
                    Lexeme lexeme = currentLexeme;
                    currentLexeme = lexer.getLexeme();
                    return Integer.parseInt(lexeme.getLexemeText());
                }
                else {
                    throw new WrongExpression("Wrong expression");
                }
            }
        }
        return 0;
    }
}

class WrongExpression extends Exception {
    public WrongExpression(String message) {
        super(message);
    }
}
