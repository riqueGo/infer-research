/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Class.java ./HelperClass.java
*/

package Class;

public class Class {

    /**
     * There is no data flow, it shouldn't trigger a conflict
    */
    private void changeLeftAndRightSeparately() {
        HelperClass helperClass = new HelperClass();
        helperClass.setLeftAttribute(1);//left operation
        base();
        helperClass.setRightAttribute(1); //right operation
    }

    private void changeLeftAndRightTogether() {
        HelperClass helperClass = new HelperClass();
        helperClass.setLeftAttribute(1);//left operation
        base();
        helperClass.changeLeftAndRight(5); //right operation
    }

    private void base(){}
}
