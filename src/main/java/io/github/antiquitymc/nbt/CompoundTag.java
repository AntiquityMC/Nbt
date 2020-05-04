package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
        return TagType.COMPOUND;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        for (Entry<String, Tag> entry : tags.entrySet()) {
            new NamedTag(entry.getKey(), entry.getValue()).write(output);
        }

        output.writeByte(TagType.END.getId());
    }

    public static CompoundTag read(DataInput input) throws IOException {
        HashMap<String, Tag> map = new HashMap<>();
        byte typeId;

        while ((typeId = input.readByte()) != TagType.END.getId()) {
            NamedTag tag = NamedTag.read(typeId, input);
            map.put(tag.getName(), tag.getTag());
        }

        return new CompoundTag(map);
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
