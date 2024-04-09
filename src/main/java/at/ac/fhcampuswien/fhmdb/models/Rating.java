package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public enum Rating {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    private final double value;

    Rating(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static List<Double> getAllValues() {
        List<Double> output = new ArrayList<>();
        for (Rating rating : Rating.values()) {
            output.add(rating.getValue());
        }
        return output;
    }
    }
