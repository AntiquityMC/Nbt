package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Objects;

public final class IntTag implements Tag<IntTag> {
    private final int value;

    public IntTag(int value) {
        this.value = value;
    }

    @Override
    public Tag.Type<IntTag> getType() {
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
        IntTag intTag = (IntTag) o;
        return value == intTag.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return "IntTag(" + value + ")";
    }

    public enum Type implements Tag.Type<IntTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return INT;
        }

        @Override
        public IntTag read(ByteSource source) {
            return new IntTag(NbtImpl.readInt(source));
        }

        @Override
        public String toString() {
            return "Int";
        }
    }
}
