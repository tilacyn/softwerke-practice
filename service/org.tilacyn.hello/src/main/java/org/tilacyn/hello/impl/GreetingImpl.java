package org.tilacyn.hello.impl;

import org.tilacyn.hello.Greeting;

/**
 * Simple implementation of Greeting interface
 * it prints Hello, <string>! to stdout
 */
public class GreetingImpl implements Greeting {
    private String greetingObject;
    GreetingImpl(String greetingObject) {
        this.greetingObject = greetingObject;
    }

    public void sayHello() {
        System.out.println("Hello, " + greetingObject + "!");
    }
}
