package io.github.antiquitymc.nbt;

import java.io.DataOutput;
import java.io.IOException;

public interface Tag {
    /**
     * Gets the {@link TagType} of this tag.
     *
     * @return the type
     */
    TagType getType();

    /**
     * Writes this tag to a data output.
     *
     * @param output the data output
     */
    void write(DataOutput output) throws IOException;
}
