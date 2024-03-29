package org.nanoboot.utils.timecalc.swing.progress;

/**
 * @author Robert Vokac
 * @since 21.02.2024
 */
public class MinuteBattery extends Battery {

    public static final String MINUTE = "Minute";

    public MinuteBattery(int x, int i, int i1) {
        super(MINUTE, x, i, i1);
    }

    public static double getMinuteProgress(int secondNow, int millisecondNow) {
        return millisecondNow / 60d / 1000d + secondNow / 60d;
    }
}
