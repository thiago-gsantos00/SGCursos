package br.pucminas.sgcursos.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Response;

/**
 * Clase responsável por representar a resposta recebida do webservice REST
 */
public class RespostaRest implements Serializable {

    private Aluno aluno;
    private String errorMessage;
    private int code;

    public RespostaRest(Response<Aluno> resposta) {
        processaResposta(resposta);
    }

    //Método responsável pelo processamento da resposta vinda do webservice REST
    private void processaResposta(Response<Aluno> resposta) {
        this.aluno = resposta.body() != null ? resposta.body() : null;
        this.code = resposta.code();
        try{
            if(resposta.errorBody() != null){
                JsonObject json = new Gson().fromJson(resposta.errorBody().string(),JsonObject.class);
                this.errorMessage = json.get("response").getAsString();
            }
        } catch (IOException e) {
            this.errorMessage =  "Erro na tentativa de autenticação.";
        }
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getCode() {
        return code;
    }

}
