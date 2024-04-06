package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public enum Rating {
    ZERO("from 0"),
    ONE("from 1"),
    TWO("from 2"),
    THREE("from 3"),
    FOUR("from 4"),
    FIVE("from 5"),
    SIX("from 6"),
    SEVEN("from 7"),
    EIGHT("from 8"),
    NINE("from 9");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getAllValues() {
        List<String> output = new ArrayList<>();
        for (Rating rating : Rating.values()) {
            output.add(rating.getValue());
        }
        return output;
    }
    }
