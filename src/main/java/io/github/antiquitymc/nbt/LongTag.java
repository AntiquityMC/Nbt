package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSource;
import io.github.antiquitymc.io.ByteSink;

public final class LongTag implements Tag<LongTag> {
    private final long value;

    public LongTag(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public Tag.Type<LongTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongTag longTag = (LongTag) o;
        return value == longTag.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return "LongTag(" + value + ")";
    }

    public enum Type implements Tag.Type<LongTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return LONG;
        }

        @Override
        public LongTag read(ByteSource source) {
            return new LongTag(NbtImpl.readLong(source));
        }

        @Override
        public String toString() {
            return "Long";
        }
    }
}
