package br.com.alefh.restdemo.model;

import com.google.gson.Gson;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe para representar um objeto do tipo Endereço
 * @author Alefh Sousa
 * @version 1.0
 *
 */
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Número não pode ser vazio")
    private Integer numero;

    private String  complemento;

    @NotEmpty(message = "Rua precisa ser preenchido")
    private String rua;

    @Length(min = 8, max = 8, message = "CEP inválido")
    @NotEmpty(message = "CEP precisa ser preenchido")
    private String  cep;

    @NotEmpty(message = "Cidade precisa ser preenchido")
    private String  cidade;

    @NotEmpty(message = "Estado precisa ser preenchido")
    private String  estado;

    private String  bairro;

    public Endereco() {
    }


    public Endereco(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ "+ this.id + ", " + this.numero + ", " + this.complemento + ", " + "]";
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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
