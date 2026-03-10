class Parent {

    static String s = "P";

    static {
        s += "A";
    }

    Parent() {
        s += "B";
    }
}

class Child extends Parent {

    static {
        s += "C";
    }
    

    Child() {
        s += "D";
    }
}

public class Test2 {

    public static void main(String[] args) {
        new Child();
        new Child();
        System.out.println(Child.s);
    }
}