package org.tilacyn.hello.impl;

import org.osgi.framework.*;
import org.tilacyn.hello.Greeting;

import java.util.Hashtable;

/**
 * A bundle activator class for activating GreetingImpl service
 */
public class Activator implements BundleActivator {

    public void start(BundleContext ctx) throws Exception {
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put("osgi.command.scope", "practice");
        properties.put("osgi.command.function", new String[]{"hello"});
        ctx.registerService(Greeting.class.getName(), new GreetingImpl(), properties);
    }

    public void stop(BundleContext ctx) throws Exception {
    }
}
