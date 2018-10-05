package sample.Sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class Sound {

    private static int sampleRate = 44100;
    private SourceDataLine sourceDataLine;
    private byte[] sineWaveFrequency;

    public Sound(int frequencyOfSignal, int seconds) {
        try {
            AudioFormat audioFormat = new AudioFormat(sampleRate, 16, 2, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat);
            line.start();
            setSourceDataLine(line);
            setSineWave(generateSineWaveFreq(frequencyOfSignal, seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] generateSineWaveFreq(int frequencyOfSignal, int mSeconds) {
        // total samples = (duration in second) * (samples per second)
        byte[] sin = new byte[(int)sampleRate * mSeconds/1000];
        double samplingInterval = (double) (sampleRate / frequencyOfSignal);
//        System.out.println("Sampling Frequency  : "+sampleRate);
//        System.out.println("Frequency of Signal : "+frequencyOfSignal);
//        System.out.println("Sampling Interval   : "+samplingInterval);
        for (int i = 0; i < sin.length; i++) {
            double angle = (2.0 * Math.PI * i) / samplingInterval;
            sin[i] = (byte) (Math.sin(angle) * 127);
            System.out.println("" + sin[i]);
        }
        return sin;
    }

    public void play(SourceDataLine line, byte[] array) {
        //int length = sampleRate * array.length;
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