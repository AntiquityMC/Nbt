package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;
import io.github.antiquitymc.io.ByteVector;

public final class ByteTag implements Tag<ByteTag> {
    private final byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public Tag.Type<ByteTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        sink.write(value);
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

    public enum Type implements Tag.Type<ByteTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return BYTE;
        }

        @Override
        public ByteTag read(ByteSource source) {
            return new ByteTag(NbtImpl.readByte(source));
        }

        @Override
        public String toString() {
            return "Byte";
        }
    }
}
