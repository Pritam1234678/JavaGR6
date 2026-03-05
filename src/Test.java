class A {
    void show(Object o) {
        System.out.println("A Object");
    }

    void show(String s) {
        System.out.println("A String");
    }
}

class B extends A {
    void show(Object o) {
        System.out.println("B String");
    }
    
}

public class Test {
    public static void main(String[] args) {

        A obj = new B();
        

        obj.show("hello");
    }
}
