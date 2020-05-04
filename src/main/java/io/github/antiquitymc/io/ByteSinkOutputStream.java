package io.github.antiquitymc.io;

import java.io.OutputStream;
import java.util.Objects;

public final class ByteSinkOutputStream extends OutputStream {
    private final ByteSink sink;

    public ByteSinkOutputStream(ByteSink sink) {
        this.sink = Objects.requireNonNull(sink, "sink");
    }

    @Override
    public void write(int b) {
        sink.write((byte) b);
    }

    @Override
    public void flush() {
        sink.flush();
    }
}
