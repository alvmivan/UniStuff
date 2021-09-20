package collections;

import java.util.Arrays;

public class NaturalsCollection {

    private static final int newAllocSize = 256;
    private static final int chSize = 256;
    private char[] masks = new char[newAllocSize];
    private int size = 0;

    private void makeMaskSupportIndex(int index) {
        if (masks.length <= index) {
            masks = Arrays.copyOf(masks, index + newAllocSize);
            Arrays.fill(masks, index, index + newAllocSize - 1, (char) 0);
        }
    }

    public int size(){
        return size;
    }

    public void add(int number)//number >0
    {
        int charIndex = number / chSize;
        if (masks.length <= charIndex) return;
        char numberBit = (char) (number % chSize);
        int mask = 1 << numberBit;
        boolean contains = (masks[charIndex] & mask) != 0;
        if (!contains) {
            masks[charIndex] |= mask;
            size++;
        }
    }

    public boolean contains(int number) {
        int charIndex = number / chSize;
        if (masks.length <= charIndex) return false;
        char numberBit = (char) (number % chSize);
        int mask = 1 << numberBit;
        return (masks[charIndex] & mask) != 0;
    }

    public void delete(int number) {
        int charIndex = number / chSize;
        if (masks.length <= charIndex) return;
        char numberBit = (char) (number % chSize);
        int mask = 1 << numberBit;
        boolean contains = (masks[charIndex] & mask) != 0;
        if (contains) {
            masks[charIndex] ^= mask;
            size--;
        }
    }


    public static void test() {
        NaturalsCollection nc = new NaturalsCollection();
        assert !nc.contains(0) : "should be empty";
        assert !nc.contains(1) : "should be empty";
        assert !nc.contains(2) : "should be empty";
        assert !nc.contains(300) : "should be empty";

        nc.add(2);
        nc.add(300);
        assert !nc.contains(0) : "should not contains 0";
        assert !nc.contains(1) : "should not contains 1";
        assert nc.contains(2) : "should contain 2";
        assert nc.contains(300) : "should contain 300";
        assert !nc.contains(40) : "should contain not 40";

        nc.delete(300);
        assert !nc.contains(0) : "should not contains 0";
        assert !nc.contains(1) : "should not contains 1";
        assert nc.contains(2) : "should contain 2";
        assert !nc.contains(300) : "should not contain 300";
        assert !nc.contains(40) : "should contain not 40";

        System.out.println("Natural collections working fine!");


    }


}
