import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void calculateTest1() throws Exception {

        Reader str = new StringReader("2+3^2");
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);

        assertEquals(11, parser.calculate());
    }

    @Test
    public void calculateTest2() throws Exception {

        Reader str = new StringReader("-2+3*2");
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);

        assertEquals(4, parser.calculate());
    }

    @Test(expected = WrongExpression.class)
    public void calculateTest3() throws Exception {

        Reader str = new StringReader("-2+3*2))");
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);
        parser.calculate();
    }

    @Test(expected = WrongExpression.class)
    public void calculateTest4() throws Exception {

        Reader str = new StringReader("(-2+3*2");
        Lexer lexer = new Lexer(str);
        Parser parser = new Parser(lexer);
        parser.calculate();
    }
}