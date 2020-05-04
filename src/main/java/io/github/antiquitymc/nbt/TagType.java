package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Function;

public interface TagType extends NbtDeserializer {
    String getName();

    Standard getStandardEquivalent();

    Tag fromStandardEquivalent(Tag standardTag);

    enum Standard implements TagType {
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

        Standard(String name, NbtDeserializer deserializer) {
            this.id = (byte) ordinal();
            this.name = name;
            this.deserializer = deserializer;
        }

        public byte getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Standard getStandardEquivalent() {
            return this;
        }

        @Override
        public Tag fromStandardEquivalent(Tag standardTag) {
            return standardTag;
        }

        @Override
        public Tag read(DataInput input) throws IOException {
            return deserializer.read(input);
        }

        @Override
        public String toString() {
            return name;
        }

        public static Standard byId(byte id) {
            if (id < 0) throw new IllegalArgumentException("Negative NBT type: " + id);
            if (id >= Standard.values().length) throw new IllegalArgumentException("Unknown NBT type: " + id);

            return Standard.values()[id];
        }
    }

    enum Antiquity implements TagType {
        BOOLEAN("Boolean", BooleanTag::read, TagType.Standard.BYTE, BooleanTag::fromByte),
        CHAR("Char", CharTag::read, TagType.Standard.SHORT, CharTag::fromShort),
        ;

        private final String name;
        private final NbtDeserializer deserializer;
        private final Standard equivalent;
        private final Function<? extends Tag, ? extends Tag> converter;

        <E extends Tag> Antiquity(String name, NbtDeserializer deserializer, TagType.Standard equivalent, Function<E, ? extends Tag> converter) {
            this.name = name;
            this.deserializer = deserializer;
            this.equivalent = equivalent;
            this.converter = converter;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public TagType.Standard getStandardEquivalent() {
            return equivalent;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Tag fromStandardEquivalent(Tag standardTag) {
            return ((Function<Tag, Tag>) converter).apply(standardTag);
        }

        @Override
        public Tag read(DataInput input) throws IOException {
            return deserializer.read(input);
        }
    }
}
