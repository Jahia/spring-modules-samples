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
package org.jahia.samples.module.springembedded;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringInfo implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(SpringInfo.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("Spring is provided by bundle {}", getSpringOrigin(applicationContext));
        logger.info(
                "Found {} bean definition(s): {}",
                applicationContext.getBeanDefinitionCount(),
                applicationContext.getBeanDefinitionNames()
        );
    }

    private String getSpringOrigin(ApplicationContext applicationContext) {
        Bundle bundle = FrameworkUtil.getBundle(applicationContext.getClass());
        return String.format("%s v%s [%d]",
                bundle.getSymbolicName(),
                bundle.getVersion(),
                bundle.getBundleId()
        );
    }

}
