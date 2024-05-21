public class Analysis {

    /*
    * @configFile .inferconfig1
    * exemeplo básico
    */
    public void analize1() {
        Person person = new Person("Henrique", 24); //Left change age
        base();
        Infer.right(person.getAge()); //Right use it
    }

    /*
     * @configFile .inferconfig1
     */
    public void analize2() {
        Person person = new Person("Henrique", 24); //Left change age
        base();
        Infer.right(() -> person.celebrateBirthday()); //Right use it and define it
    }

    /*
     * @configFile .inferconfig1
     */
    public void analize3() {
        Person person = new Person("Henrique", 24);
        person.celebrateBirthday(); //Left use and define age
        base();
        Infer.right(() -> person.celebrateBirthday()); //right use and define age
    }

    /*
     * @configFile .inferconfig2
     * como saber qual campo sendo alterado?
     */
    public void analize4() {
        Person person = new Person("Henrique", 24);
        person.setName("Luiz"); //Left change name
        base();
        String rightReceive = Infer.right(person.getName()); //right use it
    }

    /*
     * @configFile .inferconfig2
     */
    public void analize5() {
        Person person = new Person("Henrique", 24); //Left change name from "Luiz" to "Henrique"
        if (isABigName(person.getName())) { //base
            System.out.println("Is a big name");
        } else {
            System.out.println("Is not a big name");
        }
    }

    public Boolean isABigName(String name) {
        return Infer.right(name).length() >= 8; //right changed from 4 to 8
    }

    /*
     * @configFile .inferconfig1
     * Should clean age?
     */
    public void analize7() {
        Person person = new Person("Henrique", 24); //Left change age
        person.setAge(20); //base
        Infer.right(person.getAge()); //Right use it
    }

    /*
     * @configFile .inferconfig3
     * How to detect dataflow in functionss?
     */
    public void analize8() {
        int i = Infer.left(function1());
        base();
        int j = Infer.right(function2());
    }

    public int function1() {
        int x = Infer.right(Infer.left(1));
        int y = Infer.left(1);
        return Infer.left(x + y);
    }

    public int function2() {
        return innerFunction2();
    }

    public int innerFunction2() {
        return Infer.left(1);
    }

    public void base(){}

}