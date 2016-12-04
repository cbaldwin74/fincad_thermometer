package com.fincad.thermometer;

/**
 * Interface the will allow a Thermometer instance to read its temperature.
 * 
 * @author Chris Baldwin
 */
public interface TemperatureSource {
    
    /**
     * Get the current temperature value in degrees Celsius
     * 
     * @return the temperature value in degrees Celsius
     */
    public double getTemperature();
}
