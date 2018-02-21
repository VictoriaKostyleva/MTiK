import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int lastSymbol = -1;

    public Lexer(Reader reader){
        this.reader = reader;
    }

    public Lexeme getLexeme() throws IOException {
        char c;
        boolean isNumber = false;
        StringBuffer buffer = new StringBuffer();

        if(lastSymbol == -1){
            c = (char)reader.read();

            while((c >= '0')&&(c <= '9')){
                isNumber = true;
                buffer.append(c);
                c = (char)reader.read();
            }

            if(isNumber){
                lastSymbol = (int)c;
                return new Lexeme(Types.LexemeType.NUMBER, buffer.toString());
            }

            //not a number
            buffer.append(c);

            switch (c){
                case '+':
                    return new Lexeme(Types.LexemeType.PLUS, buffer.toString());
                case '-':
                    return new Lexeme(Types.LexemeType.MINUS, buffer.toString());
                case '*':
                    return new Lexeme(Types.LexemeType.MULTI, buffer.toString());
                case '/':
                    return new Lexeme(Types.LexemeType.DIVIDE, buffer.toString());
                case '(':
                    return new Lexeme(Types.LexemeType.LEFT_BR, buffer.toString());
                case ')':
                    return new Lexeme(Types.LexemeType.RIGHT_BR, buffer.toString());
                case '^':
                    return new Lexeme(Types.LexemeType.POWER, buffer.toString());
                default:
                    return new Lexeme(Types.LexemeType.EOF,"ERROR");
            }
        } else {
            buffer.append((char)lastSymbol);

            switch((char)lastSymbol){
                case '+':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.PLUS, buffer.toString());
                case '-':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.MINUS, buffer.toString());
                case '*':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.MULTI, buffer.toString());
                case '/':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.DIVIDE, buffer.toString());
                case '(':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.LEFT_BR, buffer.toString());
                case ')':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.RIGHT_BR, buffer.toString());
                case '^':
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.POWER, buffer.toString());
                default:
                    lastSymbol = -1;
                    return new Lexeme(Types.LexemeType.EOF,"ERROR");
            }
        }
    }
}
