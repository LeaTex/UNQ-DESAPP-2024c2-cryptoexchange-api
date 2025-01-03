package ar.edu.unq.desapp.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class UsdService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String DOLAR_URL = "https://dolarapi.com/v1/dolares/blue";

    // Clase interna para mapear la respuesta JSON
    private static class DolarResponseService {
        private String moneda;
        private String casa;
        private String nombre;
        @Getter
        private Double compra;
        private Double venta;
        private String fechaActualizacion;

    }

    public Double getDolarValue() {
        // Realiza la llamada a la API y mapea la respuesta a la clase DolarResponse
        DolarResponseService response = restTemplate.getForObject(DOLAR_URL, DolarResponseService.class);
        if (response != null) {
            return response.getCompra();
        }
        throw new HttpClientErrorException(HttpStatus.NO_CONTENT, "No se pudo obtener el valor del dólar");
    }
}
