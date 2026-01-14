package br.com.prefeitura.usuario.api.solar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import br.com.prefeitura.usuario.Config;

public class AccessTokenFetcher {

    // URL do endpoint de autenticação
    private final String API_URL = Config.get("eprocesso.url.token");

    // Credenciais de autenticação (usuário e senha) para Basic Auth
    // Nota: Em um ambiente de produção, essas credenciais devem ser armazenadas de forma segura (e.g., variáveis de ambiente, Vault)
    private final String USERNAME = Config.get("eprocesso.login");
    private final String PASSWORD = Config.get("eprocesso.senha");


    public static String getAccessToken(){
    	AccessTokenFetcher e = new AccessTokenFetcher();
    	return e.executar();
    }
    
    /**
     * Realiza a requisição POST para a API de token e retorna o access_token.
     *
     * @return O access_token retornado pela API. Retorna null em caso de erro.
     */
    public String executar() {
        try {
            // Cria o objeto URL
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configura a conexão para uma requisição POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true); // Permite o envio de dados no corpo da requisição

            // Configura o cabeçalho Content-Type
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Configura o cabeçalho de Autorização para Basic Auth
            String credentials = USERNAME + ":" + PASSWORD;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

            // Corpo da requisição
            String requestBody = Config.get("eprocesso.request.body");

            // Envia o corpo da requisição
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lê a resposta da API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Verifica se a resposta foi bem-sucedida (código 200)
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                // Analisa o JSON para extrair o access_token
                String jsonResponse = content.toString();
               // System.out.println("Resposta JSON recebida:\n" + jsonResponse);

                // Método de análise simples para o access_token
                String accessToken = extractAccessToken(jsonResponse);
                return accessToken;
            } else {
                // Em caso de erro, imprime o código da resposta
                System.out.println("Erro na requisição. Código de resposta: " + responseCode);
                // Opcional: Ler o stream de erro para obter mais detalhes
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String line;
                    StringBuilder errorContent = new StringBuilder();
                    while ((line = errorReader.readLine()) != null) {
                        errorContent.append(line);
                    }
                    System.out.println("Mensagem de erro: " + errorContent.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Analisa a string JSON para encontrar e retornar o valor do "access_token".
     *
     * @param jsonString A string JSON a ser analisada.
     * @return O valor do access_token.
     */
    private String extractAccessToken(String jsonString) {
        // Encontra a posição do "access_token"
        String key = "\"access_token\":";
        int startIndex = jsonString.indexOf(key);

        if (startIndex != -1) {
            // Calcula o início do valor após a chave e as aspas de abertura
            startIndex += key.length();
            if (jsonString.charAt(startIndex) == ' ') {
                startIndex++; // Ignora o espaço se houver
            }
            if (jsonString.charAt(startIndex) == '"') {
                startIndex++; // Ignora as aspas de abertura
            }

            // Encontra a próxima aspas de fechamento
            int endIndex = jsonString.indexOf('"', startIndex);

            if (endIndex != -1) {
                // Extrai a substring que contém o token
                return jsonString.substring(startIndex, endIndex);
            }
        }
        return null; // Retorna null se não conseguir extrair
    }

    public static void main(String[] args) {
        
        String accessToken = AccessTokenFetcher.getAccessToken();

        if (accessToken != null) {
            System.out.println("\nAccess Token obtido com sucesso: " + accessToken);
        } else {
            System.out.println("\nFalha ao obter o Access Token.");
        }
    }
}
