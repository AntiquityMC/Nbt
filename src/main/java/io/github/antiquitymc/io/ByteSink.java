package io.github.antiquitymc.io;

/**
 * A byte output where bytes can be written.
 *
 * <p>Inspired by Okio's <a href="https://square.github.io/okio/2.x/okio/okio/-sink/index.html">{@code Sink}</a>.
 */
public interface ByteSink {
    void write(byte b);
    void write(byte[] bytes);
    void flush();
}
