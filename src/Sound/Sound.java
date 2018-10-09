package sample.Sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sound {

    private static int sampleRate = 44100;
    private SourceDataLine sourceDataLine;
    private byte[] sineWaveFrequency;

    public Sound(String textMorse) {
        try {
            AudioFormat audioFormat = new AudioFormat(sampleRate, 8, 2, true, true);
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat);
            setSourceDataLine(line);

//            Controller controller = new Controller();
//            TranslatorToMorseCode translator = new TranslatorToMorseCode();
            shortSoundConnector(textMorse);
            sineWaveFrequency = shortSoundConnector(textMorse);
            setSineWave(sineWaveFrequency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] generateSineWave(int durationTime) {
        byte[] sin = new byte[(sampleRate/1000) * durationTime];
        double samplingInterval = (double) (sampleRate / 420 /* frequency */);
        for (int i = 0; i < sin.length; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            sin[i] = (byte) (Math.sin(angle) * 50);
            System.out.println("" + sin[i]);
        }
        return sin;
    }

    private byte[] shortSoundConnector(String textToTranslate){
        List<Byte> fullMelodyList = new ArrayList<>();
        byte[] fullMelodyArray;

        Byte[] singleSoundDot = changeToByteArray(generateSineWave(70));
        Byte[] singleSoundLine = changeToByteArray(generateSineWave(300));
        Byte[] singleSoundPause = changeToByteArray(generateSineWave(250));

        for(int i = 0; i < singleSoundPause.length ;i++)
            singleSoundPause[i] =0;

        for(int i = 0; i < textToTranslate.length(); i++){
            switch (String.valueOf(textToTranslate.charAt(i))) {
                case ".":
                    fullMelodyList.addAll(Arrays.asList(singleSoundDot));
                    fullMelodyList.addAll(Arrays.asList(singleSoundPause));
                    break;
                case "-":
                    fullMelodyList.addAll(Arrays.asList(singleSoundLine));
                    fullMelodyList.addAll(Arrays.asList(singleSoundPause));
                    break;
                case "/":
                    fullMelodyList.addAll(Arrays.asList(singleSoundPause));
                    break;
            }

        }

        fullMelodyArray = new byte[fullMelodyList.size()];
        int i = 0;
        for(byte value : fullMelodyList)
            fullMelodyArray[i++] = value;

        System.out.println(fullMelodyArray.length);
        return fullMelodyArray;
    }

    //  Method that changes byte[] array to Byte[]
    private Byte[] changeToByteArray(byte[] partSoundArray){
        Byte[] partSound = new Byte[partSoundArray.length];
        int i = 0;

        for(byte partValue : partSoundArray)
            partSound[i++] = partValue;

        return partSound;
    }

    public void play(SourceDataLine line, byte[] array) {
        //int length = sampleRate * array.length;
        line.start();
        line.write(array, 0, array.length);
        line.drain();
        line.close();
    }

    private void setSourceDataLine(SourceDataLine line){
        this.sourceDataLine = line;
    }

    public SourceDataLine getSourceDataLine(){
        return this.sourceDataLine;
    }

    private void setSineWave(byte[] sineWaveFreq) {
        this.sineWaveFrequency = sineWaveFreq;
    }

    public byte[] getSineWave() {
        return this.sineWaveFrequency;
    }
}