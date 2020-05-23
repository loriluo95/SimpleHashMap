public class SimpleHashMap<K, V> {
    private class Entry<K, V> {
        K key;
        V value;
        Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOADFACTOR = 0.75f;

    private Entry<K, V>[] array;
    private float loadFactor;
    private int size;
    public SimpleHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    public SimpleHashMap(int capacity, float loadFactor) {
        this.array = new Entry[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public void put(K key, V value) {
        // Resize when size equals the product of capacity and loadFactor
        if (isFull()) {
            reHash(array.length * 2);
        }
        int index = getIndex(key);

        Entry<K, V> curr = array[index];
        Entry<K, V> newEntry = new Entry<K, V>(key, value);

        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }
        // Reset to the head
        curr = array[index];
        newEntry.next = curr;
        array[index] = newEntry;
        size++;
    }

    public boolean containsKey(K key) {
//        doEmptyCheck();
        int index = getIndex(key);
        Entry<K, V> curr = array[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public V get(K key) {
        doEmptyCheck();
        int index = getIndex(key);
        Entry<K, V> curr = array[index];
        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    public void remove(K key) {
        doEmptyCheck();
        int index = getIndex(key);
        Entry<K, V> curr = array[index];
        Entry<K, V> prev = null;
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) return;

        // remove target key
        if (prev != null) {
            prev.next = curr.next;
//            array[index] = prev;
        } else {
            array[index] = curr.next;
        }
        size--;
    }

    public int size() {
        System.out.println("current size is: " + size);
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        return key.hashCode() % array.length;
    }

    private void reHash(int newCapacity) {
        Entry[] temp = array;
        int tempSize = size;
        array = new Entry[newCapacity];
        for (Entry<K, V> entry : temp) {
            while (entry !=  null) {
                this.put(entry.key, entry.value);
                entry = entry.next;
            }
        }
        size = tempSize;
    }

    private boolean isFull() {
        return size == array.length * loadFactor;
    }

    private void doEmptyCheck() {
        if (isEmpty()) {
            throw new IllegalArgumentException("No element in this HashMap");
        }
    }

    public static void main(String[] args) {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>(4, 0.75f);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
        map.put(9,9);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));
        System.out.println(map.get(5));
        System.out.println(map.get(9));
        map.size();

        map.put(1,11);
        map.put(2,12);
        map.put(3,13);
        map.put(4,14);
        map.put(5,15);
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));
        System.out.println(map.get(5));
        map.size();

        map.remove(1);
        System.out.println(map.containsKey(1));
        System.out.println(map.get(5));
        System.out.println(map.get(9));
        map.size();

        map.remove(5);
        System.out.println(map.containsKey(5));
        System.out.println(map.get(9));
        map.size();

        map.remove(9);
        System.out.println(map.containsKey(9));
        map.size();

        map.remove(2);
        System.out.println(map.containsKey(2));
        map.size();
    }

}
