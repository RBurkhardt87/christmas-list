package com.example.christmas_list.data;

import com.example.christmas_list.models.Wish;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WishData {
    private static final Map<Integer, Wish> wishes = new HashMap<>();

    public static Collection<Wish> getAll() {
        return wishes.values();
    }

    public static Wish getById(int id) {
        return wishes.get(id);
    }

    public static void add(Wish wish) {
        wishes.put(wish.getId(), wish);
    }

    public static void remove(int id) {
        wishes.remove(id);
    }

}
