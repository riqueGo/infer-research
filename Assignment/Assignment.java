/**
 * @description: dataflow conflict detected
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Assignment.java
 * @TODO: verify how increment operation could be assignment -> ('++', '+=', '-=')
*/

class Assignment {

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

    private void assignmentWithVarBefore(int base) {
        base = base + 5; //left operation
        base();
        int right = base; //right operation
    }

    private void assignmentWithVarAfter(int base) {
        base = getLeft(base + 5); //left operation
        base();
        int right = getRight(base); //right operation
    }

    /**
     * refactor would probably be get as conflict
    */
    private void assignmentForSameValueBefore() {
        boolean base = false;
        base = 1 > 5; //left operation
        base();
        boolean right = base; //right operation
    }

    private void assignmentForSameValueAfter() {
        boolean base = false;
        base = getLeft(1 > 5); //left operation
        base();
        boolean right = getRight(base); //right operation
    }

    /**
     * refactor would probably be get as conflict
    */
    private void printSameValueBefore(boolean base) {
        base = base; //left operation
        base();
        System.out.println(base); //right operation
    }

    private void printSameValueAfter(boolean base) {
        base = getLeft(base); //left operation
        base();
        System.out.println(getRight(base)); //right operation
    }

    private void base(){}

    private <T> T getLeft(T value) {
        return value;
    }

    private <T> T getRight(T value) {
        return value;
    }
}