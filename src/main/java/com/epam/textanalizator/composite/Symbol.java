package com.epam.textanalizator.composite;

public class Symbol implements TextComponent {

	private String content;
    private SymbolType symbolType;

    public Symbol(String symbol) {
        this.content = symbol;
    }

    public Symbol(String content, SymbolType symbolType) {
        this.content = content;
        this.symbolType = symbolType;
    }

    public String getContent() {
        return content;
    }

    public ComponentType getComponentType() {
        return ComponentType.SYMBOL;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        if (symbolType == null) {
            throw new IllegalArgumentException("Incorrect symbol type detected.");
        }

        this.symbolType = symbolType;
    }
}
