package io.github.antiquitymc.nbt;

import java.io.DataOutput;
import java.io.IOException;

public enum EndTag implements Tag {
    INSTANCE;

    @Override
    public TagType getType() {
        return TagType.Standard.END;
    }

    @Override
    public void write(DataOutput output) throws IOException {
    }

    @Override
    public String toString() {
        return "End";
    }
}
