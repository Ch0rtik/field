package identity;

import java.util.function.BinaryOperator;

public interface IdentityOperator<T> extends BinaryOperator<T> {
    T getIdentity();
}
