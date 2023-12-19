package field;

import field.FieldAddition;
import properties.DistributiveBinaryOperator;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FieldMultiplication<T> extends FieldOperatorAbstract<T> implements DistributiveBinaryOperator<T> {
    private FieldAddition<T> addition;
    public FieldMultiplication(T identity,
                               BinaryOperator<T> operation,
                               UnaryOperator<T> inverseOperation,
                               FieldAddition<T> addition) {
        super(identity, operation, inverseOperation);
        this.addition = addition;
    }

    @Override
    public T invert(T a) {
        if (a == addition.getIdentity()) {
            return null;
        } else {
            return super.invert(a);
        }
    }
}