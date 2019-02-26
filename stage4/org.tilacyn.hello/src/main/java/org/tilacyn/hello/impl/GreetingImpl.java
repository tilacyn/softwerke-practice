package org.tilacyn.hello.impl;

import org.osgi.framework.BundleContext;
import org.tilacyn.hello.Greeting;

/**
 * Simple implementation of Greeting interface
 * it prints Hello, <string>! to stdout
 */
public class GreetingImpl implements Greeting {
    public void hello(String greetingObject) {
        System.out.println("Hello, " + greetingObject + "!");
    }
}
