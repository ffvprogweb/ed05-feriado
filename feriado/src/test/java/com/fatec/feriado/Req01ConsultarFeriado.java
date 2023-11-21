package com.fatec.feriado;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

class Req01ConsultarFeriado {

	@Test
	void ct01_consultar_feriado_com_sucesso() {
		String urlBase = "https://api.invertexto.com/v1/holidays/2023?token=5375|iZWol7ut7lmLcdxUQn8OFfrdEvIyzDST&state=SP";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		record Feriado(String date, String name, String type, String level){};
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(urlBase, HttpMethod.GET, request, String.class);
		// validar o status retornado
		assertEquals("200 OK", response.getStatusCode().toString());
		// validar o headers retornado
		assertEquals("application/json" , response.getHeaders().getContentType().toString());
		// validar o body
		System.out.println(response.getBody());
		Gson gson = new Gson();
		Feriado[] lista = gson.fromJson(response.getBody(), Feriado[].class);
		System.out.println(lista[0]);
		assertEquals (17, lista.length);

	}

}
