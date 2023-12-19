package identity;

import identity.IdentityOperator;

public interface InversibleOperator<T> extends IdentityOperator<T> {
    T invert(T a);
}
