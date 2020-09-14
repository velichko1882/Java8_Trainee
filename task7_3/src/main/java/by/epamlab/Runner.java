package by.epamlab;

import by.epamlab.beans.TempCollector;
import by.epamlab.beans.Temperature;

import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Runner {

    private static Logger logger = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {

        List<Temperature> temperaturesForYear = new ArrayList<>();
        for (int i = 1; i <= Year.now().length(); i++) {
            temperaturesForYear.add(new Temperature(Year.now().getValue(), i));
        }

//1
        logger.info("Maximum temperature:");
        double maxTemp = temperaturesForYear.stream()
                .mapToDouble(t -> t.getTemperature())
                .max()
                .getAsDouble();
        temperaturesForYear.stream()
                .filter(t -> t.getTemperature() == maxTemp)
                .forEach(t -> logger.info(t.toString()));
        logger.info("Minimum temperature:");
        double minTemp = temperaturesForYear.stream()
                .mapToDouble(t -> t.getTemperature())
                .min()
                .getAsDouble();
        temperaturesForYear.stream()
                .filter(t -> t.getTemperature() == minTemp)
                .forEach(t -> logger.info(t.toString()));

//2
        double avg = temperaturesForYear.stream()
                .mapToDouble(t -> t.getTemperature())
                .average()
                .getAsDouble();
        logger.info("Average temperature for year: " + (double)Math.round(avg * 100) / 100);

//3
        for (int i = 1; i <= Month.DECEMBER.getValue(); i++) {
            int month = i;
            Supplier<Stream<Temperature>> monthStream =
                    () -> temperaturesForYear.stream()
                            .filter(t -> t.getDate().getMonthValue() == month);
            monthStream.get()
                    .mapToDouble(Temperature::getTemperature)
                    .max()
                    .ifPresent(max -> logger.info("Maximum temperature in "
                            + Month.of(month) + ": " + (double)Math.round(max * 100) / 100));
            monthStream.get()
                    .mapToDouble(Temperature::getTemperature)
                    .min()
                    .ifPresent(min -> logger.info("Minimum temperature in "
                            + Month.of(month) + ": " + (double)Math.round(min * 100) / 100));
        }

//4
        IntStream.range(Month.JANUARY.getValue(), Month.DECEMBER.getValue() + 1)
                .forEach(i -> temperaturesForYear.stream()
                        .filter(t -> t.getDate().getMonth() == Month.of(i))
                        .mapToDouble(Temperature::getTemperature)
                        .average()
                        .ifPresent(average -> logger.info("Average temperature for " + Month.of(i) +
                                " : " + (double)Math.round(average * 100) / 100)));

//5

        temperaturesForYear
                .stream()
                .collect(Collectors.groupingBy(t -> t.getDate().getMonth(), TreeMap::new,
                        Collectors.maxBy(Comparator.comparing(Temperature::getTemperature))))
                .forEach((month, temperature) -> logger.info("Max temperature in " + month + ": " + temperature.get()));

        Collector<Temperature, Map<Month, List<Temperature>>, List<Temperature>> collector = new TempCollector();
        temperaturesForYear.stream()
                .collect(collector)
                .forEach(temperature -> logger.info("Max temperature in "
                        + temperature.getDate().getMonth() + " = " + temperature.toString()));

        temperaturesForYear.parallelStream()
                .collect(collector)
                .forEach(temperature -> logger.info("Maximum temperature in "
                        + temperature.getDate().getMonth() + " in parallel stream = " + temperature.toString()));


    }
}
