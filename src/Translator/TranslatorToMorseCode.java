package sample.Translator;

import sample.Base.SymbolMorseAndLetters;
import sample.Validation.InputValidation;

public class TranslatorToMorseCode {

    private String yourText;
    private char[] charactersTable;
    private String[] morseCodeTable;
    private InputValidation validation;
    private boolean labelVisible;

    public TranslatorToMorseCode() {

    }

    public TranslatorToMorseCode(String yourText) {
        this.yourText = yourText;
        SymbolMorseAndLetters symbolMorseAndLetters = new SymbolMorseAndLetters();
        validation = new InputValidation();
        charactersTable = symbolMorseAndLetters.getSymbolsTableLetters();
        morseCodeTable = symbolMorseAndLetters.getSymbolsTableMorse();
        setYourText(fromLettersToMorse());
    }

    /* method change letters to Morse code */
    private String fromLettersToMorse(){
        String text = getYourText();
        String[] codedTable = new String[text.length() * 2];
        int characterIndex = 0;

        if(validation.validateTextToMorseCode(text)){ //this line check if inputted characters aren't prohibited
            for(int i = 0; i < text.length() ; i++){
                for(int j = 0; j < charactersTable.length; j++) {
                    if(String.valueOf(text.charAt(i)).equals(String.valueOf(charactersTable[j]))) {
                        if (!(String.valueOf(text.charAt(i)).equals(" "))) {
                            codedTable[characterIndex] = morseCodeTable[j];
                            characterIndex++;
                            codedTable[characterIndex] = "/";
                            characterIndex++;
                            break;
                        } else if(String.valueOf(text.charAt(i)).equals(" ")){
                            codedTable[characterIndex-1] = "//";
                            characterIndex++;
                            break;
                        }
                    }
                }
            }

            StringBuilder stringBuilder = new StringBuilder();
            for(String character:codedTable){
                if(character != null)
                    stringBuilder.append(character);
            }
            text = stringBuilder.toString();
            labelVisible = false;
        } else {
            labelVisible = true;
            text = "Warning!\nYou used prohibited character,\nplease check your sentence.";
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
