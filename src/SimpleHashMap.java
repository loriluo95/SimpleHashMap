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

    public SimpleHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOADFACTOR);
    }

    public SimpleHashMap(int capacity, float loadFactor) {}

    public void put(K key, V value) {}

    public boolean containsKey(K key) {}

    public V get(K key) {}

    public void remove(K key) {}

    public int size() {
        System.out.println("current size is: " + size);
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {}

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
