package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Arrays;

public final class LongArrayTag implements Tag<LongArrayTag> {
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
    public Tag.Type<LongArrayTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, value.length);

        for (long l : value) {
            NbtImpl.write(sink, l);
        }
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

    public enum Type implements Tag.Type<LongArrayTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return LONG_ARRAY;
        }

        @Override
        public LongArrayTag read(ByteSource source) {
            int length = NbtImpl.readInt(source);
            long[] value = new long[length];

            for (int i = 0; i < length; i++) {
                value[i] = NbtImpl.readLong(source);
            }

            return new LongArrayTag(value);
        }

        @Override
        public String toString() {
            return "LongArray";
        }
    }
}
