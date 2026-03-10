import java.util.ArrayList;

class A {
    static String s = "A";

    static {
        s = s + "B";
    }

    {
        s = s + "C";
    }

    A() {
        s = s + "D";
    }
}

public class Test1 {
    public static void main(String[] args) {
        new A();
        new A();
        System.out.println(A.s);
        ArrayList <Integer> ne= new ArrayList<>();
    }
}