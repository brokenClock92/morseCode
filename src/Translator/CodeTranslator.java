package sample.Translator;

import sample.Base.SymbolsMorse;
import sample.Controller;
import sample.Validation.InputValidation;

public class CodeTranslator {

    private String yourText;
    private char[] charactersTable;
    private String[] morseCodeTable;
    private InputValidation validation;

    public CodeTranslator(String givenText) {
        Controller controller = new Controller();
        SymbolsMorse symbolsMorse = new SymbolsMorse();
        validation = new InputValidation();
        charactersTable = symbolsMorse.getSymbolsTableLetters();
        morseCodeTable = symbolsMorse.getSymbolsTableMorse();

        this.yourText = givenText;
        if(controller.toMorseButton)
            setYourText(fromMorseToLetters(givenText));
        else
            setYourText(fromLettersToMorse(givenText));
    }

    /* method change Morse code to letters */
    private String fromMorseToLetters(String text) {
        String[] morseCodeSymbol = new String[text.length()];
        String[] decodedTable = new String[text.length()];
        int symbolIndex = 0;

        for (int letter = 0; letter < text.length(); letter++) {
            morseCodeSymbol[letter] = "";
            decodedTable[letter] = "";
        }

        for (int i = 0; i < text.length(); i++) {
            //Morse symbols separator
            if ("/".equals(String.valueOf(text.charAt(i)))) {
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
                    morseCodeSymbol[symbolIndex] = String.valueOf(morseCodeSymbol[symbolIndex]) + String.valueOf(String.valueOf(text.charAt(i)));
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
            validation.alertDialog();
        }

        return text;
    }

    /* method change letters to Morse code */
    private String fromLettersToMorse(String text){
        String[] codedTable = new String[text.length()];

        if(validation.validateTextToMorseCode(text)){ //this line check if inputted characters are'nt prohibited
            for(int i = 0; i < text.length(); i++){
                for(int j = 0; j < charactersTable.length; j++) {
                    if(String.valueOf(text.charAt(i)).equals(String.valueOf(charactersTable[j])))
                        codedTable[i] = morseCodeTable[j] + "/";
                }
                if(codedTable[i].equals("///"))
                    codedTable[i] = "/";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(String character:codedTable){
                stringBuilder.append(character);
            }
            text = stringBuilder.toString();
        } else {
            text = "WARNING\nYou used prohibited character,\nplease check your sentence.";
        }
        return text;
    }

    public String getYourText(){
        return yourText;
    }

    private void setYourText(String yourText) {
        this.yourText = yourText;
    }
}
