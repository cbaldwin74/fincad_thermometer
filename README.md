# fincad_thermometer

User's of this library access the factory class [<code>com.fincad.themometer.ThermometerFactory</code>](https://github.com/cbaldwin74/fincad_thermometer/blob/master/src/com/fincad/thermometer/ThermometerFactory.java) and use either <code>createThermometer(TemperatureSource source)</code> or <code>createThermometer(TemperatureSource source, TemperatureScale scale)</code> to get an instance of a [<code>Thermometer</code>](https://github.com/cbaldwin74/fincad_thermometer/blob/master/src/com/fincad/thermometer/Thermometer.java) that reads from the given <code>TemperatureSource</code>.

Users can then create an instance of a [<code>ThermometerObserver</code>](https://github.com/cbaldwin74/fincad_thermometer/blob/master/src/com/fincad/thermometer/ThermometerObserver.java) to watch the <code>Thermometer</code> instance and be notified when certain temperature events occur.

Each time anyone calls one of the <code>getTemperature()</code> methods on the <code>Thermometer</code> all registered <code>ThermometerObserver</code> instances are notified of the new temperature value and they determine if their <code>ThermometerObserverListener</code> should be notified.
