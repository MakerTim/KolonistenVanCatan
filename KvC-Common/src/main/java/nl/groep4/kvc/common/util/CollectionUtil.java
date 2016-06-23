package nl.groep4.kvc.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Collection util is used for some missing features in Collections/subclasses.
 * 
 * @version 1.5
 * @author Sander Knauff
 */
public class CollectionUtil {
    private static final Random RANDOM = new Random();

    /**
     * Random parameter returns random item from array.
     * 
     * @param e
     *            A random parameter for the array.
     * @return A random item from an array.
     */
    public static <E> E randomItem(E[] e) {
	if (e.length == 0) {
	    return null;
	}
	return e[RANDOM.nextInt(e.length)];
    }

    /**
     * Random parameter returns random item from list.
     * 
     * @param e
     *            A random parameter for the array.
     * @return A random item from a list.
     */
    public static <E> E randomItem(List<E> e) {
	if (e.isEmpty()) {
	    return null;
	}
	return e.get(RANDOM.nextInt(e.size()));
    }

    /**
     * Gets first item on the list and returns outcome.
     * 
     * @param es
     *            Refers to first item on list.
     * @return The outcome of e : es.
     */
    public static <E> E first(Collection<E> es) {
	E ret = null;
	for (E e : es) {
	    ret = e;
	    break;
	}
	return ret;
    }

    /**
     * Gets first item and returns outcome.
     * 
     * @param es
     *            Refers to first item on list.
     * @return The outcome of e :es.
     */
    public static <E> E first(E[] es) {
	E ret = null;
	for (E e : es) {
	    ret = e;
	    break;
	}
	return ret;
    }

    /**
     * Gets items with list.
     * 
     * @param original
     *            The original size.
     * @param items
     *            Set of items from list.
     * @return Array of data.
     */
    public static <T> List<T> getItems(List<T> original, int... items) {
	List<T> ret = new ArrayList<>();
	for (int i : items) {
	    if (i >= 0 && i < original.size()) {
		ret.add(original.get(i));
	    }
	}
	return ret;
    }

    /**
     * Adds item to list and addresses lists with items.
     * 
     * @param item
     *            Adds item to list.
     * @param lists
     *            Addresses lists with items.
     */
    @SafeVarargs
    public static <T> void addToAll(T item, List<T>... lists) {
	for (List<T> list : lists) {
	    list.add(item);
	}
    }

    /**
     * Generates array and sorts equally.
     * 
     * @param array
     *            Refers to array to be created
     * @param equally
     *            sorts equally
     * @return The actual array is created and returned
     */
    public static <T> int timesFound(T[] array, T equally) {
	return timesFound(Arrays.asList(array), equally);
    }

    /**
     * Generates a random number and returns outcome of for loop.
     * 
     * @param list
     *            Refers to item list
     * @param equally
     *            A random number
     * @return returns outcome of for loop
     */
    public static <T> int timesFound(List<T> list, T equally) {
	int ret = 0;
	for (T item : list) {
	    if (item.equals(equally)) {
		++ret;
	    }
	}
	return ret;
    }

    /**
     * Lists and filters data in array and returns with applied filter.
     * 
     * @param array
     *            Lists data in array.
     * @param filter
     *            Applies filter to array.
     * @return Array is returned with applied filter.
     */
    public static <T> int timesFound(T[] array, Predicate<T> filter) {
	return (int) Arrays.stream(array).filter(filter).count();
    }

    /**
     * Draws Premade list and returns filtered list.
     * 
     * @param list
     *            Draws data from premade list.
     * @param filter
     *            Represents a boolean from the predicate statement.
     * @return Filtered list.
     */
    public static <T> int timesFound(List<T> list, Predicate<T> filter) {
	return (int) list.stream().filter(filter).count();
    }

    /**
     * Draws data and returns items in array list.
     * 
     * @param list
     *            Draws data from premade list.
     * @param filter
     *            Filters data to lower case.
     * @return Returns items in the form of an array list.
     */
    public static String[] filter(String[] list, String filter) {
	String[] items = new String[0];
	for (String item : list) {
	    if (item.toLowerCase().contains(filter.toLowerCase())) {
		items = Arrays.copyOf(items, items.length + 1);
		items[items.length - 1] = item;
	    }
	}
	return items;
    }

    /**
     * Returns itrator and filters for lower case.
     * 
     * @param list
     *            Returns and itrator over the elements of T.
     * @param filter
     *            Filters for lower case.
     */
    public static <T extends Iterable<String>> void filter(T list, String filter) {
	Iterator<? extends String> strI = list.iterator();
	while (strI.hasNext()) {
	    String str = strI.next();
	    if (!str.toLowerCase().contains(filter.toLowerCase())) {
		strI.remove();
	    }
	}
    }

    /**
     * Gets in range array and index.
     * 
     * @param array
     *            Lists data in array.
     * @param index
     *            Current index.
     * @return Array in range.
     */
    public static <T> T getInRange(T[] array, int index) {
	return array[modInRange(array, index)];
    }

    /**
     * Checks if in range.
     * 
     * @param array
     *            Lists data in array.
     * @param index
     *            Current index.
     * @return Index integer in range.
     */
    public static int modInRange(Object[] array, int index) {
	index %= array.length;
	if (index < 0) {
	    index += array.length;
	}
	return index;
    }

    /**
     * Selects specific items from list and returns outcome of boolean.
     * 
     * @param list
     *            Items in list
     * @param item
     *            specific item selected from list
     * @return returns outcome of boolean
     */
    public static <T> boolean contains(T[] list, T item) {
	boolean b = false;
	for (T listItem : list) {
	    if (listItem.equals(item)) {
		b = true;
		break;
	    }
	}
	return b;
    }

    /**
     * Generates definition for key and value.
     *
     * @param <K>
     *            Definition for key.
     * @param <V>
     *            Definition for value.
     */
    public static class GeneticEntry<K, V> implements Entry<K, V> {

	private K key;
	private V value;

	/**
	 * Generates key and value.
	 * 
	 * @param key
	 *            Random number entry.
	 * @param value
	 *            Random value entry.
	 */
	public GeneticEntry(K key, V value) {
	    this.key = key;
	    this.value = value;
	}

	@Override
	public K getKey() {
	    return key;
	}

	@Override
	public V getValue() {
	    return value;
	}

	@Override
	public V setValue(V value) {
	    this.value = value;
	    return value;
	}
    }
}
