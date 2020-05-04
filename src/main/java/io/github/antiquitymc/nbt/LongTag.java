package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class LongTag implements Tag {
    private final long value;

    public LongTag(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.LONG;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeLong(value);
    }

    public static LongTag read(DataInput input) throws IOException {
        return new LongTag(input.readLong());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongTag longTag = (LongTag) o;
        return value == longTag.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return "LongTag(" + value + ")";
    }
}
