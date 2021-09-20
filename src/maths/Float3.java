package maths;

public class Float3 {

    int pointer;

    public float x() {
        return FloatsPool.getVector3ValueAt(this, 0);
    }

    public float y() {
        return FloatsPool.getVector3ValueAt(this, 1);
    }

    public float z() {
        return FloatsPool.getVector3ValueAt(this, 2);
    }

    public void setX(float value) {
        FloatsPool.setVector3ValueAt(this, 0, value);
    }

    public void setY(float value) {
        FloatsPool.setVector3ValueAt(this, 1, value);
    }

    public void setZ(float value) {
        FloatsPool.setVector3ValueAt(this, 2, value);
    }

    Float3(int pointer) {
        this.pointer = pointer;
    }

    public static Float3 create(float x, float y, float z) {
        Float3 vector = FloatsPool.getVector3Pointer();
        vector.setX(x);
        vector.setY(y);
        vector.setZ(z);
        return vector;
    }

    @Override
    public Float3 clone() {
        return create(x(), y(), z());
    }

    public int hashCode() {
        return Float.hashCode(x()) ^ Float.hashCode(y()) ^ Float.hashCode(z());
    }

    public void dispose() {
        FloatsPool.disposeVector3Pointer(this);
    }
}
