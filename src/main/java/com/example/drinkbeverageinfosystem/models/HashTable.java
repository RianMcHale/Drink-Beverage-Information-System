package com.example.drinkbeverageinfosystem.models;

public class HashTable<T> {
    private static final int TABLE_SIZE = 100; // size of hash table
    private Entry<T>[] table; // array to store data

    public HashTable() {
        table = new Entry[TABLE_SIZE]; // constructor to create table
    }


    // KEY = UNIQUE IDENTIFIER. used to store & retrieve a value in the hash table e.g acting as a label for each drink/ingredient/recipe
    // ensures each drink/ingredient/recipe does not overlap, and that a drink doesn't get added to ingredient listview etc
    // keys cannot change

    private int hashFunction(String key) { // calculate index of given key
        int hash = 0;
        for (char c : key.toCharArray()) { // loop through each character in the key
            hash = (hash * 31 + c) % TABLE_SIZE; // update hash table using prime multiplier and table size
        }
        return Math.abs(hash); // ensures the index isnt negative
    }


    public void put(String key, T value) { // add a key value pair to the table
        int index = hashFunction(key); // gets index for the key
        if (table[index] == null) {
            table[index] = new Entry<>(key, value, null); // if index slot is empty, add the entry key
        } else {
            Entry<T> current = table[index]; // if theres collisions it checks the linked list
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // if key already exists it updates its value to ensure no key is the same
                    return;
                }
                if (current.next == null) break; // stops at end of list
                current = current.next; // moves onto next node
            }
            current.next = new Entry<>(key, value, null); // add entry at end of the list
        }
    }

    // retrieve a value using its key
    public T get(String key) {
        int index = hashFunction(key); // get index of the key
        Entry<T> current = table[index]; // start at the first entry
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value; // if key matches it returns the value
            }
            current = current.next; // moves onto the next entry
        }
        return null; // return null if key not found
    }

    // remove a key-value pair
    public void remove(String key) {
        int index = hashFunction(key); // gets index for the key
        Entry<T> current = table[index]; // start at first entry
        Entry<T> previous = null; // keeps track of previous entry

        while (current != null) { // goes through linked list to find key
            if (current.key.equals(key)) { // if key matches it removes the entry
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                return; // stops after removing the entry
            }
            previous = current; // moves to the next node while updating the previous
            current = current.next;
        }
    }


    public String[] getAllKeys() { // lists all the keys in hash talbe
        int count = 0; // counter for total number of keys
        for (int i = 0; i < TABLE_SIZE; i++) { // loops through each slot in table
            Entry<T> current = table[i]; // starts at first entry in the slot
            while (current != null) {
                count++; // increment the counter for each entry found
                current = current.next; // moves to next node
            }
        }

        String[] keys = new String[count]; // creates array to hold all keys
        int index = 0; // index for the array of keys
        for (int i = 0; i < TABLE_SIZE; i++) { // loops through table again to find keys
            Entry<T> current = table[i];
            while (current != null) {
                keys[index++] = current.key; // adds each key to array
                current = current.next; // moves onto next node
            }
        }
        return keys; // returns array of keys
    }

    // Entry class
    private static class Entry<T> { // class to represent entry in hash table
        String key; // key for the entry
        T value; // value associated with key
        Entry<T> next; // points to next entry in linked list

        Entry(String key, T value, Entry<T> next) { // constructor to initalize entry with its key, value and next node
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
