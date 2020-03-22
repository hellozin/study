package me.hellozin.study;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Specification<T> {

    private Predicate<T> DEFAULT_PREDICATE = x -> true;
    private Comparator<T> DEFAULT_COMPARATOR = (a, b) -> 0;

    private Map<String, Predicate<T>> predicateMap = new HashMap<>();
    private Comparator<T> comparator = DEFAULT_COMPARATOR;

    public void addPredicate(String key, Predicate<T> predicate) {
        this.predicateMap.put(key, predicate);
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public List<T> searchWith(List<T> origin, Set<String> keys) {
        if (keys.isEmpty()) {
            return origin;
        }

        Predicate<T> predicate = DEFAULT_PREDICATE;
        for (String key : keys) {
            if (this.predicateMap.containsKey(key)) {
                predicate = predicate.and(this.predicateMap.get(key));
            }
        }

        Stream<T> stream = origin.stream();
        stream = stream.filter(predicate);

        if (keys.contains("order_by")) {
            stream = stream.sorted(this.comparator);
        }

        return stream.collect(Collectors.toList());
    }

}
