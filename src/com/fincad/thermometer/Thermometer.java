package com.fincad.thermometer;

/**
 * Define the interface for a Thermometer.
 * 
 * @author Chris Baldwin
 */
public interface Thermometer {
    
    /**
     * Get the current temperature of the thermometer using the default temperature
     * scale (CELSIUS or FAHRENHEIT)
     * 
     * @return the temperature using the default scale 
     */
    public double getTemperature();
    
    /**
     * Get the current temperature of the thermometer using the given temperature
     * scale.
     * 
     * @param scale the Scale to return the temperature in
     * @return the temperature using the given scale
     */
    public double getTemperature(TemperatureScale scale);
    
    
    
}
