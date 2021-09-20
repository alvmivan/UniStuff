package maths;

import java.util.ArrayList;

import collections.NaturalsCollection;


public class StructuresPool<T> {

    private static final int NULL = 0;
    private static final int INITIAL_ELEMENTS_SIZE = 512;

    private final int structureSize;
    private final T cleanElement;
    private final ArrayList<T> memory;
    private final NaturalsCollection alivePointers;
    private final NaturalsCollection availablePointers;


    private int last;

    public StructuresPool(int structureSize, T cleanElement) {
        this.cleanElement = cleanElement;
        this.structureSize = structureSize;

        alivePointers = new NaturalsCollection();
        availablePointers = new NaturalsCollection();
        alivePointers.add(NULL);
        memory = new ArrayList<>(structureSize * INITIAL_ELEMENTS_SIZE);
        last = 1;
    }


    private int getPointer() {
        // todo: check available pointers first

        //noinspection UnnecessaryLocalVariable
        int pointer = last += structureSize;
        return pointer;
    }

    public int allocate() {
        int pointer = getPointer();
        alivePointers.add(pointer);
        availablePointers.delete(pointer);
        //todo: check memory size
        return pointer;
    }

    public void free(int pointer) {
        alivePointers.delete(pointer);
        availablePointers.add(pointer);
        clean(pointer);
    }

    private void clean(int pointer) {
        memory.set(pointer, cleanElement);//avoid add zero
        for (int i = 1; i < structureSize; i++) {
            memory.set(pointer + i, cleanElement);
        }
    }

    public void set(int pointer, int offset, T value) {
        validatePointerAndOffset(pointer, offset);
        memory.set(pointer + offset, value);
    }

    public T get(int pointer, int offset) {
        validatePointerAndOffset(pointer, offset);
        return memory.get(pointer + offset);
    }

    private void validatePointerAndOffset(int pointer, int offset) {
        if (pointer == 0) {
            throw new NullPointerException();
        }
        if (offset < 0 || offset >= structureSize) {
            throw new IndexOutOfBoundsException("offset should be on [0," + (structureSize - 1) + "]");
        }
    }


}
