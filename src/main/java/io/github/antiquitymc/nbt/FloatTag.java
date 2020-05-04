package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Objects;

public final class FloatTag implements Tag<FloatTag> {
    private final float value;

    public FloatTag(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public Tag.Type<FloatTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, Float.floatToIntBits(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatTag floatTag = (FloatTag) o;
        return Float.compare(floatTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(value);
    }

    @Override
    public String toString() {
        return "FloatTag(" + value + ")";
    }

    public enum Type implements Tag.Type<FloatTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return FLOAT;
        }

        @Override
        public FloatTag read(ByteSource source) {
            return new FloatTag(Float.intBitsToFloat(NbtImpl.readInt(source)));
        }

        @Override
        public String toString() {
            return "Float";
        }
    }
}
