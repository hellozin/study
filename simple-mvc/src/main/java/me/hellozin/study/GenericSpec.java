package me.hellozin.study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericSpec<T> {

    private Map<String, BiPredicate<T, Object>> functionMap = new HashMap<>();

    public void add(String paramKey, BiPredicate<T, Object> matchCondition) {
        functionMap.put(paramKey, matchCondition);
    }

    public List<T> search(List<T> origin, Map<String, Object> params) {
        Predicate<T> predicate = x -> true;

        for (Entry<String, Object> entry : params.entrySet()) {
            if (functionMap.containsKey(entry.getKey())) {
                predicate = predicate.and(item -> functionMap.get(entry.getKey()).test(item, entry.getValue()));
            }
        }
        return origin.stream().filter(predicate).collect(Collectors.toList());
    }

}
