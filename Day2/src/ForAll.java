class Util {

    static String s = "A";

    static {
        s += "B";
    }

    static String mix(String a, StringBuilder b, StringBuffer c) {

        a += "X";
        b.append("Y");
        c.append("Z");

        String t = a + b + c;

        return t.intern();
    }
}

public class ForAll {

    public static void main(String[] args) {

        String s1 = "AB";
        String s2 = new String("AB");

        StringBuilder sb = new StringBuilder("C");
        StringBuffer  sf = new StringBuffer("D");

        String r1 = Util.mix(s1, sb, sf);
        String r2 = Util.mix(s2, sb, sf);

        String r3 = "ABXCYDZ";

        System.out.println(r1 == r2);
        System.out.println(r1 == r3);
        System.out.println(sb);
        System.out.println(sf);
        System.out.println(Util.s);
    }
}