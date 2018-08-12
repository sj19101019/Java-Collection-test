package com.example.ocba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ArrayListVsLinkedList {
    private static final int MAX = 500000;
    String[] strings = maxArray();

    ////////////// ADD ALL ////////////////////////////////////////
    @Test
    public void addAll() {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>(MAX);

        watch.start();
        arrayList.addAll(stringList);
        watch.totalTime("Array List addAll() = ");//101,16719 Nanoseconds
        

        watch.start();
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(stringList);
        watch.totalTime("Linked List addAll() = ");  //2623,29291 Nanoseconds
    }

    //Note: ArrayList is 26 time faster here than LinkedList for addAll()

    ///////////////// INSERT /////////////////////////////////////////////
    @Test
    public void arrayListAdd() {
        Watch watch = new Watch();
        List<String> arrayList = new ArrayList<String>(MAX);

        watch.start();
        for (String string : strings)
            arrayList.add(string);
        watch.totalTime("Array List add() = ");//152,46840 Nanoseconds
        

        List<String> linkedList = new LinkedList<String>();
        watch.start();
        for (String string : strings)
            linkedList.add(string);
        watch.totalTime("Linked List add() = "); 
    }

    //Note: ArrayList is 9 times faster than LinkedList for add sequentially

    /////////////////// INSERT IN BETWEEN ///////////////////////////////////////

    @Test
    public void arrayListInsertOne() {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>(MAX + MAX / 10);
        arrayList.addAll(stringList);

        String insertString0 = getString(true, MAX / 2 + 10);
        String insertString1 = getString(true, MAX / 2 + 20);
        String insertString2 = getString(true, MAX / 2 + 30);
        String insertString3 = getString(true, MAX / 2 + 40);

        watch.start();

        arrayList.add(insertString0);
        arrayList.add(insertString1);
        arrayList.add(insertString2);
        arrayList.add(insertString3);

        watch.totalTime("Array List add 4 item = ");//36527
        
        
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(stringList);

        watch.start();

        linkedList.add(insertString0);
        linkedList.add(insertString1);
        linkedList.add(insertString2);
        linkedList.add(insertString3);

        watch.totalTime("Linked List add 4 item = ");//29193
    }
    
    @Test
    public void linkedListIterator() {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>();
        arrayList.addAll(stringList);

        watch.start();

        Iterator<String> aitr = arrayList.iterator();
        
        while (aitr.hasNext()) {
        	aitr.next();
        }

        watch.totalTime("Array List Iterator = ");
        
        
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(stringList);

        watch.start();

        Iterator<String> itr = linkedList.iterator();
        
        while (itr.hasNext()) {
        	itr.next();
        }

        watch.totalTime("Linked List Iterator = ");
    }
    
    
    @Test
    public void linkedListForLoop() {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>();
        arrayList.addAll(stringList);

        watch.start();

        for (String s : arrayList) {
        	
        }

        watch.totalTime("Array List For Loop = ");
        
        
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(stringList);

        watch.start();

        
        for (String s : linkedList) {
        	
        }

        watch.totalTime("Linked List For Loop = ");
    }


    //Note: LinkedList is 3000 nanosecond faster than ArrayList for insert randomly.

    ////////////////// DELETE //////////////////////////////////////////////////////
    @Test
    public void arrayListRemove() throws Exception {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>(MAX);

        arrayList.addAll(stringList);
        String searchString0 = getString(true, MAX / 2 + 10);
        String searchString1 = getString(true, MAX / 2 + 20);

        watch.start();
        arrayList.remove(searchString0);
        arrayList.remove(searchString1);
        watch.totalTime("Array List remove() = ");//20,56,9095 Nanoseconds
        
        
        
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(stringList);

        watch.start();
        linkedList.remove(searchString0);
        linkedList.remove(searchString1);
        watch.totalTime("Linked List remove = ");
    }

    //Note: LinkedList is 10 millisecond faster than ArrayList while removing item.

    ///////////////////// SEARCH ///////////////////////////////////////////
    @Test
    public void arrayListSearch() throws Exception {
        Watch watch = new Watch();
        List<String> stringList = Arrays.asList(strings);
        List<String> arrayList = new ArrayList<String>(MAX);

        arrayList.addAll(stringList);
        String searchString0 = getString(true, MAX / 2 + 10);
        String searchString1 = getString(true, MAX / 2 + 20);

        watch.start();
        arrayList.contains(searchString0);
        arrayList.contains(searchString1);
        watch.totalTime("Array List addAll() time = ");//186,15,704
        
        
        
        List<String> linkedList = new LinkedList<String>();
        linkedList.addAll(Arrays.asList(strings));

        watch.start();
        linkedList.contains(searchString0);
        linkedList.contains(searchString1);
        watch.totalTime("Linked List addAll() time = ");
    }


    //Note: Linked List is 500 Milliseconds faster than ArrayList

    class Watch {
        private long startTime;
        private long endTime;

        public void start() {
            startTime = System.nanoTime();
        }

        private void stop() {
            endTime = System.nanoTime();
        }

        public void totalTime(String s) {
            stop();
            System.out.println(s + (endTime - startTime));
        }
    }


    private String[] maxArray() {
        String[] strings = new String[MAX];
        Boolean result = Boolean.TRUE;
        for (int i = 0; i < MAX; i++) {
            strings[i] = getString(result, i);
            result = !result;
        }
        return strings;
    }

    private String getString(Boolean result, int i) {
        return String.valueOf(result) + i + String.valueOf(!result);
    }
}
