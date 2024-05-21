public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return Infer.right(name);
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return Infer.right(age);
    }
    public void setAge(int age) {
//        this.age = Infer.useBase(age);
        this.age = age;
    }

    public void celebrateBirthday() {
        age = Infer.right(age) + Infer.right(1);
        Infer.right(() -> System.out.println(Infer.right("Happy birthday! You are now ") + Infer.right(age) + Infer.right(" years old.")));
    }
}
