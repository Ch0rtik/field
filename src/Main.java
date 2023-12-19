import field.Field;
import field.FieldAddition;
import field.FieldMultiplication;

public class Main {
    public static void main(String[] args) {
        //Создание поля над double
        FieldAddition<Double> doubleSum
                = new FieldAddition<>(0.0, Double::sum, (Double a) -> -a);
        FieldMultiplication<Double> doubleMultiply
                = new FieldMultiplication<>(1.0, (Double a, Double b) -> a * b, (Double a) -> 1/a, doubleSum);
        Field<Double> doubleField
                = new Field<>(doubleSum, doubleMultiply);

        System.out.println("Actions: ");
        //Проверка действий
        System.out.println(doubleField.add(10.0, 5.0));
        System.out.println(doubleField.multiply(10.0, 5.0));
        System.out.println(doubleField.negate(10.0));
        System.out.println(doubleField.reciprocate(5.0));
        System.out.println(doubleField.getAdditiveIdentity());
        System.out.println(doubleField.getMultiplicativeIdentity());
        System.out.println(doubleField.getCharacteristic());

        System.out.println();

        //Проверка составления выражений
        System.out.println("Expressions: ");
        Field<Double>.Expression expression1 = doubleField.createExpression(3.0);
        Field<Double>.Expression expression2 = doubleField.createExpression(2.0);

        Field<Double>.Expression expression_add_1 = doubleField.createExpression(
                expression1,
                Field.Action.ADD,
                expression2);

        Field<Double>.Expression expression_add_2 = expression1.append(Field.Action.ADD, expression2);

        Field<Double>.Expression expression_subtract = doubleField.createExpression(
                expression1,
                Field.Action.SUBTRACT,
                expression2);

        Field<Double>.Expression expression_multiply = doubleField.createExpression(
                expression1,
                Field.Action.MULTIPLY,
                expression2);

        Field<Double>.Expression expression_divide = doubleField.createExpression(
                expression1,
                Field.Action.DIVIDE,
                expression2);
        Field<Double>.Expression long_expression = doubleField.createExpression(
                doubleField.createExpression(
                                    doubleField.createExpression(doubleField.createExpression(2.0),
                                    Field.Action.ADD,
                                    doubleField.createExpression(3.0)),
                Field.Action.MULTIPLY,
                doubleField.createExpression(3.0)),
                Field.Action.SUBTRACT,
                doubleField.createExpression(2.0)
        );
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
        System.out.println(long_expression);
        System.out.println(long_expression.reduce());
        System.out.println();

        System.out.println("Copy and change properties:");

        Field<Double> doubleFieldNew = doubleField.copy();

        //Ради примера подставим неправильное действие
        doubleSum = new FieldAddition<>(0.0, (Double a, Double b) -> a - b, (Double a) -> -a);

        doubleField.setAdd(doubleSum);

        Field<Double>.Expression sum1 = doubleField.createExpression(
                doubleField.createExpression(3.0),
                Field.Action.ADD,
                doubleField.createExpression(2.0)
        );

       Field<Double>.Expression sum2 = doubleFieldNew.createExpression(
               doubleFieldNew.createExpression(3.0),
               Field.Action.ADD,
               doubleFieldNew.createExpression(2.0)
       );

        System.out.println(sum1);
        System.out.println(sum1.reduce());
        System.out.println(sum2);
        System.out.println(sum2.reduce());
    }
}