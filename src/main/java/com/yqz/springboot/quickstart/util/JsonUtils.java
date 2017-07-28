package com.yqz.springboot.quickstart.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

public class JsonUtils {
	 
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static <T> T deserialize(Class<T> classT, String json) {
		try {
			T value = mapper.readValue(json, classT);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T deserialize(TypeReference<T> typeR, String json) {
		try {
			T value = mapper.readValue(json, typeR);
			return value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String serialize(Object value) {
		String s = null;
		try {
			s = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return s;
	}

	 

	private static ObjectMapper getObjectMapper() {
		ObjectMapper objMapper = new ObjectMapper();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
		// javaTimeModule.addDeserializer(Date.class, new
		// SimpleDateDeserializer("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"));
		objMapper.registerModule(javaTimeModule);
		objMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		objMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		objMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
		objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
		objMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		objMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
		objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		return objMapper;
	}


}
