package sample.Base;

public class SymbolsMorse {
    private String[] symbolsTableMorse = {
//  [space]   A      B      C       D     E      F      G      H       I      J      K      L
     "//", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
//    M      N     O       P       Q      R      S     T     U      V        W      X      Y       Z
     "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
//    1        2       3       4        5        6         7       8        9        0      [period( . )]   [comma( , )]
    ".----", "..--", "...--", "...-", ".....", "-....", "--...", "---..", "----.", "-----",  ".-.-.-",     "--..--",
//      !         ?
    "-.-.--", "..--.."};

    private char[] symbolsTableLetters = {
    ' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
     'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',', '!', '?'};

    public String[] getSymbolsTableMorse() {
        return symbolsTableMorse;
    }

    public char[] getSymbolsTableLetters(){
        return symbolsTableLetters;
    }
}
