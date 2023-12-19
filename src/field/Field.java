package field;

public class Field<T>{
    private FieldAddition<T> add;
    private FieldMultiplication<T> multiply;
    private T additiveIdentity;
    private T multiplicativeIdentity;
    public Field(FieldAddition<T> add, FieldMultiplication<T> multiply){
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

    public void setMultiply(FieldMultiplication<T> multiply) {
        this.multiply = multiply;
        this.multiplicativeIdentity = multiply.getIdentity();
    }

    public Expression createExpression(T value) {
        return new Expression(value);
    }

    public Expression createExpression(Expression value1, Action action, Expression value2) {
        return new Expression(value1, action, value2);
    }

    public enum Action {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    public Field<T> copy() {
        FieldAddition<T> addition = new FieldAddition<>(add.getIdentity(), add.operation, add.inverseOperation);
        return new Field<>(
                addition,
                new FieldMultiplication<>(multiply.getIdentity(), multiply.operation, multiply.inverseOperation, addition)
        );
    }

    public class Expression {

        private T value;
        private boolean type = false;

        private Expression left_node;
        private Expression right_node;

        private Action action;

        private Expression(Expression left_node,
                           Action action,
                           Expression right_node) {
            this.left_node = left_node;
            this.right_node = right_node;
            this.action = action;
            this.type = true;
        }

        private Expression(T value) {
            this.value = value;
        }

        public Expression append(Action action, Expression expression) {
            return new Expression(this,  action, expression);
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
                case ADD -> "(" + left_node.toString() + " + " + right_node.toString() + ")";
                case SUBTRACT -> "(" + left_node.toString() + " - " + right_node.toString() + ")";
                case MULTIPLY -> left_node.toString() + " * " + right_node.toString();
                default -> left_node.toString() + " / " + right_node.toString();
            };
        }
    }
}
