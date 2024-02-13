/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./POO.java
*/

package Class;

public class Class {

    private void changeLeftAndRightSeparately() {
        HelperClass helperClass = new HelperClass();
        helperClass.changeLeftTo1();//left operation
        base();
        helperClass.changeRightTo1(); //right operation
    }

    private void changeLeftAndRightTogether() {
        HelperClass helperClass = new HelperClass();
        helperClass.changeLeftTo1();//left operation
        base();
        helperClass.changeLeftAndRightTo2(); //right operation
    }

    private void base(){}

    private <T> T getLeft(T value) {
        return value;
    }

    private <T> T getRight(T value) {
        return value;
    }
}
