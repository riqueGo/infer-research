public class Person {
    private String name;
    private String lastName;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getNameOrLastName(Boolean b) {
        if(b) {
            return name;
        }
        return lastName;
    }

    public void incAgeAndName(int age, String name) {
        this.age += age;
        this.name += name;
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
        age++;
        System.out.println("Happy birthday! You are now " + age + " years old.");
    }
}
