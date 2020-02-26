package cc.xpbootcamp.code_smell_kit.$15_speculative_generality;

public class Salesman extends Employee { //没有必要的类，过度设计
    public Salesman(String name, int age) {
        super(name, age);
    }
}
