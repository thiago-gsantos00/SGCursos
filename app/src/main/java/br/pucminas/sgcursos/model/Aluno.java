package br.pucminas.sgcursos.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *  Classe que representa o Aluno que irá fazer a autenticação na aplicação
 */
public class Aluno implements Serializable{

    private String cpf;
    private String nome;
    private String endereco;
    private String municipio;
    private String estado;
    private String telefone;
    @SerializedName("email")
    private String email;
    @SerializedName("senha")
    private String senha;

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno [cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", municipio=" + municipio
                + ", estado=" + estado + ", telefone=" + telefone + ", email=" + email + "]";
    }
}
