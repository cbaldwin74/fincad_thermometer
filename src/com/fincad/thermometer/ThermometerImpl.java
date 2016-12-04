package com.fincad.thermometer;

import java.util.Observable;

/**
 * Implementation of the Thermometer Interface.
 * 
 * @author Chris Baldwin
 */
public class ThermometerImpl extends Observable implements Thermometer {
    
    private static double SCALE_FACTOR = 1.8;
    
    private TemperatureScale defaultScale;
    private TemperatureSource source;
    
    public TemperatureScale getDefaultScale() {
        return defaultScale;
    }
    
    /**
     * Construct a Thermometer instance that reads from the given TemperatureSource.
     * 
     * @param source the source this temperature instance should read from
     */
    public ThermometerImpl(TemperatureSource source) {
        this(source, TemperatureScale.CELSIUS);
    }
    
    /**
     * Construct a Thermometer instance that reads from the given TemperatureSource
     * and set the default temperature scale to the given value.
     * 
     * @param source the source this temperature instance should read from
     * @param defaultScale the default temperature scale to use
     */
    public ThermometerImpl(TemperatureSource source, TemperatureScale defaultScale) {
        super();
        this.source = source;
        this.defaultScale = defaultScale;
    }

    @Override
    public double getTemperature() {
        return getTemperature(defaultScale);
    }

    @Override
    public double getTemperature(TemperatureScale scale) {
        double temp = source.getTemperature();
        
        if (scale == TemperatureScale.FAHRENHEIT) {
            temp = convertCelsiusToFahrenheit(temp);
        }
        
        setChanged();
        notifyObservers(new Double(temp));
        
        return temp;
    }
        
    private double convertCelsiusToFahrenheit(double temp) {
      return temp * SCALE_FACTOR + 32;  
    }
}
