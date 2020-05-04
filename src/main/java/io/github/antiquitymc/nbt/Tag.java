package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

public interface Tag<T extends Tag<T>> {
    /**
     * Gets the {@linkplain Type type} of this tag.
     *
     * @return the type
     */
    Type<T> getType();

    /**
     * Writes this tag to the byte sink.
     *
     * @param sink the byte sink
     */
    void write(ByteSink sink);

    interface Type<T extends Tag<T>> {
        byte END = 0;
        byte BYTE = 1;
        byte SHORT = 2;
        byte INT = 3;
        byte LONG = 4;
        byte FLOAT = 5;
        byte DOUBLE = 6;
        byte BYTE_ARRAY = 7;
        byte STRING = 8;
        byte LIST = 9;
        byte COMPOUND = 10;
        byte INT_ARRAY = 11;
        byte LONG_ARRAY = 12;

        /**
         * Gets the ID of this tag type.
         *
         * @return the ID
         */
        byte getId();

        /**
         * Reads an immutable tag from a byte source.
         *
         * @param source the byte source
         * @return the read tag
         */
        T read(ByteSource source);
    }
}
