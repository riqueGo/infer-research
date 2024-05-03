public class Analysis {

    /*
    * @configFile .inferconfig1
    * exemeplo básico
    */
    public void analize1() {
        Person person = new Person("Henrique", 24); //Left change age
        base();
        Infer.useRight(person.getAge()); //Right use it
    }

    /*
     * @configFile .inferconfig1
     */
    public void analize2() {
        Person person = new Person("Henrique", 24); //Left change age
        base();
        Infer.useRight(() -> person.celebrateBirthday()); //Right use it and define it
    }

    /*
     * @configFile .inferconfig2
     * como saber qual campo sendo alterado?
     */
    public void analize3() {
        Person person = new Person("Henrique", 24);
        base();
        Infer.useLeft(() -> person.celebrateBirthday()); //Left use and define age
        base();
        Infer.useRight(() -> person.celebrateBirthday()); //right use and define age
    }

    /*
     * @configFile .inferconfig2
     * como saber qual campo sendo alterado?
     */
    public void analize4() {
        Person person = new Person("Henrique", 24);
        Infer.useLeft(() -> person.setName("Luiz")); //Left change name
        base();
        String rightReceive = Infer.useRight(person.getName()); //right use it
    }

    /*
     * @configFile .inferconfig3
     * Como verificar que campos definidos estão sendo passados para outras funções?
     */
    public void analize5() {
        Person person = new Person("Henrique", 24); //Left change name from "Luiz" to "Henrique"
        base();
        if (isABigName(person.getName())) { //base
            System.out.println("Is a big name");
        } else {
            System.out.println("Is not a big name");
        }
    }

    public Boolean isABigName(String name) {
        return Infer.useRight(Infer.useRight(name).length()) >= Infer.useRight(8); //right changed from 4 to 8
    }

    /*
     * @configFile .inferconfig4
     * Algoritmo Antigo
     * Essa relação de def-use apenas por função não captura conflitos internos de metodos
     */
    public void analize6() {
        Person person = new Person("Henrique", Infer.defLeft(24)); //Left change age
        base();
        Infer.useRight(() -> person.celebrateBirthday()); //Right use it and define it
    }

    /*
     * @configFile .inferconfig5
     * exemeplo básico
     */
    public void analize7() {
        Person person = new Person("Henrique", 24); //Left change age
        Infer.useBase(() -> person.setAge(20)); //base
        Infer.useRight(person.getAge()); //Right use it
    }

    public void base(){}

}