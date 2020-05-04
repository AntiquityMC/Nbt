package io.github.antiquitymc.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ListTag<T extends Tag> implements Tag, List<T> {
    private final TagType elementType;
    private final List<T> tags;

    /**
     * Constructs a mutable list tag.
     */
    public ListTag(TagType elementType) {
        this(elementType, new ArrayList<>());
    }

    /**
     * Constructs a list tag that uses the passed list as its backing list.
     *
     * <p>All {@link List} operations will be delegated to the passed list.
     * 
     * @param tags the backing list
     */
    public ListTag(List<T> tags) {
        this(getTagType(Objects.requireNonNull(tags, "tags")), tags);
    }

    /**
     * Constructs a list tag that uses the passed list as its backing list.
     *
     * <p>All {@link List} operations will be delegated to the passed list.
     *
     * @param elementType the element type
     * @param tags the backing list
     */
    public ListTag(TagType elementType, List<T> tags) {
        this.elementType = Objects.requireNonNull(elementType, "element type");
        this.tags = Objects.requireNonNull(tags, "tags");
        checkTypes();
    }

    @Override
    public TagType getType() {
        return TagType.LIST;
    }

    public TagType getElementType() {
        return elementType;
    }

    private static TagType getTagType(List<? extends Tag> tags) {
        if (tags.isEmpty()) {
            throw new IllegalArgumentException("Cannot infer tag type from empty list! Specify the tag type manually.");
        }

        return tags.get(0).getType();
    }

    private void checkTypes() {
        for (T tag : tags) {
            if (tag.getType() != elementType) {
                throw new IllegalArgumentException("Tag " + tag + " has an invalid type! Excepted: " + elementType + ", found: " + tag.getType());
            }
        }
    }

    @Override
    public void write(DataOutput output) throws IOException {
        checkTypes();

        output.writeByte(elementType.getId());
        output.writeInt(tags.size());

        for (T tag : tags) {
            tag.write(output);
        }
    }

    public static ListTag<?> read(DataInput input) throws IOException {
        byte elementTypeId = input.readByte();
        TagType elementType = TagType.byId(elementTypeId);

        int length = input.readInt();
        ArrayList<Tag> tags = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            tags.add(elementType.read(input));
        }

        return new ListTag<>(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListTag<?> listTag = (ListTag<?>) o;
        return elementType.equals(listTag.elementType) &&
                tags.equals(listTag.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType, tags);
    }

    @Override
    public String toString() {
        return "List<" + elementType + ">[" + tags.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public boolean add(T t) {
        if (t.getType() != elementType) {
            throw new IllegalArgumentException("Tag " + t + " has an invalid type! Excepted: " + elementType + ", found: " + t.getType());
        }

        return tags.add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            if (t.getType() != elementType) {
                throw new IllegalArgumentException("Tag " + t + " has an invalid type! Excepted: " + elementType + ", found: " + t.getType());
            }
        }

        return tags.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T t : c) {
            if (t.getType() != elementType) {
                throw new IllegalArgumentException("Tag " + t + " has an invalid type! Excepted: " + elementType + ", found: " + t.getType());
            }
        }

        return tags.addAll(index, c);
    }

    @Override
    public T set(int index, T element) {
        if (element.getType() != elementType) {
            throw new IllegalArgumentException("Tag " + element + " has an invalid type! Excepted: " + elementType + ", found: " + element.getType());
        }

        return tags.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        if (element.getType() != elementType) {
            throw new IllegalArgumentException("Tag " + element + " has an invalid type! Excepted: " + elementType + ", found: " + element.getType());
        }

        tags.add(index, element);
    }

    /////////////////////////////
    //   List implementation   //
    /////////////////////////////

    @Override
    public int size() {
        return tags.size();
    }

    @Override
    public boolean isEmpty() {
        return tags.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return tags.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return tags.iterator();
    }

    @Override
    public Object[] toArray() {
        return tags.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return tags.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return tags.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return tags.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return tags.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return tags.retainAll(c);
    }

    @Override
    public void clear() {
        tags.clear();
    }

    @Override
    public T get(int index) {
        return tags.get(index);
    }

    @Override
    public T remove(int index) {
        return tags.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return tags.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return tags.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return tags.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return tags.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return tags.subList(fromIndex, toIndex);
    }
}
