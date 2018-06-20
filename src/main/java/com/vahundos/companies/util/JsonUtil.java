package com.vahundos.companies.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JsonUtil {

    private final ObjectMapper mapper;

    @Autowired
    public JsonUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> String writeValue(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public <T> String writeIgnoreProps(Collection<T> collection, String... ignoreProps) {
        List<Map<String, Object>> list = collection.stream()
                .map(e -> getAsMapWithIgnore(e, ignoreProps))
                .collect(Collectors.toList());
        return writeValue(list);
    }

    public <T> String writeIgnoreProps(T obj, String... ignoreProps) {
        Map<String, Object> map = getAsMapWithIgnore(obj, ignoreProps);
        return writeValue(map);
    }

    private <T> Map<String, Object> getAsMapWithIgnore(T obj, String[] ignoreProps) {
        Map<String, Object> map = mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
        for (String prop : ignoreProps) {
            map.remove(prop);
        }
        return map;
    }
}
