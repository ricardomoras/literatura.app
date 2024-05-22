package com.ricardo.literatura.app.services;
//Interfaz para la conversión de datos JSON a objetos Java.
public interface IConvertData {

	//Convierte datos JSON en un objeto Java de la clase especificada.
	<T> T getData(String json, Class<T> clase);
}
