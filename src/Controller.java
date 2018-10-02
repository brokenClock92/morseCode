package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import sample.Translator.TranslatorToLettersSentence;
import sample.Translator.TranslatorToMorseCode;

public class Controller {
    @FXML private TextArea textBoxRight;
    @FXML private TextArea textBoxLeft;
    @FXML public Button toMorseCode, toLetters;
    private String textFromTextBoxLeft;

    public void initialize(){
        //edit TextArea(right) disabled
        textBoxRight.setEditable(false);

        toMorseCode.setOnAction(event -> {
            setTextFromTextBoxLeft(textBoxLeft.getText());
            TranslatorToMorseCode translatorToMorseCode = new TranslatorToMorseCode(getTextFromTextBoxLeft());
            textBoxRight.setText(translatorToMorseCode.getYourText());
        });

        toLetters.setOnAction(event -> {
            setTextFromTextBoxLeft(textBoxLeft.getText());
            TranslatorToLettersSentence translatorToLettersSentence = new TranslatorToLettersSentence(getTextFromTextBoxLeft());
            textBoxRight.setText(translatorToLettersSentence.getYourText());
        });
    }

    private void setTextFromTextBoxLeft(String textFromTextBoxLeft) {
        this.textFromTextBoxLeft = textFromTextBoxLeft;
    }

    private String getTextFromTextBoxLeft() {
        return textFromTextBoxLeft;
    }
}
