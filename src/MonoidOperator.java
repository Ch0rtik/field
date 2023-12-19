import identity.IdentityOperator;
import properties.AssociativeBinaryOperator;
import properties.DistributiveBinaryOperator;

public interface MonoidOperator<T> extends AssociativeBinaryOperator<T>, IdentityOperator<T> {

}
