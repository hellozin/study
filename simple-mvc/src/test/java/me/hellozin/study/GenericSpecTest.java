package me.hellozin.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericSpecTest {

    GenericSpec<Item> spec;
    List<Item> items;

    @BeforeEach
    public void buildSpecification() {
        spec = new GenericSpec<>();
        spec.add("id", (Item item, Object id) -> item.getId().equals(id));
        spec.add("date", (Item item, Object date) -> item.getDate().isBefore((LocalDate)date));

        LocalDate now = LocalDate.now();
        items = new ArrayList<>();
        items.add(new Item("1", "hello", now.minusDays(1)));
        items.add(new Item("1", "hi", now));
        items.add(new Item("2", "hello", now.minusDays(1)));
        items.add(new Item("3", "bye", now.plusDays(1)));
    }

    @Test
    public void testSpecification() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", "1");
        params.put("date", LocalDate.now());
        List<Item> result = spec.search(items, params);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("hello");
    }

}