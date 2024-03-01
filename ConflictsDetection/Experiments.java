package ConflictsDetection;

/**
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Account.java ./Infer.java ./Experiments.java ./Person.java ./AccountType.java
*/

public class Experiments {

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

    //############################### Conflicts ###############################

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

    /*  
     * Left change has been extracted
     * Since right has change the balance by a method, the whole method was extracted to rightExtracted
     * There is a conflict since left behavior program was changed
     */
    public void leftDefRightDefAndLeftUseItInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        base();
        rightSetBalance(acc); //right
        String message = Infer.useRight(new StringBuilder()
            .append("Account: ").append(acc.getAccountNumber())
            .append("\nBalance: ").append(Infer.useLeft(acc.getBalance())) //left
            .toString()
        );
        System.out.println(message);
    }

    //conflict detected with nested objects
    public void leftDefAndRightUseItInferConflictWithPerson() {
        Account acc = new Account("1", 0, AccountType.SAVING);
        Person person = new Person("Henrique", Infer.defLeft(24), "Rua do Sol"); //left
        acc.setOwner(person);
        base();
        System.out.println(Infer.useRight(acc.getOwner().getAge())); //right
    }

    //############################### Not Conflict ###############################

    /*  
     * Left change has been extracted
     * Whole Right attribution was extracted
     * There isn't a conflict since Left change balance and right used accountType
     */
    public void leftDefAndRightUseItInferNotConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); // left
        base();
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
        base();
        acc.setBalance(acc.getBalance()+100); //base
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
        base();
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
        base();
        System.out.println(Infer.useRight(acc.getOwner().getName())); //right
    }

    //############################### It should has a conflict ###############################

    //It should have a conflict however Infer doesn't catch
    public void leftDefAndRightUseItByFunctionInferConflict() {
        Account acc = new Account("1", Infer.defLeft(100), AccountType.SAVING); //left
        Person person = new Person("Henrique", Infer.defLeft(24), "Rua do Sol"); //left
        acc.setOwner(person);
        base();
        person.celebrateBirthday(); //right
        acc.deposit(100); //right
    }

    private void base(){}
}
