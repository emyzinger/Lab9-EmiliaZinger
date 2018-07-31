package edu.luc.cs271.myhashmap;

import java.util.*;

/**
 * A generic HashMap custom implementation using chaining. Concretely, the table is an ArrayList of
 * chains represented as LinkedLists.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class MyHashMap<K, V> implements Map<K, V> {

  private static final int DEFAULT_TABLE_SIZE = 11; // a prime

  private List<List<Entry<K, V>>> table;

  public MyHashMap() {
    this(DEFAULT_TABLE_SIZE);
  }

  public MyHashMap(final int tableSize) {
    // allocate a table of the given size
    table = new ArrayList<>(tableSize);
    // then create an empty chain at each position
    for (int i = 0; i < tableSize; i += 1) {
      table.add(new LinkedList<>());
    }
  }

  @Override
  public int size() {
    int result = 0;
    for(int i= 0; i<table.size(); i++ ){
       result = result + table.get(i).size();

    }
    // TO DO add the sizes of all the chains

    return result;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean containsKey(final Object key) {
    boolean theValue = false;
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while(iter.hasNext()){
      final Entry<K, V> entry = iter.next();
      if(entry.getKey().equals(key)){
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsValue(final Object value) {
    //like size we have to search through each bucket and through each linkedList
    for (int i = 0; i<table.size(); i++ ){ //going through whole table
        for (int j = 0; j<table.get(i).size(); j++){ //going through linked list
            if (table.get(i).get(j).getValue().equals(value)){
              return true;
            }
        }
    }

    return false;
  }

  @Override
  public V get(final Object key) {
    // TO DO follow basic approach of remove below (though this will be simpler)
    //if first time null
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V theValue = entry.getValue();
        return theValue;
      }
    }
    return null;
  }

  @Override
  public V put(final K key, final V value) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        entry.setValue(value);
        return oldValue;
      }
    }
    table.get(index).add(new AbstractMap.SimpleEntry<K,V>(key, value));
    return null;
  }

  @Override
  public V remove(final Object key) {
    final int index = calculateIndex(key);
    final Iterator<Entry<K, V>> iter = table.get(index).iterator();
    while (iter.hasNext()) {
      final Entry<K, V> entry = iter.next();
      if (entry.getKey().equals(key)) {
        final V oldValue = entry.getValue();
        iter.remove();
        return oldValue;
      }
    }
    return null;
  }

  @Override
  public void putAll(final Map<? extends K, ? extends V> m) {
    // TO DO add each entry in m's entrySet
    for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()){
      this.put(entry.getKey(),entry.getValue());
    }
  }

  @Override
  public void clear() {
    // TO DO clear each chain
    for (int i = 0; i<table.size(); i++ ) { //going through whole table
      table.get(i).clear();
    }
  }

  /** The resulting keySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<K> keySet() {
    final Set<K> result = new HashSet<>();
    // TO DO populate the set
    for (int i = 0; i<table.size(); i++ ){ //going through whole table
      for (int j = 0; j<table.get(i).size(); j++) { //going through linked list
        result.add(table.get(i).get(j).getKey());
      }
    }

    return Collections.unmodifiableSet(result);
  }

  /** The resulting values collection is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Collection<V> values() {
    final List<V> result = new LinkedList<>();
    // TO DO populate the list
    for (int i = 0; i<table.size(); i++ ){ //going through whole table
      for (int j = 0; j<table.get(i).size(); j++) { //going through linked list
        result.add(table.get(i).get(j).getValue());
      }
    }

    return Collections.unmodifiableCollection(result);
  }

  /** The resulting entrySet is not "backed" by the Map, so we keep it unmodifiable. */
  @Override
  public Set<Entry<K, V>> entrySet() {
    final Set<Entry<K, V>> result = new HashSet<>();
    // TO DO populate the set
    for (int i = 0; i<table.size(); i++ ){ //going through whole table
      for (int j = 0; j<table.get(i).size(); j++) { //going through linked list
        result.add(table.get(i).get(j));
      }
    }

    return Collections.unmodifiableSet(result);
  }

  @Override
  public String toString() {
    // TO DO return the string representation of the underlying table
    String theString = "";
    for (int i = 0; i<table.size(); i++ ){ //going through whole table
      for (int j = 0; j<table.get(i).size(); j++) { //going through linked list
        theString = theString.concat(table.get(i).get(j).getKey() + " " + table.get(i).get(j).getValue() + ") (");
      }
    }

    return theString;
  }

  public boolean equals(final Object that) {
    if (this == that) {
      return true;
    } else if (!(that instanceof Map)) {
      return false;
    } else if (this.entrySet().equals(((Map) that).entrySet())) {
      // TO DO simply compare the entry sets
      return true;
    }
      return false;
  }

  private int calculateIndex(final Object key) {
    // positive remainder (as opposed to %)
    // required in case hashCode is negative!
    return Math.floorMod(key.hashCode(), table.size());
  }
}
