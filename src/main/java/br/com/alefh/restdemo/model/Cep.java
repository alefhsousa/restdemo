package br.com.alefh.restdemo.model;

import java.util.List;

/**
 * Classe para representar um objeto do tipo Cep
 * @author Alefh Sousa
 * @version 1.0
 *
 */
public class Cep {

        private String logradouro;
        private String  cep;
        private String  cidade;
        private String  estado;
        private String  bairro;

        public Cep() {
        }

        public Cep(String cep) {
            this.cep = cep;
        }

        @Override
        public String toString() {
            return "[ " + this.cep + ", " + this.estado + ", " +
                    this.cidade + ", " + this.bairro + ", " + this.logradouro + "]";
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }
}
