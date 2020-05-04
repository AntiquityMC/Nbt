package io.github.antiquitymc.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class NbtIo {
    private NbtIo() {
    }

    public static CompoundTag read(InputStream in) throws IOException {
        return CompoundTag.read(new DataInputStream(in));
    }

    public static CompoundTag readGzipped(InputStream in) throws IOException {
        return read(new GZIPInputStream(in));
    }

    public static void write(OutputStream out, Tag tag) throws IOException {
        tag.write(new DataOutputStream(out));
    }

    public static void writeGzipped(OutputStream out, Tag tag) throws IOException {
        write(new GZIPOutputStream(out), tag);
    }
}
