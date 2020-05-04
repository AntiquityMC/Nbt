package io.github.antiquitymc.nbt;

import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.Objects;

public final class DoubleTag implements Tag<DoubleTag> {
    private final double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public Tag.Type<DoubleTag> getType() {
        return Type.INSTANCE;
    }

    @Override
    public void write(ByteSink sink) {
        NbtImpl.write(sink, Double.doubleToLongBits(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleTag doubleTag = (DoubleTag) o;
        return Double.compare(doubleTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return "DoubleTag(" + value + ")";
    }

    public enum Type implements Tag.Type<DoubleTag> {
        INSTANCE;

        @Override
        public byte getId() {
            return DOUBLE;
        }

        @Override
        public DoubleTag read(ByteSource source) {
            return new DoubleTag(Double.longBitsToDouble(NbtImpl.readLong(source)));
        }

        @Override
        public String toString() {
            return "Double";
        }
    }
}
