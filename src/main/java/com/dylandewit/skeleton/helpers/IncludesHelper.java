package com.dylandewit.skeleton.helpers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IncludesHelper {
    private IncludesHelper() {
    }

    public static boolean hasInclude(List<String> includes, String item) {
        return includes.stream().anyMatch(include -> include.startsWith(item));
    }

    public static List<String> fetchNestedIncludes(List<String> includes, String item) {
        if (includes == null) throw new IllegalArgumentException("Includes should not be null");
        if (item == null) throw new IllegalArgumentException("Item should not be null");

        Set<String> nestedIncludes = new HashSet<>();

        if (includes.stream().noneMatch(include -> include.contains(item)))
            return new ArrayList<>(nestedIncludes);

        includes.forEach(include -> {
            String matcher = item + ".";

            if (include.contains(matcher))
                nestedIncludes.add(include.split(matcher)[1]);
        });

        return new ArrayList<>(nestedIncludes);
    }
}
