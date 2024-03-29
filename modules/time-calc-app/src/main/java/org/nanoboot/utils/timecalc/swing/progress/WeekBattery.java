package org.nanoboot.utils.timecalc.swing.progress;

/**
 * @author Robert Vokac
 * @since 21.02.2024
 */
public class WeekBattery extends Battery {

    public static final String WEEK = "Week";

    public WeekBattery(int x, int i, int i1) {
        super(WEEK, x, i, i1);
    }

    public static double getWeekProgress(int weekDayWhenMondayIsOne,
            double done) {
        if (done > 1) {
            done = 1;
        }
        return weekDayWhenMondayIsOne == 0
                || weekDayWhenMondayIsOne == 6
                        ? 100 : ((weekDayWhenMondayIsOne - 1) * 0.20 + done * 0.20);
    }
}
