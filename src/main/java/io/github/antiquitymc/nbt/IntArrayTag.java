package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public final class IntArrayTag implements Tag {
    private final int[] value;

    public IntArrayTag(int[] value) {
        this.value = value;
    }

    /**
     * Gets the backing int array of this tag.
     *
     * @return the backing int array
     */
    public int[] getValue() {
        return value;
    }

    @Override
    public NbtType getType() {
        return NbtType.INT_ARRAY;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(value.length);

        for (int i : value) {
            output.writeInt(i);
        }
    }

    public static IntArrayTag read(DataInput input) throws IOException {
        int length = input.readInt();
        int[] value = new int[length];

        for (int i = 0; i < length; i++) {
            value[i] = input.readInt();
        }

        return new IntArrayTag(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArrayTag that = (IntArrayTag) o;
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public String toString() {
        return "IntArrayTag" + Arrays.toString(value);
    }
}
