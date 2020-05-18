/*
 * ==========================================================================================
 * =                            JAHIA'S ENTERPRISE DISTRIBUTION                             =
 * ==========================================================================================
 *
 *                                  http://www.jahia.com
 *
 * JAHIA'S ENTERPRISE DISTRIBUTIONS LICENSING - IMPORTANT INFORMATION
 * ==========================================================================================
 *
 *     Copyright (C) 2002-2020 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */
package org.jahia.samples.module.springfeature;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** A sample bundle activator to create an application context from a XML file */
public final class SpringActivator implements BundleActivator {
    private ClassPathXmlApplicationContext applicationContext;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        String[] configLocations = getConfigLocations();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            this.applicationContext = new ClassPathXmlApplicationContext(configLocations);
        } finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        if (applicationContext == null) {
            applicationContext.stop();
            applicationContext = null;
        }
    }

    private String[] getConfigLocations() {
        return new String[] {
                "/META-INF/spring/sample-applicationContext.xml"
        };
    }

}
