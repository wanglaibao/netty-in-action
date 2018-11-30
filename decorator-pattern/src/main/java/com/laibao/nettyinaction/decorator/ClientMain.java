package com.laibao.nettyinaction.decorator;

/**
 * @author laibao wang
 */
public class ClientMain {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(new ConcreteComponent()));
        component.doSomething();
    }
}
