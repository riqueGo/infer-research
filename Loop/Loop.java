/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Loop.java
*/

package Loop;

public class Loop {
    private void whileTrueBefore() {
        while (true) { //left operation
            base();
            System.out.println("Hello right"); //right operation
            break;
        }
    }

    private void whileTrueAfter() {
        while (getLeft(true)) { //left operation
            base();
            System.out.println(getRight("Hello right")); //right operation
            break;
        }
    }

    private void whileFalseBefore() {
        int i = 1;
        int j = 1; //left changed value 2 -> 1
        while (i < j) { //left operation
            base();
            System.out.println("Hello right"); //right operation
            i++;
        }
    }

    private void whileFalseAfter() {
        int i = 1;
        int j = getLeft(1); //left changed value 2 -> 1
        while (i < j) { //left operation
            base();
            System.out.println(getRight("Hello right")); //right operation
            i++;
        }
    }

    private void forTrueBefore() {
        for(int i = 0; i < 1; i++){//left changed 2 -> 1
            base();
            System.out.println("Hello right");//right changed
        }
    }

    private void forTrueAfter() {
        for(int i = 0; getLeft(i < 1); i++){//left changed 2 -> 1
            base();
            System.out.println(getRight("Hello right"));//right changed
        }
    }

    private void forFalseBefore() {
        for(int i = 0; i < 0; i++){//left changed 1 -> 0
            base();
            System.out.println("Hello right");//right changed
        }
    }

    private void forFalseAfter() {
        for(int i = 0; getLeft(i < 0); i++){//left changed 1 -> 0
            base();
            System.out.println(getRight("Hello right"));//right changed
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
