package sample.Translator;

import sample.Base.SymbolsMorse;

public class CodeTranslator {

    private String yourText;
    private char[] charactersTable;
    private String[] morseCodeTable;

    public CodeTranslator(String givenText) {
        SymbolsMorse symbolsMorse = new SymbolsMorse();
        charactersTable = symbolsMorse.getSymbolsTableLetters();
        morseCodeTable = symbolsMorse.getSymbolsTableMorse();
        this.yourText = givenText;
        setYourText(codeText(givenText));
    }

    private String codeText(String text){
        String[] codedTable = new String[text.length()];

        for(int i = 0; i < text.length(); i++){
            for(int j = 0; j < charactersTable.length; j++) {
                if (String.valueOf(text.charAt(i)).equals(String.valueOf(charactersTable[j])))
                    codedTable[i] = morseCodeTable[j];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String character:codedTable)
            stringBuilder.append(character);
        text = stringBuilder.toString();

        return text;
    }

    public String getYourText(){
        return yourText;
    }

    private void setYourText(String yourText) {
        this.yourText = yourText;
    }
}
