package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class DoubleTag implements Tag {
    private final double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Standard.DOUBLE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeDouble(value);
    }

    public static DoubleTag read(DataInput input) throws IOException {
        return new DoubleTag(input.readDouble());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleTag doubleTag = (DoubleTag) o;
        return Double.compare(doubleTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return "DoubleTag(" + value + ")";
    }
}
