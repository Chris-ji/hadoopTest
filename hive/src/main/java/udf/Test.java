package udf;

/**
 * Created by Chris on 2019/2/18.
 */
public class Test {

    public static void main(String[] args) {
        System.out.print(B.c);
    }
}

class A {
    public static String c = "C";
    static {
        System.out.print("A");
    }
}

class B extends A{
    static {
        System.out.print("B");
    }
}
