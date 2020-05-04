package io.github.antiquitymc.nbt;

import com.google.common.collect.ImmutableList;
import io.github.antiquitymc.io.ByteSink;
import io.github.antiquitymc.io.ByteSource;

import java.util.*;
import java.util.stream.Collectors;

public final class ListTag<T extends Tag<T>> implements Tag<ListTag<T>>, List<T> {
    private final Tag.Type<T> elementType;
    private final List<T> tags;

    /**
     * Constructs a mutable list tag.
     */
    public ListTag(Tag.Type<T> elementType) {
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
    public ListTag(Tag.Type<T> elementType, List<T> tags) {
        this.elementType = Objects.requireNonNull(elementType, "element type");
        this.tags = Objects.requireNonNull(tags, "tags");
        checkTypes();
    }

    @Override
    public Tag.Type<ListTag<T>> getType() {
        return Type.instance();
    }

    public Tag.Type<T> getElementType() {
        return elementType;
    }

    private static <T extends Tag<T>> Tag.Type<T> getTagType(List<T> tags) {
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
    public void write(ByteSink sink) {
        checkTypes();

        sink.write(elementType.getId());
        NbtImpl.write(sink, tags.size());

        for (T tag : tags) {
            tag.write(sink);
        }
    }

    public ListTag<T> mutableCopy() {
        return new ListTag<>(new ArrayList<>(tags));
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

    public static final class Type<T extends Tag<T>> implements Tag.Type<ListTag<T>> {
        private static final Type<?> INSTANCE = new Type<>();

        @SuppressWarnings("unchecked")
        public static <T extends Tag<T>> Type<T> instance() {
            return (Type<T>) INSTANCE;
        }

        private Type() {
        }

        @Override
        public byte getId() {
            return LIST;
        }

        @SuppressWarnings("unchecked")
        @Override
        public ListTag<T> read(ByteSource source) {
            Tag.Type<T> elementType = (Tag.Type<T>) NbtImpl.readTagType(source);
            int length = NbtImpl.readInt(source);

            ImmutableList.Builder<T> tags = ImmutableList.builder();

            for (int i = 0; i < length; i++) {
                tags.add(elementType.read(source));
            }

            return new ListTag<>(elementType, tags.build());
        }

        @Override
        public String toString() {
            return "List";
        }
    }
}
