package com.futurumgame.base;

import com.futurumgame.base.interfaces.IMapper;
import com.futurumgame.base.util.CollectionHelper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionHelperTest {

    @Test
    public void testAddAll() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        List<Integer> testList2 = new ArrayList<>(Arrays.asList(1, 5, -2, 0, 15));
        List<Object> testList3 = new ArrayList<>(Arrays.asList(1, 5.6, -56.2, "Hi", true));

        CollectionHelper.addAll(testList1, "Does", "This", "Work");
        CollectionHelper.addAll(testList2, 34, 42, -42);
        CollectionHelper.addAll(testList3, "17", 4);

        assertEquals("World", testList1.get(1));
        assertEquals("Does", testList1.get(4));

        assertTrue(1 == new Integer(testList2.get(0)));
        assertTrue(42 ==  new Integer(testList2.get(6)));

        assertTrue(5.6 == (double)testList3.get(1));
        assertTrue("17" == (String)testList3.get(5));

        List<String> addedList = new ArrayList<>(Arrays.asList("Hi", "What's up"));

        CollectionHelper.addAll(testList1, addedList);

        assertEquals("This", testList1.get(5));
        assertEquals("Hi", testList1.get(7));
    }

    @Test
    public void testAsCollection() {
        Iterable<String> generatedIterable = CollectionHelper.asIterable("Hello", "World");
        List<String> generatedList = CollectionHelper.asList(generatedIterable);

        assertEquals("Hello", generatedList.get(0));
        assertEquals("World", generatedList.get(1));
    }

    @Test
    public void testContains() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        assertTrue(CollectionHelper.contains(testList1, p -> p.equals("Hello")));
        assertFalse(CollectionHelper.contains(testList1, p -> p.equals("Hi")));
    }

    @Test
    public void testListEquals() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        List<String> testList2 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        List<String> testList3 = new ArrayList<>(Arrays.asList("Hi", "What's up"));

        assertTrue(CollectionHelper.sequenceEquals(testList1, testList2));
        assertFalse(CollectionHelper.sequenceEquals(testList1, testList3));
    }

    @Test
    public void testToMap() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        Map<Integer, String> generatedMap = CollectionHelper.toMap(testList1, u -> testList1.indexOf(u));

        assertEquals("Hello", generatedMap.get(0));
        assertEquals("World", generatedMap.get(1));
        assertEquals("or", generatedMap.get(2));
        assertEquals("test", generatedMap.get(3));
    }

    @Test
    public void testWhere() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        List<String> testList2 = new ArrayList<>(Arrays.asList("Hello", "World", null, "test"));

        assertEquals(testList1, CollectionHelper.whereNotNull(testList1));

        List<String> nullStrippedList = new ArrayList<>(Arrays.asList("Hello", "World", "test"));
        assertTrue(CollectionHelper.sequenceEquals(CollectionHelper.whereNotNull(testList2), nullStrippedList));
    }

    @Test
    public void testToString() {
        List<String> testList1 = new ArrayList<>(Arrays.asList("Hello", "World", "or", "test"));
        List<Integer> testList2 = new ArrayList<>(Arrays.asList(1, 5, -2, 0, 15));
        List<Object> testList3 = new ArrayList<>(Arrays.asList(1, 5.6, -56.2, "Hi", true));

        String[] testArray1 = {"Hello", "World", "or", "test"};
        Object[] testArray2 = {1, 5.6, -56.2, "Hi", true};

        assertEquals("Hello, World, or, test", CollectionHelper.toString(testList1));
        assertEquals("1, 5, -2, 0, 15", CollectionHelper.toString(testList2));
        assertEquals("1, 5.6, -56.2, Hi, true", CollectionHelper.toString(testList3));
        assertEquals("Hello, World, or, test", CollectionHelper.toString(testArray1));
        assertEquals("1, 5.6, -56.2, Hi, true", CollectionHelper.toString(testArray2));
    }
}
