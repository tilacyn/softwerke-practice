package org.tilacyn.hello.impl;

import org.osgi.framework.*;
import org.tilacyn.hello.Greeting;

/**
 * A bundle activator class for activating GreetingImpl service
 */
public class Activator implements BundleActivator {

    public void start(BundleContext ctx) throws Exception {
        ctx.registerService(Greeting.class.getName(), new GreetingImpl("OSGi World"), null);
    }

    public void stop(BundleContext ctx) throws Exception {
    }
}
