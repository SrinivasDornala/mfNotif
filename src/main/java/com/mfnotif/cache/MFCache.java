package com.mfnotif.cache;

import java.util.HashMap;

class Node<T> {
    String key;
    T value;
    Node pre;
    Node next;
    public Node(String key, T value) {
        this.key = key;
        this.value = value;
    }
}

public class MFCache<T> {
    private static MFCache mfCache = new MFCache(100);
    private MFCache(){}
    private HashMap<String, Node> map;
    private int capicity, count;
    private Node head, tail;

    public MFCache(int capacity) {
        this.capicity = capacity;
        map = new HashMap<>();
        head = new Node("0", 0);
        tail = new Node("0", 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }

    public static MFCache getCache() {
        return mfCache;
    }

    public void deleteNode(Node node)  {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(Node node)  {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    // This method works in O(1)
    public T get(int key)  {
        if (map.get(key) != null) {
            Node node = map.get(key);
            T result = (T) node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return null;
    }

    // This method works in O(1)
    public void put(String key, T value) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if (count < capicity) {
                count++;
                addToHead(node);
            }  else {
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
    }

}
