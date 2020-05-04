package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class ByteArrayTag implements Tag {
    private final byte[] value;

    public ByteArrayTag(byte[] value) {
        this.value = value;
    }

    /**
     * Gets the backing byte array of this tag.
     *
     * @return the backing byte array
     */
    public byte[] getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Standard.BYTE_ARRAY;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);
        for (byte b : value) {
            output.writeByte(b);
        }
    }

    public static ByteArrayTag read(DataInput input) throws IOException {
        int length = input.readInt();
        byte[] value = new byte[length];
        input.readFully(value);

        return new ByteArrayTag(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteArrayTag that = (ByteArrayTag) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
        return "ByteArrayTag" + Arrays.toString(value);
    }
}
