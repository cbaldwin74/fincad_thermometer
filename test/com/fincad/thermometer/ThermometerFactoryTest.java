/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fincad.thermometer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chris Baldwin
 */
public class ThermometerFactoryTest {
    
    public ThermometerFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createThermometer(source) method, of class ThermometerFactory.
     */
    @Test
    public void testCreateThermometerWithSource() {
        System.out.println("createThermometerWithSource");
        TemperatureSource source = new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, 1.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        };
        Thermometer result = ThermometerFactory.createThermometer(source);
        assertNotNull(result);
        assertTrue(result instanceof Thermometer);
    }
    
    /**
     * Test of createThermometer(source, scale) method, of class ThermometerFactory.
     */
    @Test
    public void testCreateThermometerWithSourceAndScale() {
        System.out.println("createThermometerWithSourceAndScale");
        TemperatureSource source = new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, 1.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        };
        TemperatureScale scale = TemperatureScale.FAHRENHEIT;
        Thermometer result = ThermometerFactory.createThermometer(source, scale);
        assertNotNull(result);
        assertTrue(result instanceof Thermometer);
    }
}
