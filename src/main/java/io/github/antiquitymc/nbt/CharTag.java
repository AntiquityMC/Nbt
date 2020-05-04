package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class CharTag implements Tag {
    private final char value;

    public CharTag(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public TagType getType() {
        return TagType.Antiquity.CHAR;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeChar(value);
    }

    public static CharTag read(DataInput input) throws IOException {
        return new CharTag(input.readChar());
    }

    static CharTag fromShort(ShortTag tag) {
        short s = tag.getValue();
        int upper = (s >> 8) & 0xFF;
        int lower = s & 0xFF;

        return new CharTag((char) ((upper << 8) | lower));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharTag charTag = (CharTag) o;
        return value == charTag.value;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }

    @Override
    public String toString() {
        return "Char(" + value + ")";
    }
}
