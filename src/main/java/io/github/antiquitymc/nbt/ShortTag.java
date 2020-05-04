package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class ShortTag implements Tag {
    private final short value;

    public ShortTag(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Standard.SHORT;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeShort(value);
    }

    public static ShortTag read(DataInput input) throws IOException {
        return new ShortTag(input.readShort());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShortTag shortTag = (ShortTag) o;
        return value == shortTag.value;
    }

    @Override
    public int hashCode() {
        return Short.hashCode(value);
    }

    @Override
    public String toString() {
        return "ShortTag(" + value + ")";
    }
}
