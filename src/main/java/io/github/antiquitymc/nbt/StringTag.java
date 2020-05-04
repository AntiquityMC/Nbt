package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public final class StringTag implements Tag {
    private static final short MAX_LENGTH = Short.MAX_VALUE;
    private final String value;

    public StringTag(final String value) {
        this.value = Objects.requireNonNull(value, "value");

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The maximum length of strings is " + MAX_LENGTH + ", found " + value.length());
        }
    }

    @Override
    public NbtType getType() {
        return NbtType.STRING;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeUTF(value);
    }

    public static StringTag read(DataInput input) throws IOException {
        return new StringTag(input.readUTF());
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
}
