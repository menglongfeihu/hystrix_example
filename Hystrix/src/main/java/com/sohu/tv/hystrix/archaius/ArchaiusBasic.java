/**
 * Copyright (c) 2012 Sohu. All Rights Reserved
 */
package com.sohu.tv.hystrix.archaius;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class ArchaiusBasic {
    @Before
    public void setUp() throws Exception {
        ConfigurationManager.loadCascadedPropertiesFromResources("application");
        System.setProperty("archaius.configurationSource.defaultFileName","config.properties"); //Another option
    }

    @Test
    public void testBasicStringProps() {
        DynamicStringProperty sampleProp = DynamicPropertyFactory.getInstance().getStringProperty("stringprop", "");
        System.out.println(sampleProp.get());
        assertEquals(sampleProp.get(), "propvalue");
    }
}
