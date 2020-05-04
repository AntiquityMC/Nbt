package io.github.antiquitymc.io;

import java.util.Arrays;
import java.util.Objects;

/**
 * Growable vector of bytes.
 */
public final class ByteVector implements ByteSink {
    private byte[] bytes;
    private int length = 0;

    /**
     * Creates a byte vector as a copy of a byte array.
     *
     * <p>The specified byte array will not be modified.
     *
     * @param bytes the byte array
     */
    public ByteVector(byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes");
        this.bytes = Arrays.copyOf(bytes, bytes.length);
        this.length = bytes.length;
    }

    /**
     * Creates an empty byte vector with a specified initial capacity.
     *
     * @param capacity the capacity
     */
    public ByteVector(int capacity) {
        this.bytes = new byte[capacity];
    }

    /**
     * Creates an empty byte vector with an initial capacity of 32 bytes.
     */
    public ByteVector() {
        this(32);
    }

    public byte get(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds! Length: " + length);
        }

        return bytes[index];
    }

    public ByteVector insert(byte b) {
        growTo(length + 1);
        bytes[length] = b;
        length++;

        return this;
    }

    public ByteVector insert(byte[] bytes) {
        growTo(length + bytes.length);
        System.arraycopy(bytes, 0, this.bytes, length, bytes.length);
        length += bytes.length;
        return this;
    }

    public ByteVector set(int index, byte b) {
        growTo(index + 1);
        bytes[index] = b;
        return this;
    }

    public void growBy(int amount) {
        bytes = Arrays.copyOf(bytes, bytes.length + amount);
    }

    public void growTo(int newCapacity) {
        if (newCapacity <= bytes.length) {
            return;
        }

        bytes = Arrays.copyOf(bytes, newCapacity);
    }

    public int getCapacity() {
        return bytes.length;
    }

    public int getLength() {
        return length;
    }

    /**
     * Creates a slice from this vector.
     *
     * @param start  the starting index, inclusive
     * @param length the slice length
     * @return the slice
     * @throws IndexOutOfBoundsException if the indices are out of range
     */
    public byte[] slice(int start, int length) {
        byte[] slice = new byte[length];
        System.arraycopy(bytes, start, slice, 0, length);
        return slice;
    }

    public byte[] toArray() {
        return Arrays.copyOf(bytes, length);
    }

    /**
     * Clears this byte vector. Does not modify the capacity.
     */
    public void clear() {
        length = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteVector that = (ByteVector) o;
        return length == that.length && Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(length);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }

    @Override
    public void flush() {
    }

    @Override
    public void write(byte b) {
        insert(b);
    }

    @Override
    public void write(byte[] bytes) {
        insert(bytes);
    }
}
