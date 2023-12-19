import field.Field;
import field.FieldAddition;
import field.FieldMultiplication;

public class Main {
    public static void main(String[] args) {
        FieldAddition<Double> floatSum = new FieldAddition<>(0.0, Double::sum, (Double a) -> -a);
        FieldMultiplication<Double, FieldAddition<Double>> floatMultiply = new FieldMultiplication<>(1.0, (Double a, Double b) -> a * b, (Double a) -> 1/a);
        Field<Double> floatField = new Field<>(floatSum, floatMultiply);

        System.out.println(floatField.add(10.0, 5.0));
        System.out.println(floatField.multiply(10.0, 5.0));
        System.out.println(floatField.negate(10.0));
        System.out.println(floatField.reciprocate(5.0));
        System.out.println(floatField.getAdditiveIdentity());
        System.out.println(floatField.getMultiplicativeIdentity());
        System.out.println(floatField.getCharacteristic());

    }
}