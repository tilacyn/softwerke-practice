package org.tilacyn.hello.client;

import org.apache.felix.scr.annotations.*;
import org.tilacyn.hello.Greeting;

/**
 * Consumer of the Greeting service
 * it invokes sayHello method when starting a bundle
 */
@Component
public class Client {
    @Reference(bind = "setGreeting")
    private Greeting greeting;

    void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    public void sayHello() {
        greeting.sayHello();
    }

    @Activate
    void activate() {
        sayHello();
    }

    @Deactivate
    void deactivate() {
    }
}
