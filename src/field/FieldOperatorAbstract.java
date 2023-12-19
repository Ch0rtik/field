package field;

import identity.IdentityOperatorAbstract;
import identity.InversibleOperator;
import properties.AssociativeBinaryOperator;
import properties.CommutativeBinaryOperator;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class FieldOperatorAbstract<T> extends IdentityOperatorAbstract<T> implements AssociativeBinaryOperator<T>, CommutativeBinaryOperator<T>, InversibleOperator<T> {
    protected BinaryOperator<T> operation;
    protected UnaryOperator<T> inverseOperation;

    public FieldOperatorAbstract(T identity, BinaryOperator<T> operation, UnaryOperator<T> inverseOperation) {
        this.identity = identity;
        this.operation = operation;
        this.inverseOperation = inverseOperation;
    }

    @Override
    public T invert(T a) {
        return inverseOperation.apply(a);
    }

    @Override
    protected T regularApply(T first, T second) {
        return operation.apply(first, second);
    }

    @Override
    public <V> BiFunction<T, T, V> andThen(Function<? super T, ? extends V> after) {
        return super.andThen(after);
    }
}