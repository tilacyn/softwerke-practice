package org.tilacyn.hello.main;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.framework.Felix;
import org.osgi.framework.*;


/**
 * Main class that runs the application
 * It is taken from the osgi-in-action companion code
 */
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            final Map configMap = new HashMap();
            configMap.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");
            Felix m_framework = new Felix(configMap);
            m_framework.init();

            final BundleContext context = m_framework.getBundleContext();

            Bundle provider = context.installBundle("file:../org.tilacyn.hello/target/org.tilacyn.hello-1.0.0.jar");
            Bundle consumer = context.installBundle("file:../org.tilacyn.hello.client/target/org.tilacyn.hello.client-1.0.0.jar");

            m_framework.start();
            provider.start();

            consumer.start();
            consumer.stop();

            provider.stop();
            m_framework.stop();

        } catch (Exception ex) {
            System.err.println("Error starting program: " + ex);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
