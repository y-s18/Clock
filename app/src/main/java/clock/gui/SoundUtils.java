package clock.gui;

import javax.sound.sampled.*;


public class SoundUtils {

    public static float SAMPLE_RATE = 8000f;
    private static byte[] buf;
    private static AudioFormat af;

    private static void configureTone(int hz, int msecs)
            throws LineUnavailableException {
        configureTone(hz, msecs, 1.0);
    }

    private static void configureTone(int hz, int msecs, double vol)
            throws LineUnavailableException {
        buf = new byte[1];
        af = new AudioFormat(
                SAMPLE_RATE, // sampleRate
                8, // sampleSizeInBits
                1, // channels
                true, // signed
                false); // bigEndian
        configureSourceDataLine(hz, msecs, vol);        
        
    }

    private static void configureSourceDataLine(int hz, int msecs, double vol) throws LineUnavailableException{
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i = 0; i < msecs * 8; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    public static void playSound() throws Exception{
        SoundUtils.configureTone(1000, 100);
    }
}