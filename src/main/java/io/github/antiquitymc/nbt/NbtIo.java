package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.StreamByteSink;
import io.github.antiquitymc.io.StreamByteSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class NbtIo {
    private NbtIo() {
    }

    public static CompoundTag read(InputStream in) throws UncheckedIOException {
        return CompoundTag.Type.INSTANCE.read(new StreamByteSource(in));
    }

    public static CompoundTag readGzipped(InputStream in) throws IOException {
        return read(new GZIPInputStream(in));
    }

    public static void write(Tag<?> tag, OutputStream out) throws UncheckedIOException {
        tag.write(new StreamByteSink(out));
    }

    public static void writeGzipped(Tag<?> tag, OutputStream out) throws IOException {
        write(tag, new GZIPOutputStream(out));
    }
}
