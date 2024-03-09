package org.nanoboot.utils.timecalc.swing.progress;

import org.nanoboot.utils.timecalc.utils.property.BooleanProperty;
import org.nanoboot.utils.timecalc.utils.property.IntegerProperty;
import org.nanoboot.utils.timecalc.utils.property.ReadOnlyProperty;

import java.util.Calendar;
import java.util.Date;

public class Time extends Thread {
    private final IntegerProperty yearReadWriteProperty
            = new IntegerProperty("yearProperty");
    private final IntegerProperty monthReadWriteProperty
            = new IntegerProperty("monthProperty");
    private final IntegerProperty dayReadWriteProperty
            = new IntegerProperty("dayProperty");
    private final IntegerProperty hourReadWriteProperty
            = new IntegerProperty("hourProperty");
    private final IntegerProperty minuteReadWriteProperty
            = new IntegerProperty("minuteProperty");
    private final IntegerProperty secondReadWriteProperty
            = new IntegerProperty("secondProperty");
    private final IntegerProperty millisecondReadWriteProperty
            = new IntegerProperty("millisecondProperty");
    private final IntegerProperty dayOfWeekReadWriteProperty
            = new IntegerProperty("dayOfWeek");
    public ReadOnlyProperty<Integer> yearProperty
            = yearReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> monthProperty
            = monthReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> dayProperty
            = dayReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> hourProperty
            = hourReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> minuteProperty
            = minuteReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> secondProperty
            = secondReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> millisecondProperty
            = millisecondReadWriteProperty.asReadOnlyProperty();
    public ReadOnlyProperty<Integer> dayOfWeek
            = dayOfWeekReadWriteProperty.asReadOnlyProperty();
    public final IntegerProperty yearCustomProperty
            = new IntegerProperty("yearCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty monthCustomProperty
            = new IntegerProperty("monthCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty dayCustomProperty
            = new IntegerProperty("dayCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty hourCustomProperty
            = new IntegerProperty("hourCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty minuteCustomProperty
            = new IntegerProperty("minuteCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty secondCustomProperty
            = new IntegerProperty("secondCustomProperty", Integer.MAX_VALUE);
    public final IntegerProperty millisecondCustomProperty
            = new IntegerProperty("millisecondCustomProperty", Integer.MAX_VALUE);
    public final BooleanProperty allowCustomValuesProperty
            = new BooleanProperty("allowCustomValuesProperty", false);
    //private long lastUpdateNanoTime = 0l;

    public Time() {
        this.setDaemon(true);
        start();
    }

    public Calendar asCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yearProperty.getValue());
        cal.set(Calendar.MONTH, monthProperty.getValue() -1);
        cal.set(Calendar.DAY_OF_MONTH, dayProperty.getValue());
        cal.set(Calendar.HOUR_OF_DAY, hourProperty.getValue());
        cal.set(Calendar.MINUTE, minuteProperty.getValue());
        cal.set(Calendar.SECOND, secondProperty.getValue());
        cal.set(Calendar.MILLISECOND, millisecondProperty.getValue());
        return cal;
    }
    public void run() {

        while (true) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());

            yearReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.YEAR, yearCustomProperty, yearProperty));
            monthReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.MONTH, monthCustomProperty, monthProperty));
            dayReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.DAY_OF_MONTH, dayCustomProperty, dayProperty));
            hourReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.HOUR_OF_DAY, hourCustomProperty, hourProperty));
            minuteReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.MINUTE, minuteCustomProperty, minuteProperty));
            secondReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.SECOND, secondCustomProperty, secondProperty));
            millisecondReadWriteProperty.setValue(returnCustomValueIfNeeded(cal, Calendar.MILLISECOND, millisecondCustomProperty, millisecondProperty));

            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            dayOfWeekReadWriteProperty
                    .setValue(dayOfWeek == 1 ? 7 : dayOfWeek - 1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void writeString() {
        System.out.println(
                yearProperty.getValue() + " "
                + monthProperty.getValue() + " "
                + dayProperty.getValue() + " "
                + hourProperty.getValue() + " "
                + minuteProperty.getValue() + " "
                + secondProperty.getValue() + " "
                + millisecondProperty.getValue() + " "
                + dayOfWeek.getValue() + " "
        );
    }
    private int returnCustomValueIfNeeded(Calendar cal, int timeUnit,
            IntegerProperty custom,
            ReadOnlyProperty<Integer> real) {
        boolean allow = allowCustomValuesProperty.isEnabled();
        Integer customValue = custom.getValue();

        if(allow && customValue != Integer.MAX_VALUE) {
            return custom.getValue();
        } else {
            return timeUnit == Calendar.MONTH ? (cal.get(timeUnit) + 1) : cal.get(timeUnit);
        }

    }
}
