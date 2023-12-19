package field;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FieldAddition<T> extends FieldOperatorAbstract<T> {
    public FieldAddition(T identity, BinaryOperator<T> operation, UnaryOperator<T> inverseOperation) {
        super(identity, operation, inverseOperation);
    }
}
