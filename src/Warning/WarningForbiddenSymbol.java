package sample.Warning;

public class WarningForbiddenSymbol {

    public String warningMakerToMorse(char[] contentWarningTable){
        StringBuilder stringBuilder = new StringBuilder();
        String symbolString;
        String[] stringTab = new String[contentWarningTable.length];
        for(int i = 0; i < contentWarningTable.length; i++){
            symbolString = String.valueOf(contentWarningTable[i]);
            if(i == 0)
                stringTab[i] = "[ \"{space}\", ";
            else if(i == contentWarningTable.length-1)
                stringTab[i] = "\"" + symbolString + "\" ]";
            else
                stringTab[i] = "\"" + symbolString + "\", ";
        }
        for(String character : stringTab)
            stringBuilder.append(character);
        return String.valueOf(stringBuilder);
    }

    public String warningMakerToText(String[] contentWarningTable){
        StringBuilder stringBuilder = new StringBuilder();
        String[] stringTab = new String[contentWarningTable.length];
        for(int i = 0; i < contentWarningTable.length; i++){
            stringTab[i] = String.valueOf(contentWarningTable[i]);
            if(i == 0)
                stringTab[i] = "[ \"" + contentWarningTable[i] +"\", ";
            else if(i == contentWarningTable.length-1)
                stringTab[i] = "\"" + contentWarningTable[i] + "\" ].";
            else
                stringTab[i] = "\"" + contentWarningTable[i] + "\", ";
        }
        for(String character : stringTab)
            stringBuilder.append(character);
        return String.valueOf(stringBuilder);
    }
}
