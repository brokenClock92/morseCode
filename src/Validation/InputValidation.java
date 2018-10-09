package sample.Validation;

import javafx.scene.control.Alert;
import sample.Base.SymbolMorseAndLetters;

public class InputValidation {

    private SymbolMorseAndLetters symbolMorseAndLetters = new SymbolMorseAndLetters();

    public boolean validateTextToMorseCode(String yourText){
        boolean[] characterIsOk = new boolean[yourText.length()];
        boolean result = true;
        for(int i = 0; i < yourText.length(); i++){
            for (char characters : symbolMorseAndLetters.getSymbolsTableLetters()) {
                if (String.valueOf(yourText.charAt(i)).equals(String.valueOf(characters)))
                    characterIsOk[i] = true;
            }
        }

        for(boolean characterIsInTheTable : characterIsOk){
            System.out.println(characterIsInTheTable);
            if(!characterIsInTheTable) {
                result = false;
            }
        }
        if(!result)
            alertDialog();
        return result;
    }

    public void alertDialog(){
        Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
        alertDialog.setTitle("Information");
        alertDialog.setHeaderText(null);
        alertDialog.setContentText("Your input contains prohibited symbol, please check your sentence.");

        alertDialog.showAndWait();
    }

}
