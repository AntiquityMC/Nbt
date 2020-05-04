package io.github.antiquitymc.nbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Reads and writes {@linkplain NamedTag named tags} from {@link InputStream} and to {@link OutputStream}.
 */
public final class NbtIo {
    private NbtIo() {
    }

    public static NamedTag read(InputStream in) throws IOException {
        return NamedTag.read(new DataInputStream(in));
    }

    public static NamedTag readGzipped(InputStream in) throws IOException {
        return read(new GZIPInputStream(in));
    }

    public static void write(OutputStream out, NamedTag tag) throws IOException {
        tag.write(new DataOutputStream(out));
    }

    public static void writeGzipped(OutputStream out, NamedTag tag) throws IOException {
        write(new GZIPOutputStream(out), tag);
    }
}
