package br.ifsp.techmaps.external.persistence.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
    }
    public HashMap<String, Object> retrieveStepParams(String params) {
        try {
            return objectMapper.readValue(params, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public String writeMapStringObjectAsJsonString(Map<String, Object> params) {
        try {
            return objectMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

}

