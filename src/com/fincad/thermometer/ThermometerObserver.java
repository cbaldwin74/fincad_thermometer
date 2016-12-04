package com.fincad.thermometer;

import java.util.Observable;
import java.util.Observer;

/**
 * An observer for watching the temperature of a thermometer and be notified 
 * when the temperature meets certain conditions.
 * 
 * @author Chris Baldwin
 */
public class ThermometerObserver implements Observer {

    
    public interface ThermometerObserverListener {
      public void thresholdCrossed(ThermometerObserver o, Thermometer t);  
    }
    private static final double ABSOLUTE_ZERO_CELSIUS = -274;
    
    private final boolean once;
    private final double thresholdValue;
    private final String name;
    private final TemperatureTrend direction;
    private final TemperatureScale scale;
    private final ThermometerObserverListener listener;
    private double oldTemp = ABSOLUTE_ZERO_CELSIUS;
    private double thresholdValueCelsius;
    private double delta;
    
    /**
     * Construct a new Observer that will be notified when the given conditions are
     * met.
     * 
     * @param name the name of the observer
     * @param thresholdValue the temperature threshold value
     * @param scale the temperature scale of the temperature value
     * @param once if true will only notify the first time the conditions are met
     * @param listener the listener to call when the conditions are met
     */
    public ThermometerObserver(String name, double thresholdValue, TemperatureScale scale, 
            boolean once, ThermometerObserverListener listener) {
        this(name, thresholdValue, scale, null, 0.0, once, listener);
    }
    
    /**
     * Construct a new Observer that will be notified when the given conditions are
     * met.
     * 
     * @param name the name of the observer
     * @param thresholdValue the temperature threshold value
     * @param scale the temperature scale of the temperature value
     * @param direction the trending direction to be used
     * @param once if true will only notify the first time the conditions are met
     * @param listener the listener to call when the conditions are met
     */
    public ThermometerObserver(String name, double thresholdValue, TemperatureScale scale, 
            TemperatureTrend direction, boolean once, ThermometerObserverListener listener) {
        this(name, thresholdValue, scale, direction, 0.0, once, listener);
    }
    
    /**
     * Construct a new Observer that will be notified when the given conditions are
     * met.
     * 
     * @param name the name of the observer
     * @param thresholdValue the temperature threshold value
     * @param scale the temperature scale of the temperature value
     * @param delta the minimum change that the observer is interested in, changes less
     * than this value will not trigger notifications
     * @param once if true will only notify the first time the conditions are met
     * @param listener the listener to call when the conditions are met
     */
    public ThermometerObserver(String name, double thresholdValue, TemperatureScale scale, 
            double delta, boolean once, ThermometerObserverListener listener) {
        this(name, thresholdValue, scale, null, delta, once, listener);
    }
    
    /**
     * Construct a new Observer that will be notified when the given conditions are
     * met.
     * 
     * @param name the name of the observer
     * @param thresholdValue the temperature threshold value
     * @param scale the temperature scale of the temperature value
     * @param direction the trending direction to be used
     * @param once if true will only notify the first time the conditions are met
     * @param delta the minimum change that the observer is interested in, changes less
     * than this value will not trigger notifications
     * @param listener the listener to call when the conditions are met
     */
    public ThermometerObserver(String name, double thresholdValue, TemperatureScale scale, 
            TemperatureTrend direction, double delta, boolean once, ThermometerObserverListener listener) {
        this.name = name;
        this.thresholdValue = thresholdValue;
        this.direction = direction;
        this.once = once;
        this.scale = scale;
        this.listener = listener;
        this.delta = delta;
        
        if (this.scale == TemperatureScale.FAHRENHEIT) {
            this.thresholdValueCelsius = convertFahrenheitToCelsius(this.thresholdValue);
        } else {
            this.thresholdValueCelsius = this.thresholdValue;
        }
    }
    
    @Override
    public void update(Observable o, Object o1) {
        Thermometer t = (Thermometer) o;
        double temp = ((Double) o1);
        
        if ((oldTemp > ABSOLUTE_ZERO_CELSIUS) && (oldTemp != temp)) {
            if ((oldTemp <= thresholdValueCelsius && temp >= thresholdValueCelsius && (direction == null || direction == TemperatureTrend.RISING)) ||
                    (oldTemp >= thresholdValueCelsius && temp <= thresholdValueCelsius && (direction == null || direction == TemperatureTrend.FALLING))) {
                if (delta != 0.0){
                    double tempDiff = oldTemp - temp;
                    if (Math.abs(tempDiff) >= delta) {
                        listener.thresholdCrossed(this, t);
                    }
                } else {
                    listener.thresholdCrossed(this, t);
                }

                if (once) {
                    o.deleteObserver(this);
                }
            }
        }
        
        oldTemp = temp;
    }

    public double getThresholdValue() {
        return thresholdValue;
    }

    public String getName() {
        return name;
    }

    public TemperatureTrend getDirection() {
        return direction;
    }

    public TemperatureScale getScale() {
        return scale;
    }
        
    private double convertFahrenheitToCelsius(double thresholdValue) {
        return (thresholdValue - ((double) 32.0)) / 1.8;
    }
}
