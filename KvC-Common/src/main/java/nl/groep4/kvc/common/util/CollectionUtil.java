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
 * Collection util is used for some missing features in Collections/subclasses
 * 
 * @version 1.5
 * @author Sander Knauff
 */
public class CollectionUtil {
    private static final Random RANDOM = new Random();

    /**
     * 
     * @param e
     * @return a random item from a array
     */
    public static <E> E randomItem(E[] e) {
	if (e.length == 0) {
	    return null;
	}
	return e[RANDOM.nextInt(e.length)];
    }

    /**
     * 
     * @param e
     * @return a random item from a list
     */
    public static <E> E randomItem(List<E> e) {
	if (e.isEmpty()) {
	    return null;
	}
	return e.get(RANDOM.nextInt(e.size()));
    }

    /**
     * 
     * @param es
     * @return
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
     * 
     * @param es
     * @return
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
     * 
     * @param original
     * @param items
     * @return
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
     * 
     * @param item
     * @param lists
     */
    @SafeVarargs
    public static <T> void addToAll(T item, List<T>... lists) {
	for (List<T> list : lists) {
	    list.add(item);
	}
    }

    /**
     * 
     * @param array
     * @param equally
     * @return
     */
    public static <T> int timesFound(T[] array, T equally) {
	return timesFound(Arrays.asList(array), equally);
    }

    /**
     * 
     * @param list
     * @param equally
     * @return
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
     * 
     * @param array
     * @param filter
     * @return
     */
    public static <T> int timesFound(T[] array, Predicate<T> filter) {
	return (int) Arrays.stream(array).filter(filter).count();
    }

    /**
     * 
     * @param list
     * @param filter
     * @return
     */
    public static <T> int timesFound(List<T> list, Predicate<T> filter) {
	return (int) list.stream().filter(filter).count();
    }

    /**
     * 
     * @param list
     * @param filter
     * @return
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
     * 
     * @param list
     * @param filter
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
     * 
     * @param list
     * @param item
     * @return
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
     * 
     * @author Gebruiker
     *
     * @param <K>
     * @param <V>
     */
    public static class GeneticEntry<K, V> implements Entry<K, V> {

	private K key;
	private V value;

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public GeneticEntry(K key, V value) {
	    this.key = key;
	    this.value = value;
	}

	/**
	 * 
	 */
	@Override
	public K getKey() {
	    return key;
	}

	/**
	 * 
	 */
	@Override
	public V getValue() {
	    return value;
	}

	/**
	 * 
	 */
	@Override
	public V setValue(V value) {
	    this.value = value;
	    return value;
	}
    }
}
