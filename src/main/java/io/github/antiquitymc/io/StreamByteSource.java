package io.github.antiquitymc.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Objects;

public final class StreamByteSource implements ByteSource {
    private final InputStream in;

    public StreamByteSource(InputStream in) {
        this.in = Objects.requireNonNull(in, "in");
    }

    @Override
    public int read(ByteSink sink, int n) {
        try {
            byte[] bytes = new byte[n];
            int result = in.read(bytes, 0, n);

            if (result == -1) {
                return result;
            }

            sink.write(bytes);
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
