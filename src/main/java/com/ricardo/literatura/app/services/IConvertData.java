package com.ricardo.literatura.app.services;

public interface IConvertData {

	<T> T getData(String json, Class<T> clase);
}
