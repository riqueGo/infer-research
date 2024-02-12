/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Condition.java
*/

class Condition {

    /**
     * condition true by optimization gets only if statement
    */
    private void conditionTrueBefore() {
        if (1 == 1) { //left operation
            base();
            System.out.println("Right Inside If"); //right operation
        } else {
            System.out.println("Right inside Else"); //right operation
        }
    }

    private void conditionTrueAfter() {
        if (getLeft(1 == 1)) { //left operation
            base();
            System.out.println(getRight("Right Inside If")); //right operation
        } else {
            System.out.println(getRight("Right inside Else")); //right operation
        }
    }

    /**
     * condition false by optimization gets only else statement
    */
    private void conditionFalseBefore() {
        if (1 != 1) { //left operation
            base();
            System.out.println("Right Inside If"); //right operation
        } else {
            System.out.println("Right inside Else"); //right operation
        }
    }

    private void conditionFalseAfter() {
        if (getLeft(1 != 1)) { //left operation
            base();
            System.out.println(getRight("Right Inside If")); //right operation
        } else {
            System.out.println(getRight("Right inside Else")); //right operation
        }
    }

    /**
     * when the optimization can not be done, the analysis get inside the condition
    */
    private void conditionBlindIfBefore(boolean leftBool) { //left operation
        if (leftBool) { //left operation
            base();
            System.out.println("Right inside If"); //right operation
        } else {
            System.out.println("Right inside Else"); //right operation
        }
    }

    private void conditionBlindIfAfter(boolean leftBool) { //left operation
        if (getLeft(leftBool)) { //left operation
            base();
            System.out.println(getRight("Right inside If")); //right operation
        } else {
            System.out.println(getRight("Right inside Else")); //right operation
        }
    }

    private void base(){}

    private <T> T getLeft(T value) {
        return value;
    }

    private <T> T getRight(T value) {
        return value;
    }
}
