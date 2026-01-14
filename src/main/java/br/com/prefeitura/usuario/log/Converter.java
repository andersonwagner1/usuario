package br.com.prefeitura.usuario.log;

import br.com.prefeitura.usuario.dto2.RetornoApiEprocessoDto;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

    public static RetornoApiEprocessoDto convertJsonToDTO(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(json, RetornoApiEprocessoDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
}