package br.com.prefeitura.usuario;

import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	

    private static Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static boolean isModoDesenvolvimento() {
        return Boolean.parseBoolean(get("modo.desenvolvimento"));
    }
}