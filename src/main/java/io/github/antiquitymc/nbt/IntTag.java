package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class IntTag implements Tag {
    private final int value;

    public IntTag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Standard.INT;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value);
    }

    public static IntTag read(DataInput input) throws IOException {
        return new IntTag(input.readInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntTag intTag = (IntTag) o;
        return value == intTag.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return "IntTag(" + value + ")";
    }
}
