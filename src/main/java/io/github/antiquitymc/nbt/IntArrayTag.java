package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Arrays;

public final class IntArrayTag implements Tag<IntArrayTag> {
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
    public Tag.Type<IntArrayTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, value.length);

        for (int i : value) {
            NbtImpl.write(sink, i);
        }
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

    public enum Type implements Tag.Type<IntArrayTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return INT_ARRAY;
        }

        @Override
        public IntArrayTag read(ByteSource source) {
            int length = NbtImpl.readInt(source);
            int[] value = new int[length];

            for (int i = 0; i < length; i++) {
                value[i] = NbtImpl.readInt(source);
            }

            return new IntArrayTag(value);
        }

        @Override
        public String toString() {
            return "IntArray";
        }
    }
}
