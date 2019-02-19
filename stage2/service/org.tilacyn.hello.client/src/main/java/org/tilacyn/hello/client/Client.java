package org.tilacyn.hello.client;

import org.osgi.framework.*;
import org.tilacyn.hello.Greeting;

/**
 * Consumer of the Greeting service
 * it invokes sayHello method when starting a bundle
 */
public class Client implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference = bundleContext.getServiceReference(Greeting.class.getName());
        ((Greeting) bundleContext.getService(reference)).sayHello();
    }

    public void stop(BundleContext bundleContext) throws Exception {

    }
}
