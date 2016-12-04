package com.fincad.thermometer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the TemperatureImpl
 * 
 * @author Chris Baldwin
 */
public class ThermometerImplTest {
    
    public ThermometerImplTest() {
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
     * Test of getDefaultScale method, of class ThermometerImpl.
     */
    @Test
    public void testGetDefaultScale() {
        System.out.println("getDefaultScale");
        ThermometerImpl instance = new ThermometerImpl( new TemperatureSource() {
            @Override
            public double getTemperature() {
                return 0.0;
            }
            
        });
        TemperatureScale expResult = TemperatureScale.CELSIUS;
        TemperatureScale result = instance.getDefaultScale();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemperature method, of class ThermometerImpl.
     */
    @Test
    public void testGetTemperature_0args() {
        System.out.println("getTemperature");
        ThermometerImpl instance = new ThermometerImpl( new TemperatureSource() {
            @Override
            public double getTemperature() {
                return 0.0;
            }
            
        });
        double expResult = 0.0;
        double result = instance.getTemperature();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTemperature method, of class ThermometerImpl.
     */
    @Test
    public void testGetTemperature_ThermometerScaleCelsius() {
        System.out.println("getTemperatureCelsius");
        TemperatureScale scale = TemperatureScale.CELSIUS;
        ThermometerImpl instance = new ThermometerImpl( new TemperatureSource() {
            @Override
            public double getTemperature() {
                return 0.0;
            }
            
        });
        double expResult = 0.0;
        double result = instance.getTemperature(scale);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of getTemperature method, of class ThermometerImpl.
     */
    @Test
    public void testGetTemperature_ThermometerScaleFahrenheit() {
        System.out.println("getTemperatureFahrenheit");
        TemperatureScale scale = TemperatureScale.FAHRENHEIT;
        ThermometerImpl instance = new ThermometerImpl( new TemperatureSource() {
            @Override
            public double getTemperature() {
                return 0.0;
            }
            
        });
        double expResult = 32.0;
        double result = instance.getTemperature(scale);
        assertEquals(expResult, result, 32.0);
    }
}
