package br.com.prefeitura.usuario.dto2;

import java.util.List;

public class ExcluirSetorDTO {

    private List<SetorUsuario> setoresUsuarios;

    public List<SetorUsuario> getSetoresUsuarios() {
        return setoresUsuarios;
    }

    public void setSetoresUsuarios(List<SetorUsuario> setoresUsuarios) {
        this.setoresUsuarios = setoresUsuarios;
    }

    // --- classes internas ---
    public static class SetorUsuario {
        private String cdUsuario;
        private List<Unidade> unidades;

        public String getCdUsuario() {
            return cdUsuario;
        }

        public void setCdUsuario(String cdUsuario) {
            this.cdUsuario = cdUsuario;
        }

        public List<Unidade> getUnidades() {
            return unidades;
        }

        public void setUnidades(List<Unidade> unidades) {
            this.unidades = unidades;
        }
    }

    public static class Unidade {
        private String sgOrgao;
        private String sgUnidade;

        public String getSgOrgao() {
            return sgOrgao;
        }

        public void setSgOrgao(String sgOrgao) {
            this.sgOrgao = sgOrgao;
        }

        public String getSgUnidade() {
            return sgUnidade;
        }

        public void setSgUnidade(String sgUnidade) {
            this.sgUnidade = sgUnidade;
        }
    }
}