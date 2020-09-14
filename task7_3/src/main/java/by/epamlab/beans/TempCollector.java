package by.epamlab.beans;

import java.time.Month;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TempCollector
        implements Collector<Temperature, Map<Month, List<Temperature>>, List<Temperature>> {

    @Override
    public Supplier<Map<Month, List<Temperature>>> supplier() {
        return TreeMap::new;
    }

    @Override
    public BiConsumer<Map<Month, List<Temperature>>, Temperature> accumulator() {
        return (monthListMap, temperature) -> {
            Month month = temperature.getDate().getMonth();
            if (monthListMap.containsKey(month)){
                monthListMap.get(month).add(temperature);
            } else {
                List<Temperature> temperatureList = new ArrayList<>();
                temperatureList.add(temperature);
                monthListMap.put(month, temperatureList);
            }
        };
    }

    @Override
    public BinaryOperator<Map<Month, List<Temperature>>> combiner() {
        return (monthListMap, monthListMap2) -> {
            monthListMap.forEach((month, temperatures) -> temperatures.addAll(monthListMap2.get(month)));
            return monthListMap;
        };
    }

    @Override
    public Function<Map<Month, List<Temperature>>, List<Temperature>> finisher() {
        return monthListMap -> {
            List<Temperature> maxTempByMonth = monthListMap.values().stream()
                    .map(temperatures -> temperatures.stream()
                            .max(Comparator.comparing(Temperature::getTemperature)))
                    .map(temperature -> temperature.get())
                    .collect(Collectors.toList());
            return maxTempByMonth;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
    }
}
