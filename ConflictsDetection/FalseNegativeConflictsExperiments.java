package ConflictsDetection;

import java.util.function.Function;

public class FalseNegativeConflictsExperiments {
    
    /*  
     * It doesn't get conflict extracting method
     */
    public void leftDefAndRightUseItByFunctionInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();
        accDeposit100(acc); //right
    }

    private void accDeposit100(Account acc) {
        acc.deposit(100);
    }

    /*  
     * It doesn't get conflict with functional approach
     */
    public void leftDefAndRightUseItByFunctionInferConflict2() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();

        Function<Account, Account> depositFunction = account -> { //right
            account.deposit(100);
            return account;
        };

        Infer.useRight(acc, depositFunction);
    }

    /*  
     * It doesn't get conflict even though depositOnlyPrint is in pulse-taint-sinks
     */
    public void leftDefAndRightUseItByFunctionInferConflict3() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();
        acc.depositOnlyPrint(100); //right
    }

    /*  
     * With Functional Aproach and depositOnlyPrint method
     * It gets conflict because depositOnlyPrint doesn't define balance, only uses
     */
    public void leftDefAndRightUseItByFunctionInferConflict4() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();
        Function<Account, Account> depositFunction = account -> { //right
            account.depositOnlyPrint(100);
            return account;
        };

        Infer.useRight(acc, depositFunction);
    }

    /*  
     * extracting depositOnlyPrint method to accDeposit100OnlyPrint
     * It gets conflict because depositOnlyPrint doesn't define balance, only uses
     */
    public void leftDefAndRightUseItByFunctionInferConflict5() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();
        accDeposit100OnlyPrint(acc); //right
    }

    private void accDeposit100OnlyPrint(Account acc) {
        acc.depositOnlyPrint(100);
    }

    /*  
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There whould be a conflict since
     * base uses balance changed by left to def a new balance
     */
    public void leftDefBaseDefAndRightUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        acc.setBalance(acc.getBalance()+100); //base
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString()
        );
        System.out.println(message);
    }
}
