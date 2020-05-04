package io.github.antiquitymc.nbt.test;

import io.github.antiquitymc.nbt.CompoundTag;
import io.github.antiquitymc.nbt.IntTag;
import io.github.antiquitymc.nbt.ListTag;
import io.github.antiquitymc.nbt.NamedTag;
import io.github.antiquitymc.nbt.NbtIo;
import io.github.antiquitymc.nbt.TagType;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IoTests {
    @Test
    void readLevelDat() throws Exception {
        NbtIo.readGzipped(IoTests.class.getResourceAsStream("/level.dat"));
    }

    @Test
    void roundtrip() throws Exception {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Year", 2020);
        CompoundTag nested = new CompoundTag();
        nested.putString("Hello", "world!");
        ListTag<IntTag> tags = new ListTag<>(TagType.INT);
        tags.add(new IntTag(10));
        tags.add(new IntTag(20));
        tags.add(new IntTag(30));
        tag.put("Nested tags", nested);
        tag.put("Listed tags", tags);

        NamedTag written = new NamedTag("Root", tag);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        NbtIo.write(out, written);
        NamedTag read = NbtIo.read(new ByteArrayInputStream(out.toByteArray()));

        assertEquals(written, read);
    }
}
