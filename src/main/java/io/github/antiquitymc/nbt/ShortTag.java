package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

public final class ShortTag implements Tag<ShortTag> {
    private final short value;

    public ShortTag(short value) {
        this.value = value;
    }

    public ShortTag(int value) {
        this((short) value);
    }

    public short getValue() {
        return value;
    }

    @Override
    public Tag.Type<ShortTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, value);
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

    public enum Type implements Tag.Type<ShortTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return SHORT;
        }

        @Override
        public ShortTag read(ByteSource source) {
            return new ShortTag(NbtImpl.readShort(source));
        }

        @Override
        public String toString() {
            return "Short";
        }
    }
}
