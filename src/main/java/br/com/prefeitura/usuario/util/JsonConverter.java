package br.com.prefeitura.usuario.util;

import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T converterJsonParaDto(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(
                "Erro ao converter JSON para " + clazz.getSimpleName(), e
            );
        }
    }
    
    public static <T> List<T> converterJsonParaLista(String json, Class<T> clazz) {
        try {
            JavaType type = objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, clazz);

            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(
                "Erro ao converter JSON para List<" + clazz.getSimpleName() + ">", e
            );
        }
    }
}