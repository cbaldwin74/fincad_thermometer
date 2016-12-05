package com.fincad.thermometer;

import java.util.Observable;

/**
 * Define the interface for a Thermometer.
 * 
 * @author Chris Baldwin
 */
public abstract class Thermometer extends Observable {
    
    /**
     * Get the current temperature of the thermometer using the default temperature
     * scale 
     * 
     * @see com.fincad.thermometer.TemperatureScale
     * 
     * @return the temperature using the default scale 
     */
    public abstract double getTemperature();
    
    /**
     * Get the current temperature of the thermometer using the given temperature
     * scale and notify any registered Observers about the new value.
     * 
     * @param scale the TemperatureScale to return the temperature in
     * @return the temperature using the given scale
     * 
     * @see com.fincad.thermometer.TemperatureScale
     */
    public abstract double getTemperature(TemperatureScale scale);

}
