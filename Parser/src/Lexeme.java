public class Lexeme {
    private Types.LexemeType lexemeType;
    private String lexemeText;

    public Lexeme(Types.LexemeType lexemeType, String lexemeText){
        this.lexemeType = lexemeType;
        this.lexemeText = lexemeText;
    }

    public Types.LexemeType getLexemeType(){
        return this.lexemeType;
    }

    public String getLexemeText(){
        return this.lexemeText;
    }
}

