package io.github.antiquitymc.io;

import java.io.InputStream;

public final class ByteSourceInputStream extends InputStream {
    private final ByteSource source;
    private final ByteVector vec = new ByteVector(1);

    public ByteSourceInputStream(ByteSource source) {
        this.source = source;
    }

    @Override
    public int read() {
        synchronized (vec) {
            vec.clear();
            int result = source.read(vec, 1);
            return result == -1 ? -1 : vec.get(0);
        }
    }
}
