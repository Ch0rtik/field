package properties;

import java.util.function.BinaryOperator;

@FunctionalInterface
public interface DistributiveBinaryOperator<T, R extends BinaryOperator<T>> extends BinaryOperator<T> {

}
