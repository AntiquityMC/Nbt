package io.github.antiquitymc.nbt;

import com.google.common.collect.ImmutableMap;
import io.github.antiquitymc.io.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

final class NbtImpl {
    static final Map<Byte, Tag.Type<?>> TAG_TYPES = ImmutableMap.<Byte, Tag.Type<?>>builder()
            .put(Tag.Type.END, EndTag.Type.INSTANCE)
            .put(Tag.Type.BYTE, ByteTag.Type.INSTANCE)
            .put(Tag.Type.SHORT, ShortTag.Type.INSTANCE)
            .put(Tag.Type.INT, IntTag.Type.INSTANCE)
            .put(Tag.Type.LONG, LongTag.Type.INSTANCE)
            .put(Tag.Type.FLOAT, FloatTag.Type.INSTANCE)
            .put(Tag.Type.DOUBLE, DoubleTag.Type.INSTANCE)
            .put(Tag.Type.BYTE_ARRAY, ByteArrayTag.Type.INSTANCE)
            .put(Tag.Type.STRING, StringTag.Type.INSTANCE)
            .put(Tag.Type.LIST, ListTag.Type.instance())
            .put(Tag.Type.COMPOUND, CompoundTag.Type.INSTANCE)
            .put(Tag.Type.INT_ARRAY, IntArrayTag.Type.INSTANCE)
            .put(Tag.Type.LONG_ARRAY, LongArrayTag.Type.INSTANCE)
            .build();

    private NbtImpl() {}

    static void forceRead(ByteSource source, ByteSink sink, int length) {
        for (int i = 0; i < length; i++) {
            int result = source.read(sink, 1);

            if (result == -1) {
                throw new IllegalArgumentException("Could not read " + length + " bytes from source!");
            }
        }
    }

    static byte readByte(ByteSource source) {
        ByteVector vec = new ByteVector(1);
        forceRead(source, vec, 1);
        return vec.get(0);
    }

    static short readShort(ByteSource source) {
        ByteVector vec = new ByteVector(2);
        forceRead(source, vec, 2);
        return (short) ((vec.get(0) << 8) | vec.get(1));
    }

    static int readInt(ByteSource source) {
        ByteVector vec = new ByteVector(4);
        forceRead(source, vec, 4);
        return (vec.get(0) << 24) | (vec.get(1) << 16) | (vec.get(2) << 8) | vec.get(3);
    }

    static long readLong(ByteSource source) {
        ByteVector vec = new ByteVector(8);
        forceRead(source, vec, 8);
        long first = (vec.get(0) << 24) | (vec.get(1) << 16) | (vec.get(2) << 8) | vec.get(3);
        long second = (vec.get(4) << 24) | (vec.get(5) << 16) | (vec.get(6) << 8) | vec.get(7);

        return (first << 32) | second;
    }

    static String readString(ByteSource source) {
        try {
            return new DataInputStream(new ByteSourceInputStream(source)).readUTF();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static Tag.Type<?> readTagType(ByteSource source) {
        ByteVector vec = new ByteVector(1);
        forceRead(source, vec, 1);

        return getTagType(vec.get(0));
    }

    static Tag.Type<?> getTagType(byte id) {
        Tag.Type<?> type = TAG_TYPES.get(id);
        if (type == null) {
            throw new IllegalStateException("Unknown tag type: " + id);
        }

        return type;
    }

    static void write(ByteSink sink, short s) {
        sink.write((byte) ((s >> 8) & 0xFF));
        sink.write((byte) (s & 0xFF));
    }

    static void write(ByteSink sink, int i) {
        sink.write((byte) ((i >> 24) & 0xFF));
        sink.write((byte) ((i >> 16) & 0xFF));
        sink.write((byte) ((i >> 8) & 0xFF));
        sink.write((byte) (i & 0xFF));
    }

    static void write(ByteSink sink, long l) {
        sink.write((byte) ((l >> 56) & 0xFF));
        sink.write((byte) ((l >> 48) & 0xFF));
        sink.write((byte) ((l >> 40) & 0xFF));
        sink.write((byte) ((l >> 32) & 0xFF));
        sink.write((byte) ((l >> 24) & 0xFF));
        sink.write((byte) ((l >> 16) & 0xFF));
        sink.write((byte) ((l >> 8) & 0xFF));
        sink.write((byte) (l & 0xFF));
    }

    static void write(ByteSink sink, String str) {
        DataOutputStream stream = new DataOutputStream(new ByteSinkOutputStream(sink));
        try {
            stream.writeUTF(str);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
