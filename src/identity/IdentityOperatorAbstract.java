package identity;

public abstract class IdentityOperatorAbstract<T> implements IdentityOperator<T> {
    protected T identity;

    @Override
    public T apply(T first, T second) {
        if (first == identity || second == identity) {
            return first == identity? second : first;
        } else {
            return regularApply(first, second);
        }
    }

    protected abstract T regularApply(T first, T second);

    public T getIdentity() {
        return identity;
    }
}
