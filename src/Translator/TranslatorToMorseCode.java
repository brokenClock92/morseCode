package sample.Translator;

import sample.Base.SymbolsMorse;
import sample.Validation.InputValidation;

public class TranslatorToMorseCode {

    private String yourText;
    private char[] charactersTable;
    private String[] morseCodeTable;
    private InputValidation validation;

    public TranslatorToMorseCode(String yourText) {
        this.yourText = yourText;
        SymbolsMorse symbolsMorse = new SymbolsMorse();
        validation = new InputValidation();
        charactersTable = symbolsMorse.getSymbolsTableLetters();
        morseCodeTable = symbolsMorse.getSymbolsTableMorse();
        setYourText(fromLettersToMorse(getYourText()));
    }

    /* method change letters to Morse code */
    private String fromLettersToMorse(String text){
        text = getYourText();
        String[] codedTable = new String[text.length() * 2];
        int characterIndex = 0;

        if(validation.validateTextToMorseCode(text)){ //this line check if inputted characters aren't prohibited
            for(int i = 0; i < text.length() ; i++){
                for(int j = 0; j < charactersTable.length; j++) {
                    if(String.valueOf(text.charAt(i)).equals(String.valueOf(charactersTable[j]))){
                        codedTable[characterIndex] = morseCodeTable[j];
                        characterIndex++;
                        codedTable[characterIndex] = " ";
                        characterIndex++;
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(String character:codedTable){
                stringBuilder.append(character);
            }
            text = stringBuilder.toString();
        } else {
            text = "Warning!\nYou used prohibited character,\nplease check your sentence.";
        }
        return text;
    }

    public String getYourText(){
        return yourText;
    }

    private void setYourText(String text){
        this.yourText = text;
    }
}
