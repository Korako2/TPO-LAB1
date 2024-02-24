package algorithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    public void illegalBucketNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new HashTable<String>(0);
        });
    }

    @Test
    @DisplayName("Insertion of a single element")
    public void insertion() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertEquals("Ivan Varyukhin", table.get(0));
    }

    @Test
    @DisplayName("Repeated insertion of the same key")
    public void repeatedInsertion() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertEquals("Ivan Varyukhin", table.get(0));
        assertEquals("Ivan Varyukhin", table.put(0, "Arkhip Ryabokon"));
        assertEquals("Arkhip Ryabokon", table.get(0));
    }

    @Test
    @DisplayName("Insertion with a collision")
    public void collisionsInsertion() {
        String[] names = new String[]{"Ivan Varyukhin", "Arkhip Ryabokon", "Chernova Elizabeth", "Kanukova Eva", "Pavel Solovyov", "Malika Agadilova"};
        // Number of buckets = 5, inserting 6 elements will guarantee a collision
        HashTable<String> table = new HashTable<>(5);
        for (int i = 0; i < 6; i++) {
            assertNull(table.put(i, names[i]));
            for (int j = 0; j <= i; j++) {
                assertEquals(names[j], table.get(j));
            }
        }
    }

    @Test
    @DisplayName("Get the value from a non-existent key")
    public void getNonExistentKey() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.get(52));
    }

    @Test
    @DisplayName("Delete a key")
    public void deleteKey() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertEquals("Ivan Varyukhin", table.get(0));
        assertEquals("Ivan Varyukhin", table.delete(0));
        assertNull(table.get(0));
    }

    @Test
    @DisplayName("Delete a non-existent key")
    public void deleteNonExistentKey() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.delete(0));
    }
}