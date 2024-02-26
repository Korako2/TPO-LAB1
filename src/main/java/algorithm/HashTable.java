package algorithm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<V> {
    private class Entry {
        Integer key;
        V value;

        Entry(Integer key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private final List<List<Entry>> buckets;

    public HashTable(int nBuckets) {
        if (nBuckets <= 0)
            throw new IllegalArgumentException("Number of buckets must be greather than 0");

        this.buckets = new ArrayList<>(nBuckets);

        for (int i = 0; i < nBuckets; i++) {
            this.buckets.add(new LinkedList<>());
        }
    }

    public V put(Integer key, V value) {
        int hashKey = hashKey(key);
        List<Entry> bucket = buckets.get(hashKey);

        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                V prev = entry.value;
                entry.value = value;
                return prev;
            }
        }

        Entry newEntry = new Entry(key, value);
        bucket.add(newEntry);

        return null;
    }

    public V get(Integer key) {
        int hashKey = hashKey(key);
        List<Entry> bucket = buckets.get(hashKey);
        return bucket.stream().filter(e -> e.key.equals(key)).map(e -> e.value).findFirst().orElse(null);
    }

    public V delete(Integer key) {
        int hashKey = hashKey(key);
        List<Entry> bucket = buckets.get(hashKey);
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).key.equals(key)) {
                V prev = bucket.get(i).value;
                bucket.remove(i);
                return prev;
            }
        }

        return null;
    }

    public List<List<V>> getBuckets() {
        List<List<V>> result = new ArrayList<>();
        for (List<Entry> bucket : buckets) {
            List<V> values = new ArrayList<>();
            for (Entry entry : bucket) {
                values.add(entry.value);
            }
            result.add(values);
        }
        return result;
    }

    private int hashKey(Integer key) {
        return Math.abs(key) % buckets.size();
    }
}