public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
//        this.age = Infer.useBase(age);
        this.age = age;
    }

    public void celebrateBirthday() {
        age = age + 1;
        System.out.println("Happy birthday! You are now " + this.age + " years old.");
    }
}
