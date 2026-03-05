class Test1 {

    void show(Integer x) {
        System.out.println("Integer");
    }

    void show(long x) {
        System.out.println("long");
    }

    public static void main(String[] args) {

        Test1 t = new Test1();
        t.show(10);
    }
}