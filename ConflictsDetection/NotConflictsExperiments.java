package ConflictsDetection;

public class NotConflictsExperiments {
    /*  
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There isn't a conflict since Left change balance and right used accountType
     */
    public void leftDefAndRightUseItInferNotConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nType: ").append(acc.getAccountType()) //right
            .toString()
        );
        System.out.println(message);
    }

    /*  
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There isn't a conflict since base has sanitized balance
     */
    public void leftDefBaseDefAndRightUseItInferNotConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        acc.setBalance(100); //base
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString()
        );
        System.out.println(message);
    }

    /*
     * Left change has been extracted
     * Since right has change the balance by a method, the whole method was extracted to rightExtracted
     * However left doesn't use the value changed,
     * so there is no conflict since left and right behavior was preserved
     */
    public void leftDefRightDefAndLeftUseItInferNotConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        rightSetBalance(acc); //right
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nType: ").append(Infer.useLeft(acc.getAccountType())) //left
            .toString()
        );
        System.out.println(message);
    }

    private void rightSetBalance(Account acc) {
        acc.setBalance(100);
    }

    //there wasn't a conflict since right uses a different field
    public void leftDefAndRightUseItInferNotConflictWithPerson() {
        Account acc = new Account("1", 0, AccountType.SAVING);
        Person person = new Person("Henrique", Infer.defLeft(24), "Rua do Sol"); //left
        acc.setOwner(person);
        ExperimentsHelper.base();
        System.out.println(Infer.useRight(acc.getOwner().getName())); //right
    }
}
