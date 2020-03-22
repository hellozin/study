package me.hellozin.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/test")
    public List<Item> getList(@RequestParam Map<String, Object> params) {
        Specification<Item> spec = buildSpecification(params);

        List<Item> items = internalGetList();
        return spec.searchWith(items, params.keySet());
    }

    private static Specification<Item> buildSpecification(Map<String, Object> params) {
        Specification<Item> spec = new Specification<>();

        spec.addPredicate("id", item -> item.getId().equals(params.get("id")));
        spec.addPredicate("name", item -> item.getName().equals(params.get("name")));
        spec.setComparator(Comparator.comparing(Item::getDate).reversed());

        return spec;
    }


    private List<Item> internalGetList() {
        LocalDate now = LocalDate.now();
        List<Item> items = new ArrayList<>();
        items.add(new Item("1", "hello", now.minusDays(1)));
        items.add(new Item("1", "hi", now.minusDays(1)));
        items.add(new Item("2", "hello", now));
        items.add(new Item("3", "bye", now.plusDays(1)));
        return items;
    }
}
