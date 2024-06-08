package ru.yandex.practicum.filmorate.service.util;

import java.util.Map;

public class ServiceUtil {

    public static long generateNewId(Map<Long, ?> map) {
        return map.keySet().stream()
                .mapToLong(Long::valueOf)
                .max()
                .orElse(0) + 1;
    }
}
