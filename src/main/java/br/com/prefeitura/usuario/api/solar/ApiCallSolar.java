package br.com.prefeitura.usuario.api.solar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.prefeitura.usuario.api.solar.AccessTokenFetcher;

import com.fasterxml.jackson.databind.ObjectMapper;





public class ApiCallSolar {
                private String AUTH_TOKEN = "";
                String apiUrl = null;

    public ApiCallSolar(String apiUrl, String chave) {
        this.apiUrl = apiUrl;
        this.AUTH_TOKEN = chave;
    }
    
    public ApiCallSolar(String apiUrl) {
        this.apiUrl = apiUrl;
        this.AUTH_TOKEN = AccessTokenFetcher.getAccessToken();
    }
    
    public ApiCallSolar(String apiUrl, Integer cd) {
        this.apiUrl = apiUrl +"/" +cd;
        this.AUTH_TOKEN = AccessTokenFetcher.getAccessToken();
    }
    
    protected void disable() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
    
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
    
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
            };
    
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected HttpURLConnection conectar(String request) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod(request);
        connection.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
        connection.setRequestProperty("Content-Type", "application/json");

        // Apenas define saída para POST, PUT e DELETE
        if (!request.equals("GET")) {
            connection.setDoOutput(true);
        }

        return connection;
    }
    
    protected StringBuilder executar(Map<String, Object> params) {
        return executar(params, "POST");
    }

    public StringBuilder executar(Map<String, Object> params, String tipo) {
        if ("GET".equals(tipo)) {
            return executarGet(params);
        }
        return executarPostPutDelete(params, tipo);
    }

    protected StringBuilder executarPostPutDelete(Map<String, Object> params, String tipo) {
        try {
            HttpURLConnection connection = conectar(tipo);
            
            String requestBody = new ObjectMapper().writeValueAsString(params);
            System.out.println(requestBody);
             try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
            }

            return obterResposta(connection);

        } catch (IOException e) {
            e.printStackTrace();
            return new StringBuilder(e.getMessage());
        }
    }

    private StringBuilder executarGet(Map<String, Object> params) {
        try {
            // Adicionando parâmetros na URL para GET
      
        			
            String queryString = params.entrySet()
                .stream()
                .map(entry -> "" + entry.getValue())
                .reduce((a, b) -> a + "&" + b)
                .orElse("");

            URL url = new URL(apiUrl + "?" + queryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + AUTH_TOKEN);
            connection.setRequestProperty("Content-Type", "application/json");

            return obterResposta(connection);

        } catch (IOException e) {
            e.printStackTrace();
            return new StringBuilder(e.getMessage());
        }
    }

    protected StringBuilder obterResposta(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        BufferedReader reader = (responseCode == HttpURLConnection.HTTP_OK)
                ? new BufferedReader(new InputStreamReader(connection.getInputStream()))
                : new BufferedReader(new InputStreamReader(connection.getErrorStream()));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        return response;
    }
}
