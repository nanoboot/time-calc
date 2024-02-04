package org.nanoboot.utils.timecalc.utils.property;

import org.nanoboot.utils.timecalc.app.TimeCalcException;

/**
 * @author Robert
 * @since 23.02.2024
 */
public class ReadOnlyProperty<T> extends Property<T> {
    private Property<T> innerProperty;
    public ReadOnlyProperty(T valueIn) {
        super(valueIn);
        throw new TimeCalcException("This constructor is forbidden in class " + getClass().getName() + ".");
    }
    public ReadOnlyProperty(Property<T> property) {
        super(null);
        this.innerProperty = property;
    }
    public final void setValue(T valueIn) {
        throw new TimeCalcException("This is a read only property. New value cannot be set.");
    }

    public final T getValue(T valueIn) {
        return innerProperty.getValue();
    }
    public final void unBound() {
        throw new TimeCalcException("This is a write only property. Unbounding is forbiden.");
    }
    public final void bindTo(Property<T> anotherProperty) {
        throw new TimeCalcException("This is a write only property. Bounding to another property is forbiden.");
    }

}
