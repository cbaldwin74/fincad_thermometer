package com.fincad.thermometer;

import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.fincad.thermometer.TemperatureTrend;

/**
 * Test the ThermometerObserver alone and attached to a Thermometer.
 * 
 * @author Chris Baldwin
 */
public class ThermometerObserverTest implements ThermometerObserver.ThermometerObserverListener {
    private int called;
    
    public ThermometerObserverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        called = 0;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of update method, of class ThermometerObserver.
     */
    @Test
    public void testUpdateOnlyOnce() {
        System.out.println("updateOnlyOnce");
        Observable o = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {0.0, 1.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
            
        });

        ThermometerObserver instance = new ThermometerObserver("updateOnlyOnce", 
                0.0, TemperatureScale.CELSIUS, TemperatureTrend.FALLING, true, this);
        Object o1 = new Double(1.0);
        instance.update(o, o1);
        o1 = new Double(0.0);
        instance.update(o, o1);
        assertEquals(1, called);
    }

    /**
     * Test for the Rising trend 
     */
    @Test
    public void testUpdateRising() {
        System.out.println("updateRising");
        Observable o = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {0.0, 1.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
            
        });
        
        ThermometerObserver instance = new ThermometerObserver("updateRising", 
                0.0, TemperatureScale.CELSIUS, TemperatureTrend.RISING, false, this);
        Object o1 = 0.0;
        instance.update(o, o1);
        
        o1 = 1.0;
        instance.update(o, o1);
        
        o1 = -1.0;
        instance.update(o, o1);
        
        o1 = 1.0;
        instance.update(o, o1);

        assertEquals(2, called);
    }

    /**
     * Test for the Rising trend 
     */
    @Test
    public void testUpdateFalling() {
        System.out.println("updateRising");
        Observable o = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {0.0, 1.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
            
        });
        
        ThermometerObserver instance = new ThermometerObserver("updateFalling", 
                0.0, TemperatureScale.CELSIUS, TemperatureTrend.FALLING, false, this);
        Object o1 = 0.0;
        instance.update(o, o1);
        
        o1 = 1.0;
        instance.update(o, o1);
        
        o1 = -1.0;
        instance.update(o, o1);
        
        o1 = 1.0;
        instance.update(o, o1);

        assertEquals(1, called);
    }

    /**
     * Test of getThresholdValue method, of class ThermometerObserver.
     */
    @Test
    public void testGetThresholdValue() {
        System.out.println("getThresholdValue");
        ThermometerObserver.ThermometerObserverListener listener = new ThermometerObserver.ThermometerObserverListener() {
            @Override
            public void thresholdCrossed(ThermometerObserver o, Thermometer t) {                
            }
        };
        
        ThermometerObserver instance = new ThermometerObserver("getThresholdValue", 
                0.0, TemperatureScale.CELSIUS, true, listener);
        double expResult = 0.0;
        double result = instance.getThresholdValue();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getName method, of class ThermometerObserver.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ThermometerObserver.ThermometerObserverListener listener = new ThermometerObserver.ThermometerObserverListener() {
            @Override
            public void thresholdCrossed(ThermometerObserver o, Thermometer t) {                
            }
        };
        
        ThermometerObserver instance = new ThermometerObserver("getName", 
                0.0, TemperatureScale.CELSIUS, true, listener);
        String expResult = "getName";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class ThermometerObserver.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        ThermometerObserver.ThermometerObserverListener listener = new ThermometerObserver.ThermometerObserverListener() {
            @Override
            public void thresholdCrossed(ThermometerObserver o, Thermometer t) {                
            }
        };
        
        ThermometerObserver instance = new ThermometerObserver("getDirection", 
                0.0, TemperatureScale.CELSIUS, TemperatureTrend.FALLING, true, listener);
        TemperatureTrend expResult = TemperatureTrend.FALLING;
        TemperatureTrend result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScale method, of class ThermometerObserver.
     */
    @Test
    public void testGetScale() {
        System.out.println("getScale");
        ThermometerObserver.ThermometerObserverListener listener = new ThermometerObserver.ThermometerObserverListener() {
            @Override
            public void thresholdCrossed(ThermometerObserver o, Thermometer t) {                
            }
        };
        
        ThermometerObserver instance = new ThermometerObserver("getScale", 
                0.0, TemperatureScale.CELSIUS, TemperatureTrend.FALLING, true, listener);
        TemperatureScale expResult = TemperatureScale.CELSIUS;
        TemperatureScale result = instance.getScale();
        assertEquals(expResult, result);
    }

    /**
     * Test that the observer only notifies once.
     */
    @Test
    public void testNotifyOnlyOnceComplex() {
        System.out.println("notifyOnlyOnceComplex");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("notifyOnlyOnceComplex", 
                0.0, TemperatureScale.CELSIUS, null, true, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 10; ii++) {
            t.getTemperature();
        }

        assertEquals(1, called);
    }
        
    /**
     * Test that the observer notifies multiple times hitting the threshold exactly
     */
    @Test
    public void testNotifyMultipleComplexOnThreshold() {
        System.out.println("notifyMultipleComplexOnThreshold");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("updateOnlyOnceComplex", 
                0.0, TemperatureScale.CELSIUS, null, false, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 10; ii++) {
            t.getTemperature();
        }

        assertEquals(7, called);
    }

    /**
     * Test that the observer notifies multiple times when it crosses but does
     * not equal the threshold value
     */
    @Test
    public void testNotifyMultipleComplex() {
        System.out.println("notifyMultipleComplex");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, -0.25, -0.5, 0.25, -0.5, 0.25, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("updateOnlyOnceComplex", 
                0.0, TemperatureScale.CELSIUS, null, false, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 10; ii++) {
            t.getTemperature();
        }

        assertEquals(5, called);
    }

    /**
     * Test that the Observer is notified when the threshold is defined in degrees
     * Fahrenheit
     */
    @Test
    public void testNotifyFahrenheit() {
        System.out.println("notifyFahrentheit");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("notifyFahrentheit", 
                33.8, TemperatureScale.FAHRENHEIT, null, true, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 2; ii++) {
            t.getTemperature();
        }

        assertEquals(1, called);
    }
    
    /**
     * Test that the Observer is only notified when the change in temperature
     * is greater than the set delta value.
     */
    @Test
    public void testOnlyNotifyIfLargerThanDelta() {
        System.out.println("onlyNotifyIfLargerThanDelta");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, 1.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("onlyNotifyIfLargerThanDelta", 
                0.0, TemperatureScale.CELSIUS, null, 0.75, false, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 11; ii++) {
            t.getTemperature();
        }

        assertEquals(2, called);
    }
    
    /**
     * Test that the observer is notified when no trend value is specified.
     */
    @Test
    public void testNoSpecificTrendNotification() {
        System.out.println("noSpecificTrendNotification");
        ThermometerImpl t = new ThermometerImpl( new TemperatureSource() {
            private double[] temps = {1.5, 1.0, 0.5, 0.0, 1.0, -0.5, 0.0, -0.5, 0.0, 0.5, 0.0};
            private int callCount = 0;
            
            @Override
            public double getTemperature() {
                return temps[callCount++];
            }
        });

        ThermometerObserver instance = new ThermometerObserver("noSpecificTrendNotification", 
                0.0, TemperatureScale.CELSIUS, 0.75, false, this);
        
        t.addObserver(instance);
        for (int ii = 0; ii < 11; ii++) {
            t.getTemperature();
        }

        assertEquals(2, called);
    }
    
    @Override
    public void thresholdCrossed(ThermometerObserver o, Thermometer t) {
        called++;
    }
    
}
