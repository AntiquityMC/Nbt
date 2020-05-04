package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSource;
import io.github.antiquitymc.io.ByteSink;

public enum EndTag implements Tag<EndTag> {
    INSTANCE;

    @Override
    public Tag.Type<EndTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
    }

    @Override
    public String toString() {
        return "End";
    }

    public enum Type implements Tag.Type<EndTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return END;
        }

        @Override
        public EndTag read(ByteSource source) {
            return EndTag.INSTANCE;
        }

        @Override
        public String toString() {
            return "End";
        }
    }
}
