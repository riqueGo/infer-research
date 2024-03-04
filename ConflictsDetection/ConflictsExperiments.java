package ConflictsDetection;

public class ConflictsExperiments {
    /*
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There is a conflict since Left change balance and right use it (Data Flow)
     */
    public void leftDefAndRightUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(acc.getBalance()) //right
            .toString()
        );
        System.out.println(message);
    }

    // Alternative way
    public void leftDefAndRightUseItInferConflict2() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        StringBuilder message1 = new StringBuilder().append("Account: ").append(acc.getAccountNumber());
            
        StringBuilder message2 = Infer.useRight(message1.append("\nBalance: ").append(acc.getBalance())); //right
        
        String message = message2.toString();
        
        System.out.println(message);
    }

    /*  
     * Left change has been extracted
     * Since right has change the balance by a method, the whole method was extracted to rightExtracted
     * There is a conflict since left behavior program was changed
     */
    public void leftDefRightDefAndLeftUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        ExperimentsHelper.base();
        rightSetBalance(acc); //right
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(Infer.useLeft(acc.getBalance())) //left
            .toString()
        );
        System.out.println(message);
    }
    private void rightSetBalance(Account acc) {
        acc.setBalance(100);
    }

    //conflict detected with nested objects
    public void leftDefAndRightUseItInferConflictWithPerson() {
        Account acc = new Account("1", 0, AccountType.SAVING);
        Person person = new Person("Henrique", Infer.defLeft(24), "Rua do Sol"); //left
        acc.setOwner(person);
        ExperimentsHelper.base();
        System.out.println(Infer.useRight(acc.getOwner().getAge())); //right
    }
}
