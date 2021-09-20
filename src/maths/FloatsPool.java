package maths;

import java.util.LinkedList;
import java.util.Queue;

public class FloatsPool {

    private static final StructuresPool<Float> vectors3 = new StructuresPool<>(3, 0.0f);
    private static final Queue<Float3> availableStructures = new LinkedList<>();

    static Float3 getVector3Pointer() {
        int ptr = vectors3.allocate();
        if (availableStructures.isEmpty()) {
            return new Float3(ptr);
        }
        return availableStructures.poll();
    }

    static void disposeVector3Pointer(Float3 pointer) {
        vectors3.free(pointer.pointer);
        pointer.pointer = 0;
        availableStructures.add(pointer);
    }

    static float getVector3ValueAt(Float3 pointer, int offset) {
        return vectors3.get(pointer.pointer, offset);
    }

    static void setVector3ValueAt(Float3 pointer, int offset, float value) {
        vectors3.set(pointer.pointer, offset, value);
    }

}


