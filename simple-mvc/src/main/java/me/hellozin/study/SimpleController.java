package me.hellozin.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    GenericSpec<Item> genericSpec;

    @PostConstruct
    private void buildSpec() {
        genericSpec = new GenericSpec<>();

        genericSpec.add("id", (Item item, Object value) -> item.getId().equals(value));
        genericSpec.add("name", (Item item, Object value) -> item.getName().equals(value));
    }

    @GetMapping("/test")
    public List<Item> getList(@RequestParam Map<String, Object> params) {
        List<Item> items = internalGetList();

        return genericSpec.search(items, params);
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
