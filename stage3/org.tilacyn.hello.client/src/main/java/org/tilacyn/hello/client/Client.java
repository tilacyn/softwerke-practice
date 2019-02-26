package org.tilacyn.hello.client;

import org.apache.felix.scr.annotations.*;
import org.osgi.service.component.ComponentContext;
import org.tilacyn.hello.Greeting;

/**
 * Consumer of the Greeting service
 * it invokes sayHello method when starting a bundle
 */
@Component
public class Client {
    @Reference(bind = "setGreeting"/*, unbind = "unbindGreeting"*/)
    private Greeting greeting;

    void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    void unbindGreeting(ComponentContext ctx) {
        this.greeting = null;
    }

    public void sayHello() {
        greeting.sayHello();
    }

    @Activate
    void activate(ComponentContext ctx) {
        sayHello();
    }

    @Deactivate
    void deactivate(ComponentContext ctx) {
    }
}
