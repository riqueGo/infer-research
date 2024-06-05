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
        person.celebrateBirthday(); //Right use it and define it
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
        int i = Infer.left(foo1());
        base();
        int j = Infer.right(foo2());
    }

    public void analize9() {
        int x = Infer.left(1);
        int y = Infer.left(2);
        foo3(x+y);
    }

    public void analize10() {
        int x = Infer.left(1);
        base();
        int y = Infer.right(2);
        Infer.right(() -> Infer.left(() -> foo3(x+y)));
    }

    public void foo3(int z) {
        base();
        System.out.println(Infer.right(z));
    }


    //Não é DF
    //É CF (Confluence Flow)
    public void analize11() {
        boolean a = true; // left altera a
        base();
        boolean b = false; //right altera b

        if(a && b) { //a e b são utilizados na condicional
            //Do Something
        }
    }

    public void analize12(boolean c) {
        boolean a = Infer.left(false);
        int x;
        boolean b = Infer.right(false);

        if(Infer.right(Infer.left(foo4(Infer.left(a || c)) && foo4(Infer.right(b))))) {
            //Do something
        }
    }

    //Não é DF
    //É um PDG (Program Dependence Graph)
    public void analize13() {
        String s ="test"; //left altera valor de s
        base();
        if(s.length() >= 4) { //s é utilizado na condicional
            base();
            foo1(); //right chama uma função
        }
    }

    /*
     * @configFile .inferconfig1
     * Should clean age?
     */
    public void analize14() {
        Person person = new Person("Henrique", 24); //Left change age
        Person person2 = new Person("Luiz", 24);
        base();
        Infer.right(person2.getAge()); //Right use it
    }

    //É DF
    public void analize15() {
        int x = Infer.left(10);
        int y = x;
        int z = Infer.right(y);
    }

    //É DF
    public void analize16(boolean a) {
        int x = Infer.left(10);
        if(a) {
            Infer.right(() -> foo3(x));
        }
    }

    public int foo1() {
        int x = Infer.right(Infer.left(1));
        int y = Infer.left(1);
        return Infer.left(x + y);
    }

    public int foo2() {
        return innerFoo2();
    }

    public int innerFoo2() {
        return Infer.left(1);
    }

    public <T> T foo4(T value) {
        return value;
    }

    public void base(){}

}