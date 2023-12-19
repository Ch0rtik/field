package field;

import field.FieldAddition;
import properties.DistributiveBinaryOperator;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FieldMultiplication<T, R extends FieldAddition<T>> extends FieldOperatorAbstract<T> implements DistributiveBinaryOperator<T, R> {
    public FieldMultiplication(T identity, BinaryOperator<T> operation, UnaryOperator<T> inverseOperation) {
        super(identity, operation, inverseOperation);
    }
}