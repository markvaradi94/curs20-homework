package ro.fasttrackit.mvnbase;

public class HelloMaven {

    public static void main(String[] args) {
        System.out.println(new HelloMaven().sayHello());
    }

    public String sayHello() {
        return "Hello Maven";
    }

}
