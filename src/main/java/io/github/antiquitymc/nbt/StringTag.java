package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Objects;

public final class StringTag implements Tag<StringTag> {
    private static final short MAX_LENGTH = Short.MAX_VALUE;
    private final String value;

    public StringTag(final String value) {
        this.value = Objects.requireNonNull(value, "value");

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The maximum length of strings is " + MAX_LENGTH + ", found " + value.length());
        }
    }

    @Override
    public Tag.Type<StringTag> getType() {
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
        StringTag stringTag = (StringTag) o;
        return value.equals(stringTag.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "StringTag(" + value + ")";
    }

    public enum Type implements Tag.Type<StringTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return STRING;
        }

        @Override
        public StringTag read(ByteSource source) {
            return new StringTag(NbtImpl.readString(source));
        }

        @Override
        public String toString() {
            return "String";
        }
    }
}
