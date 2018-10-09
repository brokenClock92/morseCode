package sample.Translator;

import sample.Base.SymbolMorseAndLetters;
import sample.Validation.InputValidation;

public class TranslatorToLettersSentence {

    private String yourText;
    private char[] charactersTable;
    private String[] morseCodeTable;
    private InputValidation validation;
    private boolean labelVisible;

    public TranslatorToLettersSentence(String yourText){
        this.yourText = yourText;
        SymbolMorseAndLetters symbolMorseAndLetters = new SymbolMorseAndLetters();
        validation = new InputValidation();
        charactersTable = symbolMorseAndLetters.getSymbolsTableLetters();
        morseCodeTable = symbolMorseAndLetters.getSymbolsTableMorse();
        setYourText(fromMorseToLetters(getYourText()));
    }

    /* method change Morse code to letters */
    private String fromMorseToLetters(String text) {
        text = getYourText();
        String[] morseCodeSymbol = new String[text.length()];
        String[] decodedTable = new String[text.length()];
        int symbolIndex = 0;

        // filling arrays
        for (int letter = 0; letter < text.length(); letter++) {
            morseCodeSymbol[letter] = "";
            decodedTable[letter] = "";
        }

        for (int i = 0; i < text.length(); i++) {
            //Morse symbols separator
            if ("/".equals(String.valueOf(text.charAt(i))) || " ".equals(String.valueOf(text.charAt(i)))) {
                if((i+1) < text.length()){
                    if ("/".equals(String.valueOf(text.charAt(i + 1)))) {
                        symbolIndex++;
                        morseCodeSymbol[symbolIndex] = "//";
                    } else {
                        symbolIndex++;
                    }
                }
            } else if (".".equals(String.valueOf(text.charAt(i))) || "-".equals(String.valueOf(text.charAt(i)))) {
                if (i == 0)
                    morseCodeSymbol[symbolIndex] = String.valueOf(String.valueOf(text.charAt(i)));
                else
                    morseCodeSymbol[symbolIndex] = String.valueOf(morseCodeSymbol[symbolIndex])
                            + String.valueOf(String.valueOf(text.charAt(i)));
            } else {
                morseCodeSymbol[symbolIndex] = "#";
                symbolIndex++;
            }
        }

        for (int i = 0; i < morseCodeSymbol.length; i++) {
            for (int j = 0; j < morseCodeTable.length; j++) {
                if (morseCodeSymbol[i].equals(String.valueOf(morseCodeTable[j]))) {
                    decodedTable[i] = String.valueOf(charactersTable[j]);
                } else if(morseCodeSymbol[i].equals("#")){
                    decodedTable[i] = String.valueOf(morseCodeSymbol[i]);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String character : decodedTable)
            stringBuilder.append(character);
        text = stringBuilder.toString();
        if(text.contains("#")){
            labelVisible = true;
            validation.alertDialog();
            text = "Warning!\nYou used prohibited character,\nplease check your sentence.";
        } else {
            labelVisible = false;
        }
        setLabelIsVisible(labelVisible);
        return text;
    }

    public String getYourText(){
        return yourText;
    }

    private void setYourText(String text){
        this.yourText = text;
    }

    public boolean getLabelIsVisible(){
        return labelVisible;
    }

    private void setLabelIsVisible(boolean isVisible){
        this.labelVisible = isVisible;
    }
}
