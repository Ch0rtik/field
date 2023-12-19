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
        //System.out.println(floatField.getCharacteristic());

        System.out.println();

        Field<Double>.FieldExpression expression1 = floatField.createExpression(3.0);
        Field<Double>.FieldExpression expression2 = floatField.createExpression(2.0);

        Field<Double>.FieldExpression expression_add_1 = floatField.createExpression(expression1, Field.Action.ADD, expression2);

        Field<Double>.FieldExpression expression_add_2 = expression1.append(Field.Action.ADD, expression2);

        Field<Double>.FieldExpression expression_subtract = floatField.createExpression(
                expression1,
                Field.Action.SUBTRACT,
                expression2);

        Field<Double>.FieldExpression expression_multiply = floatField.createExpression(
                expression1,
                Field.Action.MULTIPLY,
                expression2);

        Field<Double>.FieldExpression expression_divide = floatField.createExpression(
                expression1,
                Field.Action.DIVIDE,
                expression2);

        System.out.println(expression_add_1);
        System.out.println(expression_add_1.reduce());
        System.out.println(expression_add_2);
        System.out.println(expression_add_2.reduce());
        System.out.println(expression_subtract);
        System.out.println(expression_subtract.reduce());
        System.out.println(expression_multiply);
        System.out.println(expression_multiply.reduce());
        System.out.println(expression_divide);
        System.out.println(expression_divide.reduce());
        System.out.println();
    }
}