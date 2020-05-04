package io.github.antiquitymc.io;

/**
 * A readable source of bytes.
 *
 * <p>Inspired by Okio's <a href="https://square.github.io/okio/2.x/okio/okio/-source/index.html">{@code Source}</a>.
 */
public interface ByteSource {
    /**
     * Reads at least 1, and up to {@code n}, bytes and inserts them to the sink.
     *
     * @param sink   the output sink
     * @param n      the maximum number of bytes; must be at least 1
     * @return the number of bytes read, or -1 is there are none available
     */
    int read(ByteSink sink, int n);
}
