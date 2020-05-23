import org.junit.jupiter.api.Test;

import java.util.PrimitiveIterator;

import static org.junit.jupiter.api.Assertions.*;

class SimpleHashMapTest {

    SimpleHashMap<Integer, Integer> hp;

    @Test
    public void testPutCollision() {
        hp = new SimpleHashMap<>(4, 0.75f);
        hp.put(1, 1);
        hp.put(5, 5);
        hp.put(9, 9);
        assertEquals(1, hp.get(1));
        assertEquals(9, hp.get(9));
        assertEquals(5, hp.get(5));
    }

    @Test
    public void testPutUpdate() {
        hp = new SimpleHashMap<>(4, 0.75f);
        hp.put(1, 1);
        hp.put(9, 9);

        assertEquals(1, hp.get(1));
        hp.put(1, 10);
        assertEquals(10, hp.get(1));
        assertEquals(2, hp.size());
    }

    @Test
    public void testGetCollision() {
        hp = new SimpleHashMap<>(4, 0.75f);
        hp.put(1, 1);
        hp.put(9, 9);
        assertEquals(1, hp.get(1));
        assertEquals(9, hp.get(9));
    }

    @Test
    public void testGetEmptyException() {
        hp = new SimpleHashMap<>();
        Exception getException = assertThrows(IllegalArgumentException.class, () -> {
            hp.get(1);
        });
        String expectedMessage = "No element in this HashMap";
        assertEquals(expectedMessage, getException.getMessage());
    }

    @Test
    public void testReHash() {
        hp = new SimpleHashMap<>(4, 0.75f);
        hp.put(1, 1);
        hp.put(3, 3);
        hp.put(5, 5);
        hp.put(9, 9);
        hp.put(7, 7);
        assertEquals(1, hp.get(1));
        assertEquals(5, hp.get(5));
        assertEquals(9, hp.get(9));
        assertEquals(5, hp.size());
    }

    @Test
    public void testRemove() {
        hp = new SimpleHashMap<>();
        hp.put(1, 1);
        hp.put(3, 3);
        hp.put(5, 5);
        hp.put(9, 9);
        hp.remove(1);
        assertEquals(3, hp.size());
        assertEquals(false, hp.containsKey(1));
        assertEquals(9, hp.get(9));

    }

    @Test
    public void testRemoveEmptyException() {
        hp = new SimpleHashMap<>();
        Exception removeException = assertThrows(IllegalArgumentException.class, () -> {
            hp.remove(1);
        });
        String expectedMessage = "No element in this HashMap";
        assertEquals(expectedMessage, removeException.getMessage());
    }

    @Test
    public void testContainsKey() {
        hp = new SimpleHashMap<>();
        hp.put(1, 1);
        assertEquals(false, hp.containsKey(0));
        assertEquals(true, hp.containsKey(1));
    }



}