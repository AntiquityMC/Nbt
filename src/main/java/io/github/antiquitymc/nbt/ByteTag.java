package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class ByteTag implements Tag {
    private final byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.BYTE;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeByte(value);
    }

    public static ByteTag read(DataInput input) throws IOException {
        return new ByteTag(input.readByte());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteTag byteTag = (ByteTag) o;
        return value == byteTag.value;
    }

    @Override
    public int hashCode() {
        return Byte.hashCode(value);
    }

    @Override
    public String toString() {
        return "ByteTag(" + value + ")";
    }
}
