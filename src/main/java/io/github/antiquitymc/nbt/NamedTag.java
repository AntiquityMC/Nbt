package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public final class NamedTag {
    private final String name;
    private final Tag tag;

    public NamedTag(String name, Tag tag) {
        this.name = Objects.requireNonNull(name, "name");
        this.tag = Objects.requireNonNull(tag, "tag");

        if (tag.getType().getId() == TagType.END.getId()) {
            throw new IllegalArgumentException("End tags cannot be named!");
        }
    }

    public String getName() {
        return name;
    }

    public Tag getTag() {
        return tag;
    }

    public void write(DataOutput output) throws IOException {
        output.writeByte(tag.getType().getId());
        output.writeUTF(name);
        tag.write(output);
    }

    public static NamedTag read(DataInput input) throws IOException {
        byte typeId = input.readByte();
        return read(typeId, input);
    }

    public static NamedTag read(byte typeId, DataInput input) throws IOException {
        TagType type = TagType.byId(typeId);
        String name = input.readUTF();
        Tag tag = type.read(input);
        return new NamedTag(name, tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedTag namedTag = (NamedTag) o;
        return name.equals(namedTag.name) &&
                tag.equals(namedTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag);
    }

    @Override
    public String toString() {
        return '"' + name + "\": " + tag;
    }
}
