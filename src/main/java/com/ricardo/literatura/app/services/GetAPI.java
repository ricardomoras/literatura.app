package com.ricardo.literatura.app.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//Clase que realiza peticiones HTTP para obtener datos de una API externa.
public class GetAPI {

	//Realiza una solicitud GET a una URL específica y devuelve la respuesta como una cadena JSON.
	public String getData(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}

		return response.body();
	}
}
