package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.Base.SymbolMorseAndLetters;
import sample.Sound.Sound;
import sample.Translator.TranslatorToLettersSentence;
import sample.Translator.TranslatorToMorseCode;
import sample.Warning.WarningForbiddenSymbol;

public class Controller {
    @FXML private TextArea textBoxRight, textBoxLeft;
    @FXML public Button toMorseCode, toLetters, soundMorseCode;
    @FXML public Label labelWarning, contentWarning;

    private String textFromTextLeftWindow, textFromRightWindow;
    private SymbolMorseAndLetters symbolMorseAndLetters = new SymbolMorseAndLetters();
    private WarningForbiddenSymbol warningForbiddenSymbol = new WarningForbiddenSymbol();

    public void initialize(){
        // TextArea on right is disabled
        textBoxRight.setEditable(false);

        toMorseCode.setOnAction(event -> {
            setTextFromTextLeftWindow(textBoxLeft.getText());
            TranslatorToMorseCode translatorToMorseCode = new TranslatorToMorseCode(getTextFromTextLeftWindow());
            textBoxRight.setText(translatorToMorseCode.getYourText());

            // condition checks if user input include prohibited symbol
            if(translatorToMorseCode.getLabelIsVisible()){
                labelWarning.setVisible(translatorToMorseCode.getLabelIsVisible());
                contentWarning.setText("you can use only symbols like: "
                        + warningForbiddenSymbol.warningMakerToMorse(symbolMorseAndLetters.getSymbolsTableLetters()));
                contentWarning.setVisible(translatorToMorseCode.getLabelIsVisible());
            }else{
                labelWarning.setVisible(translatorToMorseCode.getLabelIsVisible());
                contentWarning.setVisible(translatorToMorseCode.getLabelIsVisible());
            }

            setTextFromRightWindow(textBoxRight.getText());
        });

        // button translate Morse code to Letters
        toLetters.setOnAction(event -> {
            setTextFromTextLeftWindow(textBoxLeft.getText());
            TranslatorToLettersSentence translatorToLettersSentence = new TranslatorToLettersSentence(getTextFromTextLeftWindow());
            textBoxRight.setText(translatorToLettersSentence.getYourText());

            // condition checks if user input include prohibited symbol
            if(translatorToLettersSentence.getLabelIsVisible()){
                labelWarning.setVisible(translatorToLettersSentence.getLabelIsVisible());
                contentWarning.setText("you can use only symbols like: "
                        + warningForbiddenSymbol.warningMakerToText(symbolMorseAndLetters.getSymbolsTableMorse()));
                contentWarning.setVisible(translatorToLettersSentence.getLabelIsVisible());
            }else{
                labelWarning.setVisible(translatorToLettersSentence.getLabelIsVisible());
                contentWarning.setVisible(translatorToLettersSentence.getLabelIsVisible());
            }
        });

        // button translate Letters to Morse code
        soundMorseCode.setOnAction(event -> {
            setTextFromRightWindow(textBoxRight.getText());
            Sound sound = new Sound(getTextFromRightWindow());
            sound.play(sound.getSourceDataLine(), sound.getSineWave());
        });
    }

    private void setTextFromTextLeftWindow(String textFromTextLeftWindow) {
        this.textFromTextLeftWindow = textFromTextLeftWindow;
    }

    private String getTextFromTextLeftWindow() {
        return textFromTextLeftWindow;
    }

    private void setTextFromRightWindow(String textFromRightWindow){
        this.textFromRightWindow = textFromRightWindow;
    }

    private String getTextFromRightWindow(){
        return this.textFromRightWindow;
    }
}
