package com.fincad.thermometer;

/**
 * Factory class for creating Thermometer instances.
 * 
 * @author Chris Baldwin
 */
public class ThermometerFactory {
   /**
    * Create a Thermometer instance that reads from the given TemperatureSource.
    * 
    * @param source the source this temperature instance should read from
    */
   public static Thermometer createThermometer(TemperatureSource source) {
       return new ThermometerImpl(source);
   } 
   
   /**
    * Construct a Thermometer instance that reads from the given TemperatureSource
    * and set the default temperature scale to the given value.
    * 
    * @param source the source this temperature instance should read from
    * @param defaultScale the default temperature scale to use
    */
   public static Thermometer createThermometer(TemperatureSource source, TemperatureScale scale) {
       return new ThermometerImpl(source, scale);
   }
}
