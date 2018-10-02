package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Translator.CodeTranslator;

public class Controller {
    @FXML private TextArea textBoxRight;
    @FXML private TextArea textBoxLeft;
    @FXML public Button toMorseCode, toLetters;
    private String textFromTextBoxLeft;
    public boolean toMorseButton;

    public Controller(){

    }

    public void initialize(){
        //edit TextArea(right) disabled
        textBoxRight.setEditable(false);

        toMorseCode.setOnAction(event -> {
            toMorseButton = true;
            buttonClickInstructions();
        });
        toLetters.setOnAction(event -> {
            toMorseButton = false;
            buttonClickInstructions();
        });
    }

    private void buttonClickInstructions(){
        textFromTextBoxLeft = textBoxLeft.getText();
        setTextTextBoxLeft(textFromTextBoxLeft);
        CodeTranslator codeTranslator = new CodeTranslator(getTextFromTextBoxLeft());
        textBoxRight.setText(codeTranslator.getYourText());
    }

    private void setTextTextBoxLeft(String textFromTextBoxLeft) {
        this.textFromTextBoxLeft = textFromTextBoxLeft;
    }

    private String getTextFromTextBoxLeft() {
        return textFromTextBoxLeft;
    }
}
