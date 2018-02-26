package com.epam.textanalizator.composite;

public class Lexeme extends TextComposite {

	private LexemeType lexemeType;

    public Lexeme() {
        super.setComponentType(ComponentType.LEXEME);
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public void setLexemeType(LexemeType lexemeType) {
        this.lexemeType = lexemeType;
    }

    @Override
    public void setComponentType(ComponentType componentType) {
        throw new UnsupportedOperationException("Unsupported operation detected.");
    }
}
