package io.github.antiquitymc.io;

import java.util.Objects;

public final class ByteQueue implements ByteSource, ByteSink {
    private final ByteVector bytes;
    private int position = 0;

    public ByteQueue(byte[] bytes) {
        this(new ByteVector(bytes));
    }

    public ByteQueue(ByteVector bytes) {
        this.bytes = Objects.requireNonNull(bytes, "bytes");
    }

    @Override
    public void write(byte b) {
        bytes.write(b);
    }

    @Override
    public void write(byte[] bytes) {
        this.bytes.write(bytes);
    }

    @Override
    public int read(ByteSink sink, int n) {
        if (position >= bytes.getLength()) return -1;

        int length = Math.min(n, bytes.getLength() - position);
        sink.write(bytes.slice(position, length));
        position += length;
        return length;
    }

    @Override
    public void flush() {
    }
}
