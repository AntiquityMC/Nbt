package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.IOException;

@FunctionalInterface
public interface NbtDeserializer {
    Tag read(DataInput input) throws IOException;
}
