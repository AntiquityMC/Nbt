package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public final class BooleanTag implements Tag {
    private final boolean value;

    public BooleanTag(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Antiquity.BOOLEAN;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeBoolean(value);
    }

    public static BooleanTag read(DataInput input) throws IOException {
        return new BooleanTag(input.readBoolean());
    }

    static BooleanTag fromByte(ByteTag tag) {
        return new BooleanTag(tag.getValue() != 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanTag that = (BooleanTag) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }

    @Override
    public String toString() {
        return "Boolean(" + value + ")";
    }
}
