package ConflictsDetection;

/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Account.java ./Infer.java ./Experiments.java ./Person.java
*/

public class Experiments {

    public void leftDefVarAndRightUseItBase() {
        Account acc = new Account("1", 0);
        base();
        String message = new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .toString();
        System.out.println(message);
    }
    
    public void leftDefVarAndRightUseItMerge() {
        Account acc = new Account("1", 100); // left
        base();
        String message = new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString();
        System.out.println(message);
    }

    public void leftDefVarAndRightUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100)); // left
        base();
        String message = Infer.useRight(new StringBuilder()
                .append("Account: ").append(acc.getAccountNumber())
                .append("\nBalance: ").append(acc.getBalance()) //right
                .toString()
            );
        System.out.println(message);
    }

    public void leftDefVarAndRightUseItInferNotConflict() {
        Account acc = new Account("1", Infer.defLeft(100)); // left
        base();
        String message = Infer.useRight(new StringBuilder()
                .append("Account: ").append(acc.getAccountNumber())
                .toString()
            );
        System.out.println(message);
    }

    private void base(){}
}
