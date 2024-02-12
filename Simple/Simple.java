/**
 * @description: dataflow conflict detected
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Simple.java
*/

class Simple {

    private void assignmentBefore() {
        int base = 10;
        base = base + 5; //left operation
        base();
        int right = base; //right operation
    }

    private void assignmentAfter() {
        int base = 10;
        base = getLeft(base + 5); //left operation
        base();
        int right = getRight(base); //right operation
    }

    private void base(){}

    private <T> T getLeft(T value) {
        return value;
    }

    private <T> T getRight(T value) {
        return value;
    }
}