package br.com.prefeitura.usuario.api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;

public class ApiInsereSetor extends ApiCallSolar {

    public ApiInsereSetor() {
        super("https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/inserir-setor");
    }

    public String adicionarSetor(CadastroUsuarioPostDto usuario) {
        disable();

        if (usuario == null || usuario.getCdUsuario() == null) {
            throw new IllegalArgumentException("Usu�rio e cdUsuario n�o podem ser nulos.");
        }

        // Pega o cdUsuario do body enviado pelo Postman
        String cdUsuario = usuario.getCdUsuario();

        Unidade unidadePadrao = new Unidade("PMDI", "SEPLAGE");

        List<Unidade> unidades = Arrays.asList(
                new Unidade("PMDI", "SEPLAGE"),
                new Unidade("PMDI", "SF")
        );

        SetorUsuario setorUsuario = new SetorUsuario(cdUsuario, unidadePadrao, unidades);

        Payload payload = new Payload(
                Collections.singletonList("UNIDADES"),
                Collections.singletonList(setorUsuario)
        );

        try {
            String jsonPayload = new ObjectMapper().writeValueAsString(payload);
            return executarJson(jsonPayload).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"status\":\"erro\",\"message\":\"Falha ao gerar JSON\"}";
        }
    }

    // Novo m�todo para enviar JSON direto sem mexer no ApiCallSolar original
    protected StringBuilder executarJson(String json) {
        try {
            HttpURLConnection connection = conectar("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.getBytes("UTF-8"));
            }

            return obterResposta(connection);

        } catch (Exception e) {
            e.printStackTrace();
            return new StringBuilder("{\"status\":\"erro\",\"message\":\"Falha ao chamar API\"}");
        }
    }

    @JsonPropertyOrder({ "campos", "setoresUsuarios" })
    public static class Payload {
        private final List<String> campos;
        private final List<SetorUsuario> setoresUsuarios;

        public Payload(List<String> campos, List<SetorUsuario> setoresUsuarios) {
            this.campos = campos;
            this.setoresUsuarios = setoresUsuarios;
        }

        public List<String> getCampos() { return campos; }
        public List<SetorUsuario> getSetoresUsuarios() { return setoresUsuarios; }
    }

    public static class SetorUsuario {
        private final String cdUsuario;
        private final Unidade unidadePadrao;
        private final List<Unidade> unidades;

        public SetorUsuario(String cdUsuario, Unidade unidadePadrao, List<Unidade> unidades) {
            this.cdUsuario = cdUsuario;
            this.unidadePadrao = unidadePadrao;
            this.unidades = unidades;
        }

        public String getCdUsuario() { return cdUsuario; }
        public Unidade getUnidadePadrao() { return unidadePadrao; }
        public List<Unidade> getUnidades() { return unidades; }
    }

    public static class Unidade {
        private final String sgOrgao;
        private final String sgUnidade;

        public Unidade(String sgOrgao, String sgUnidade) {
            this.sgOrgao = sgOrgao;
            this.sgUnidade = sgUnidade;
        }

        public String getSgOrgao() { return sgOrgao; }
        public String getSgUnidade() { return sgUnidade; }
    }
}