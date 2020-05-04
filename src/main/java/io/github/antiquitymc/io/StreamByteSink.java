package io.github.antiquitymc.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;

public final class StreamByteSink implements ByteSink {
    private final OutputStream out;

    public StreamByteSink(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(byte b) {
        try {
            out.write(b);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void write(byte[] bytes) {
        try {
            out.write(bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void flush() {
        try {
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
