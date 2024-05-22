package com.ricardo.literatura.app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
// Clase que implementa la interfaz IConvertData para convertir datos JSON a objetos Java.
public class ConvertData implements IConvertData {

	private ObjectMapper objectMapper = new ObjectMapper();

	//Convierte datos JSON en un objeto Java de la clase especificada.
	@Override
	public <T> T getData(String json, Class<T> clase) {
		try {
			return objectMapper.readValue(json, clase);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
