package ConflictsDetection;

import java.util.function.Function;

public class FalseNegativeConflictsExperiments {
    
    //It should have a conflict however Infer doesn't catch
    public void leftDefAndRightUseItByFunctionInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        Person person = new Person("Henrique", Infer.defLeft(24), "Rua do Sol"); //left
        acc.setOwner(person);
        ExperimentsHelper.base();
        person.celebrateBirthday(); //right
        accDeposit100(acc); //right
    }

    private void accDeposit100(Account acc) {
        acc.deposit(100);
    }

    // With Functional Aproach
    public void leftDefAndRightUseItByFunctionInferConflict2() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();

        Function<Account, Account> depositFunction = account -> {
            account.deposit(100);
            return account;
        };

        Infer.useRight(acc, depositFunction);
    }

    // With Functional Aproach and depositOnlyPrint method
    public void leftDefAndRightUseItByFunctionInferConflict3() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        ExperimentsHelper.base();
        Function<Account, Account> depositFunction = account -> {
            account.depositOnlyPrint(100);
            return account;
        };

        Infer.useRight(acc, depositFunction);
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
