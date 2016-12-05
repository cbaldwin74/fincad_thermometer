package com.fincad.thermometer;

/**
 * Factory class for creating Thermometer instances.
 * 
 * @author Chris Baldwin
 */
public class ThermometerFactory {
   public static Thermometer createThermometer(TemperatureSource source) {
       return new ThermometerImpl(source);
   } 
   
   public static Thermometer createThermometer(TemperatureSource source, TemperatureScale scale) {
       return new ThermometerImpl(source, scale);
   }
}
