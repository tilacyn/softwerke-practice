package org.tilacyn.hello.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Service;
import org.tilacyn.hello.Greeting;
import org.apache.felix.scr.annotations.Component;

/**
 * Simple implementation of Greeting interface
 * it prints Hello, <string>! to stdout
 */
@Component(immediate = true)
@Service
public class GreetingImpl implements Greeting {
    private final String greetingObject = "Declarative Services World";

    public GreetingImpl() {
    }

    public void sayHello() {
        System.out.println("Hello, " + greetingObject + "!");
    }

    @Activate
    void activate() {
    }

    @Deactivate
    void deactivate() {
    }
}
