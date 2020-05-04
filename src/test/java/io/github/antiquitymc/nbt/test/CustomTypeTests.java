package io.github.antiquitymc.nbt.test;

import io.github.antiquitymc.nbt.CompoundTag;
import io.github.antiquitymc.nbt.NamedTag;
import io.github.antiquitymc.nbt.NbtIo;
import io.github.antiquitymc.nbt.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomTypeTests {
    @Test
    void charToShort() throws IOException {
        CompoundTag expected = new CompoundTag();
        expected.putShort("Hello", (short) 'H');

        CompoundTag root = new CompoundTag();
        root.putChar("Hello", 'H');

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        NbtIo.write(out, new NamedTag("", root));
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        Tag read = NbtIo.read(in).getTag();

        assertEquals(expected, read);
    }
}
