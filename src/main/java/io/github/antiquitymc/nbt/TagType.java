package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.IOException;

public enum TagType implements NbtDeserializer {
    END("End", input -> EndTag.INSTANCE),
    BYTE("Byte", ByteTag::read),
    SHORT("Short", ShortTag::read),
    INT("Int", IntTag::read),
    LONG("Long", LongTag::read),
    FLOAT("Float", FloatTag::read),
    DOUBLE("Double", DoubleTag::read),
    BYTE_ARRAY("ByteArray", ByteArrayTag::read),
    STRING("String", StringTag::read),
    LIST("List", ListTag::read),
    COMPOUND("Compound", CompoundTag::read),
    INT_ARRAY("IntArray", IntArrayTag::read),
    LONG_ARRAY("LongArray", LongArrayTag::read),
    ;

    private final byte id;
    private final String name;
    private final NbtDeserializer deserializer;

    TagType(byte id, String name, NbtDeserializer deserializer) {
        this.id = id;
        this.name = name;
        this.deserializer = deserializer;
    }

    TagType(String name, NbtDeserializer deserializer) {
        this.id = (byte) ordinal();
        this.name = name;
        this.deserializer = deserializer;
    }

    public byte getId() {
        return id;
    }

    @Override
    public Tag read(DataInput input) throws IOException {
        return deserializer.read(input);
    }

    @Override
    public String toString() {
        return name;
    }

    public static TagType byId(byte id) {
        if (id < 0) throw new IllegalArgumentException("Negative NBT type: " + id);
        if (id >= values().length) throw new IllegalArgumentException("Unknown NBT type: " + id);

        return values()[id];
    }
}
