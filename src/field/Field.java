package field;

import java.util.SplittableRandom;

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

    public FieldExpression createExpression(T value) {
        return new FieldExpression(value);
    }

    public FieldExpression createExpression(FieldExpression value1, Action action, FieldExpression value2) {
        return new FieldExpression(value1, action, value2);
    }

    public enum Action {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    public class FieldExpression {

        private T value;
        private boolean type = false;

        private FieldExpression left_node;
        private FieldExpression right_node;

        private Action action;

        private FieldExpression(FieldExpression left_node,
                               Action action,
                               FieldExpression right_node) {
            this.left_node = left_node;
            this.right_node = right_node;
            this.action = action;
            this.type = true;
        }

        private FieldExpression(T value) {
            this.value = value;
        }

        public FieldExpression append(Action action, FieldExpression expression) {
            return new FieldExpression(this,  action, expression);
        }

        public T reduce() {
            return !type ? value : switch (action) {
                case ADD -> add(left_node.reduce(), right_node.reduce());
                case SUBTRACT -> add(left_node.reduce(), negate(right_node.reduce()));
                case MULTIPLY -> multiply(left_node.reduce(), right_node.reduce());
                default -> multiply(left_node.reduce(), reciprocate(right_node.reduce()));
            };
        }

        @Override
        public String toString() {
            return !type ? value.toString() : switch (action) {
                case ADD -> left_node.toString() + " + " + right_node.toString();
                case SUBTRACT -> left_node.toString() + " - " + right_node.toString();
                case MULTIPLY -> left_node.toString() + " * " + right_node.toString();
                default -> left_node.toString() + " / " + right_node.toString();
            };
        }
    }
}
