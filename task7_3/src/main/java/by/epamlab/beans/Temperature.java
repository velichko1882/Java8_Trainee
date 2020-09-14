package by.epamlab.beans;

import java.time.LocalDate;
import java.util.Random;

public class Temperature {

    private static Random random = new Random();

    private LocalDate date;
    private double temperature;

    public Temperature() {
    }

    public Temperature(int year, int day) {
        this.date = LocalDate.ofYearDay(year, day);
        this.temperature = getTemperatuteFromDay(day);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Temperature{");
        sb.append("date=").append(date);
        sb.append(", temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }

    private static double getTemperatuteFromDay(int day){
        double temp = random.nextInt(20) + Math.random();
        if (day <= 60 || day >= 336){
            temp -= 15;
        }
        if ((day > 60 && day <= 152) || (day > 244 && day < 336)){
            temp +=5;
        }
        if (day > 152 && day <=244){
            temp += 15;
        }
        return (double)Math.round(temp * 10) / 10;
    }
}
