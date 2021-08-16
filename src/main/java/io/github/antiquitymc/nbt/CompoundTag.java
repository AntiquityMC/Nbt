package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class CompoundTag implements Tag, Map<String, Tag> {
    private final Map<String, Tag> tags;

    /**
     * Constructs a mutable compound tag.
     */
    public CompoundTag() {
        this(new HashMap<>());
    }

    /**
     * Constructs a compound tag that uses the passed map as its backing map.
     *
     * <p>All {@link Map} operations will be delegated to the passed map.
     *
     * @param tags the backing map
     */
    public CompoundTag(Map<String, Tag> tags) {
        this.tags = Objects.requireNonNull(tags, "tags");
    }

    @Override
    public TagType getType() {
        return TagType.Standard.COMPOUND;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        for (Entry<String, Tag> entry : tags.entrySet()) {
            new NamedTag(entry.getKey(), entry.getValue()).write(output);
        }

        output.writeByte(TagType.Standard.END.getId());
    }

    public static CompoundTag read(DataInput input) throws IOException {
        HashMap<String, Tag> map = new HashMap<>();
        byte typeId;

        while ((typeId = input.readByte()) != TagType.Standard.END.getId()) {
            NamedTag tag = NamedTag.read(typeId, input);
            map.put(tag.getName(), tag.getTag());
        }

        return new CompoundTag(map);
    }

    /**
     * Gets a float from this compound.
     *
     * @param key the key
     * @return the float value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public float getFloat(String key) {
        if (containsKey(key)) {
            return ((FloatTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a float from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the float value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public float getFloat(String key, float defaultValue) {
        if (containsKey(key)) {
            return ((FloatTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a double from this compound.
     *
     * @param key the key
     * @return the double value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public double getDouble(String key) {
        if (containsKey(key)) {
            return ((DoubleTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a double from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the double value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public double getDouble(String key, double defaultValue) {
        if (containsKey(key)) {
            return ((DoubleTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a byte from this compound.
     *
     * @param key the key
     * @return the byte value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public byte getByte(String key) {
        if (containsKey(key)) {
            return ((ByteTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a byte from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the byte value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public byte getByte(String key, byte defaultValue) {
        if (containsKey(key)) {
            return ((ByteTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a short from this compound.
     *
     * @param key the key
     * @return the short value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public short getShort(String key) {
        if (containsKey(key)) {
            return ((ShortTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a short from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the short value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public short getShort(String key, short defaultValue) {
        if (containsKey(key)) {
            return ((ShortTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets an int from this compound.
     *
     * @param key the key
     * @return the int value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public int getInt(String key) {
        if (containsKey(key)) {
            return ((IntTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets an int from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the int value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public int getInt(String key, int defaultValue) {
        if (containsKey(key)) {
            return ((IntTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a long from this compound.
     *
     * @param key the key
     * @return the long value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with a different type
     */
    public long getLong(String key) {
        if (containsKey(key)) {
            return ((LongTag) get(key)).getValue();
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a long from this compound.
     * If missing, returns the default value.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the long value of the key, or the default if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public long getLong(String key, long defaultValue) {
        if (containsKey(key)) {
            return ((LongTag) get(key)).getValue();
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a character from this compound.
     *
     * <p>If the tag represented by the key is a short,
     * it is converted to a char using unsigned casting.
     *
     * @param key the key
     * @return the character value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with an incompatible type
     */
    public char getChar(String key) {
        if (containsKey(key)) {
            Tag tag = get(key);
            if (tag instanceof CharTag) {
                return ((CharTag) tag).getValue();
            } else if (tag instanceof ShortTag) {
                return CharTag.fromShort((ShortTag) tag).getValue();
            } else {
                throw new ClassCastException("Incompatible type: " + tag.getType().getName());
            }
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a character from this compound.
     * If missing, returns the default value.
     *
     * <p>If the tag represented by the key is a short,
     * it is converted to a char using unsigned casting.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the character value of the key, or the default if not found
     * @throws ClassCastException if the key is present with an incompatible type
     */
    public char getChar(String key, char defaultValue) {
        if (containsKey(key)) {
            Tag tag = get(key);
            if (tag instanceof CharTag) {
                return ((CharTag) tag).getValue();
            } else if (tag instanceof ShortTag) {
                return CharTag.fromShort((ShortTag) tag).getValue();
            } else {
                throw new ClassCastException("Incompatible type: " + tag.getType().getName());
            }
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a boolean from this compound.
     *
     * <p>If the tag represented by the key is a byte,
     * it is converted to a boolean.
     *
     * @param key the key
     * @return the boolean value of the key
     * @throws NoSuchElementException if the key is not found
     * @throws ClassCastException     if the key is present with an incompatible type
     */
    public boolean getBoolean(String key) {
        if (containsKey(key)) {
            Tag tag = get(key);
            if (tag instanceof BooleanTag) {
                return ((BooleanTag) tag).getValue();
            } else if (tag instanceof ByteTag) {
                return ((ByteTag) tag).getValue() != 0;
            } else {
                throw new ClassCastException("Incompatible type: " + tag.getType().getName());
            }
        } else {
            throw new NoSuchElementException(key);
        }
    }

    /**
     * Gets a boolean from this compound.
     * If missing, returns the default value.
     *
     * <p>If the tag represented by the key is a byte,
     * it is converted to a boolean.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean value of the key, or the default if not found
     * @throws ClassCastException if the key is present with an incompatible type
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        if (containsKey(key)) {
            Tag tag = get(key);
            if (tag instanceof BooleanTag) {
                return ((BooleanTag) tag).getValue();
            } else if (tag instanceof ByteTag) {
                return ((ByteTag) tag).getValue() != 0;
            } else {
                throw new ClassCastException("Incompatible type: " + tag.getType().getName());
            }
        } else {
            return defaultValue;
        }
    }

    /**
     * Gets a string from this compound.
     *
     * @param key the key
     * @return the string value, or null if not found
     * @throws ClassCastException if the key is present with an incompatible type
     */
    public /* TODO: @Nullable */ String getString(String key) {
        return containsKey(key) ? ((StringTag) get(key)).getValue() : null;
    }

    /**
     * Gets a nested compound tag from this compound.
     *
     * @param key the key
     * @return the nested compound associated with the key, or null if not found
     * @throws ClassCastException if the key is present with a different type
     */
    public CompoundTag getSubTag(String key) {
        return (CompoundTag) get(key);
    }

    /**
     * Gets or create a nested compound tag from this compound.
     * If the tag is missing from this compound, creates a new compound tag and inserts it.
     *
     * @param key the key
     * @return the nested compound associated with the key
     * @throws ClassCastException if the key is present with a different type
     */
    public CompoundTag getOrCreateSubTag(String key) {
        return (CompoundTag) computeIfAbsent(key, it -> new CompoundTag());
    }

    /**
     * Gets the tag represented by the key.
     *
     * <p>If the value at the key is a standard key that is compatible
     * with the specified type, it is converted.
     *
     * @param key  the key
     * @param type the wanted type
     * @return the tag represented by the key or a converted version of it, or null if not found
     * @throws ClassCastException if the type is incompatible
     */
    public Tag get(String key, TagType type) {
        if (containsKey(key)) {
            Tag tag = get(key);

            if (tag.getType() == type) {
                return tag;
            } else if (tag.getType() == type.getStandardEquivalent()) {
                return type.fromStandardEquivalent(tag);
            } else {
                throw new ClassCastException("Incompatible type: " + tag.getType().getName());
            }
        } else {
            return null;
        }
    }

    public void putFloat(String key, float value) {
        put(key, new FloatTag(value));
    }

    public void putDouble(String key, double value) {
        put(key, new DoubleTag(value));
    }

    public void putByte(String key, byte value) {
        put(key, new ByteTag(value));
    }

    public void putShort(String key, short value) {
        put(key, new ShortTag(value));
    }

    public void putInt(String key, int value) {
        put(key, new IntTag(value));
    }

    public void putLong(String key, long value) {
        put(key, new LongTag(value));
    }

    public void putString(String key, String value) {
        put(key, new StringTag(value));
    }

    public void putList(String key, List<? extends Tag> list) {
        put(key, new ListTag<>(list));
    }

    public void putBoolean(String key, boolean value) {
        put(key, new BooleanTag(value));
    }

    public void putChar(String key, char value) {
        put(key, new CharTag(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompoundTag that = (CompoundTag) o;
        return tags.equals(that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags);
    }

    @Override
    public String toString() {
        return "CompoundTag{" + tags.entrySet().stream().map(entry -> entry.getKey() + " = " + entry.getValue()).collect(Collectors.joining(", ")) + "}";
    }

    ////////////////////////////
    //   Map implementation   //
    ////////////////////////////

    //@Nullable
    @Override
    public Tag put(String key, Tag value) {
        return tags.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    @Override
    public void putAll(Map<? extends String, ? extends Tag> m) {
        Objects.requireNonNull(m, "map");

        for (Entry<? extends String, ? extends Tag> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public int size() {
        return tags.size();
    }

    @Override
    public boolean isEmpty() {
        return tags.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return tags.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return tags.containsValue(value);
    }

    @Override
    public Tag get(Object key) {
        return tags.get(key);
    }

    @Override
    public Tag remove(Object key) {
        return tags.remove(key);
    }

    @Override
    public void clear() {
        tags.clear();
    }

    @Override
    public Set<String> keySet() {
        return tags.keySet();
    }

    @Override
    public Collection<Tag> values() {
        return tags.values();
    }

    @Override
    public Set<Entry<String, Tag>> entrySet() {
        return tags.entrySet();
    }
}
