import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/Data.txt");

        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        try{
        int result = parser.calculate();
            System.out.println(result);
        }
        catch (WrongExpression e) {
            System.out.println(e.getMessage());
        }

    }
}