package com.example.quizmaster_backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListWithLongsConverter implements AttributeConverter<List<Long>, String> {

    private ObjectMapper objectMapper;
    private Logger logger = LogManager.getLogger(ListWithLongsConverter.class);

    public ListWithLongsConverter() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        String converted = null;
        try {
            converted = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            logger.error("Error while converting a List<Long> to JSON", e);
        }
        return converted;
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        List<Long> converted = new ArrayList<>();
        try {
            converted = objectMapper.readValue(dbData, List.class);
        } catch (IOException e) {
            logger.error("Error while converting a JSON to List<Long>", e);
        }
        return converted;
    }
}
