package quarks0;

import java.util.concurrent.TimeUnit;

import quarks.providers.direct.DirectProvider;
import quarks.topology.TStream;
import quarks.topology.Topology;

public class TempSensorApplication {
    public static void main(String[] args) throws Exception {
        TempSensor sensor = new TempSensor();
        DirectProvider dp = new DirectProvider();      
        Topology topology = dp.newTopology();
        TStream<Double> tempReadings = topology.poll(sensor, 1, TimeUnit.MILLISECONDS);
        TStream<Double> filteredReadings = tempReadings.filter(reading -> reading < 50 || reading > 80);

        filteredReadings.print();
        dp.submit(topology);
      }
}