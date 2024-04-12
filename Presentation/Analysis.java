public class Analysis {

    /*
    * @configFile .inferconfig1
    * exemeplo básico
    */
    public void analize1() {
        Person person = new Person("Henrique", 24); //left change age
        base();
        int rightReceive = Infer.useRight(person.getAge());
    }

    /*
     * @configFile .inferconfig1
     */
    public void analize2() {
        Person person = new Person("Henrique", 24); //left change age
        base();
        Infer.useRight(() -> person.celebrateBirthday()); //Right use it and define it
    }

    /*
     * @configFile .inferconfig2
     */
    public void analize3() {
        Person person = new Person("Henrique", 24);
        base();
        Infer.useLeft(() -> person.celebrateBirthday()); //left use and define age
        base();
        Infer.useRight(() -> person.celebrateBirthday()); //right use and define age
    }

    /*
     * @configFile .inferconfig2
     * como saber qual campo sendo alterado?
     */
    public void analize4() {
        Person person = new Person("Henrique", 24);
        Infer.useLeft(() -> person.setName("Luiz")); //left change name
        base();
        String rightReceive = Infer.useRight(person.getName());
    }

    /*
     * @configFile .inferconfig2
     * Como verificar que campos definidos estão sendo passados para outras funções?
     */
    public void analize5() {
        Person person = new Person("Henrique", 24);
        Infer.useLeft(() -> person.setName("Luiz")); //left change name
        base();
        if (isABigName(person.getName())) { //base
            System.out.println("Is a big name");
        } else {
            System.out.println("Is not a big name");
        }
    }

    public void base(){}

    public Boolean isABigName(String name) {
        return name.length() >= Infer.useRight(8); //right changed from 4 to 8
    }
}