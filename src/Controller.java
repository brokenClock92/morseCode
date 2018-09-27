package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Translator.CodeTranslator;

public class Controller {
    @FXML private TextArea textBoxRight;
    @FXML private TextArea textBoxLeft;
    private String textFromTextBoxLeft;
    @FXML public Button translateButton;
    private CodeTranslator codeTranslator;

    public Controller(){

    }

    public void initialize(){
            translateButton.setOnAction(event -> {
                textFromTextBoxLeft = textBoxLeft.getText();
                setTextTextBoxLeft(textFromTextBoxLeft);
                codeTranslator = new CodeTranslator(getTextFromTextBoxLeft());
                textBoxRight.setText(codeTranslator.getYourText());
            });
    }

    private void setTextTextBoxLeft(String textFromTextBoxLeft) {
        this.textFromTextBoxLeft = textFromTextBoxLeft;
    }

    private String getTextFromTextBoxLeft() {
        return textFromTextBoxLeft;
    }
}
