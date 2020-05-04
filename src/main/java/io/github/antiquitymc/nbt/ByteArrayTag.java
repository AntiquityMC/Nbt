package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;
import io.github.antiquitymc.io.ByteVector;

import java.util.Arrays;

public final class ByteArrayTag implements Tag<ByteArrayTag> {
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
    public Tag.Type<ByteArrayTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, value.length);
        sink.write(value);
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

    public enum Type implements Tag.Type<ByteArrayTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return BYTE_ARRAY;
        }

        @Override
        public ByteArrayTag read(ByteSource source) {
            int length = NbtImpl.readInt(source);
            ByteVector vec = new ByteVector(length);
            NbtImpl.forceRead(source, vec, length);

            return new ByteArrayTag(vec.toArray());
        }

        @Override
        public String toString() {
            return "ByteArray";
        }
    }
}
