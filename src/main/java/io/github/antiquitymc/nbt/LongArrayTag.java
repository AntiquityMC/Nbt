package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class LongArrayTag implements Tag {
    private final long[] value;

    public LongArrayTag(long[] value) {
        this.value = value;
    }

    /**
     * Gets the backing long array of this tag.
     *
     * @return the backing long array
     */
    public long[] getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.LONG_ARRAY;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);

        for (long l : value) {
            output.writeLong(l);
        }
    }

    public static LongArrayTag read(DataInput input) throws IOException {
        int length = input.readInt();
        long[] value = new long[length];

        for (int i = 0; i < length; i++) {
            value[i] = input.readLong();
        }

        return new LongArrayTag(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongArrayTag that = (LongArrayTag) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
        return "LongArrayTag" + Arrays.toString(value);
    }
}
