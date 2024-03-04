package ConflictsDetection;

/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Account.java ./Infer.java ./Experiments.java ./Person.java ./AccountType.java
*/

public class ExperimentsHelper {

    //############################### Example Scenario ###############################

    // Original program
    public void leftDefAndRightUseItBase() {
        Account acc = new Account("1", 0, AccountType.SAVING);
        base();
        String message = new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .toString();
        System.out.println(message);
    }
    
    /*  
     * Example Merge program
     * left changed the balance in constructor
     * right use it to build the message
     */
    public void leftDefAndRightUseItMerge() {
        Account acc = new Account("1", 100, AccountType.SAVING); // left
        base();
        String message = new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString();
        System.out.println(message);
    }

    /*
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There is a conflict since Left change balance and right use it (Data Flow)
     */
    public void leftDefAndRightUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        base();
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString()
        );
        System.out.println(message);
    }

    // Method created just to represent base code 
    public static void base(){}
}
