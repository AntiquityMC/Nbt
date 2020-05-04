package io.github.antiquitymc.nbt;

import java.io.DataOutput;
import java.io.IOException;

public interface Tag {
    /**
     * Gets the {@linkplain NbtType type} of this tag.
     *
     * @return the type
     */
    NbtType getType();

    /**
     * Writes this tag to a data output.
     *
     * @param output the data output
     */
    void write(DataOutput output) throws IOException;
}
