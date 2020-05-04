package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class FloatTag implements Tag {
    private final float value;

    public FloatTag(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.FLOAT;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(value);
    }

    public static FloatTag read(DataInput input) throws IOException {
        return new FloatTag(input.readFloat());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatTag floatTag = (FloatTag) o;
        return Float.compare(floatTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(value);
    }

    @Override
    public String toString() {
        return "FloatTag(" + value + ")";
    }
}
