package Class;

public class HelperClass {
    public int leftAttribute;
    public int rightAttribute;

    public HelperClass(){
        this.leftAttribute = 0;
        this.rightAttribute= 0;
    }

    public void setLeftAttribute(int value) {
        this.leftAttribute = value;
    }

    public void setRightAttribute(int value) {
        this.rightAttribute = value;
    }

    public void changeLeftAndRight(int value){
        this.rightAttribute = this.leftAttribute;
        this.leftAttribute = value;
    }
}
