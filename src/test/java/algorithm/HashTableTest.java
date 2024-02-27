package algorithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @ParameterizedTest
    @DisplayName("Illegal bucket number")
    @ValueSource(ints = {0, -1})
    public void illegalBucketNumber(int n) {
        assertThrows(IllegalArgumentException.class, () -> new HashTable<String>(n));
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

    @Test
    @DisplayName("Attempt to re-delete")
    public void reDelete() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertEquals("Ivan Varyukhin", table.get(0));
        assertEquals("Ivan Varyukhin", table.delete(0));
        assertNull(table.get(0));
        assertNull(table.delete(0));
    }

    @Test
    @DisplayName("Check adding to the correct bucket")
    public void checkAddingToCorrectBucket() {
        HashTable<String> table = new HashTable<>(5);
        String[] names = new String[]{"Ivan Varyukhin", "Arkhip Ryabokon", "Chernova Elizabeth", "Kanukova Eva", "Pavel Solovyov"};
        int[] keys = new int[]{0, 6, 7, 8, 9};
        for (int i = 0; i < names.length; i ++) {
            assertNull(table.put(keys[i], names[i]));
        }
        List<List<String>> buckets = table.getBuckets();
        for (int i = 0; i < names.length; i++) {
            assertEquals(names[i], buckets.get(keys[i] % 5).get(0));
        }
    }
    @Test
    @DisplayName("Check correct bucket size")
    public void checkCorrectBucketSize() {
        HashTable<String> table = new HashTable<>(5);
        String[] names = new String[]{"Ivan Varyukhin", "Arkhip Ryabokon", "Chernova Elizabeth", "Kanukova Eva", "Pavel Solovyov"};
        int[] keys = new int[]{0, 6, 7, 8, 9};
        for (int i = 0; i < names.length; i ++) {
            assertNull(table.put(keys[i], names[i]));
        }
        List<List<String>> buckets = table.getBuckets();
        for (int i = 0; i < names.length; i++) {
            assertEquals(1, buckets.get(keys[i] % 5).size());
        }
    }

    @Test
    @DisplayName("Check the correctness of the collision when adding to the basket")
    public void checkCollisionAddingToBucket() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertNull(table.put(5, "Arkhip Ryabokon"));
        assertNull(table.put(10, "Chernova Elizabeth"));
        List<List<String>> buckets = table.getBuckets();
        assertEquals("Ivan Varyukhin", buckets.get(0).get(0));
        assertEquals("Arkhip Ryabokon", buckets.get(0).get(1));
        assertEquals("Chernova Elizabeth", buckets.get(0).get(2));
    }

    @Test
    @DisplayName("Check the correct bucket size when adding to the basket with collision")
    public void checkCorrectBucketSizeWithCollision() {
        HashTable<String> table = new HashTable<>(5);
        String[] names = new String[]{"Ivan Varyukhin", "Arkhip Ryabokon", "Chernova Elizabeth", "Kanukova Eva"};
        int[] keys = new int[]{0, 5, 10, 11};
        for (int i = 0; i < names.length; i ++) {
            assertNull(table.put(keys[i], names[i]));
        }
        List<List<String>> buckets = table.getBuckets();
        System.out.println(buckets);
        int[] size = new int[]{3, 1, 0, 0, 0};
        for (int i = 0; i < names.length; i++) {
            assertEquals(size[i], buckets.get(i).size());
        }
    }

    @Test
    @DisplayName("Check the correct deletion in the trash in case of a collision")
    public void checkCorrectDeletionInBucketWithCollision() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, "Ivan Varyukhin"));
        assertNull(table.put(5, "Arkhip Ryabokon"));
        assertNull(table.put(10, "Chernova Elizabeth"));
        assertEquals("Ivan Varyukhin", table.delete(0));
        assertNull(table.get(0));
        List<List<String>> buckets = table.getBuckets();
        assertEquals("Arkhip Ryabokon", buckets.get(0).get(0));
        assertEquals("Chernova Elizabeth", buckets.get(0).get(1));
        assertEquals("Chernova Elizabeth", table.delete(10));
        assertNull(table.get(10));
        assertEquals("Arkhip Ryabokon", buckets.get(0).get(0));
    }

    @Test
    @DisplayName("Check null value")
    public void checkNullValue() {
        HashTable<String> table = new HashTable<>(5);
        assertNull(table.put(0, null));
        assertThrows(NullPointerException.class, () -> table.get(0));
    }

}