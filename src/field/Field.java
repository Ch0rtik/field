package field;

public class Field<T>{
    private FieldAddition<T> add;
    private FieldMultiplication<T, FieldAddition<T>> multiply;
    private T additiveIdentity;
    private T multiplicativeIdentity;
    public Field(FieldAddition<T> add, FieldMultiplication<T, FieldAddition<T>> multiply){
        this.add = add;
        this.multiply = multiply;

        this.additiveIdentity = add.getIdentity();
        this.multiplicativeIdentity = multiply.getIdentity();
    }

    public T add(T first, T second) {
        return add.apply(first, second);
    }

    public T multiply(T first, T second) {
        return multiply.apply(first, second);
    }

    public T getAdditiveIdentity() {
        return additiveIdentity;
    }

    public T getMultiplicativeIdentity() {
        return multiplicativeIdentity;
    }

    public T negate(T a) {
        return add.invert(a);
    }
    public T reciprocate(T a) {
        return multiply.invert(a);
    }

    public int getCharacteristic() {
        T sum = multiplicativeIdentity;
        int i = 1;

        while (sum != additiveIdentity) {
            i += 2;
            sum = add(sum, multiplicativeIdentity);
            if(i == 1) {
                i = 0;
                break;
            }
        }

        return i;
    }

    public void setAdd(FieldAddition<T> add) {
        this.add = add;
        this.additiveIdentity = add.getIdentity();
    }

    public void setMultiply(FieldMultiplication<T, FieldAddition<T>> multiply) {
        this.multiply = multiply;
        this.multiplicativeIdentity = multiply.getIdentity();
    }
}
