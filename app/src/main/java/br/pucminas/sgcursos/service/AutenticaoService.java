package br.pucminas.sgcursos.service;

import br.pucminas.sgcursos.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AutenticaoService {

    // URL de acesso ao webservice REST
    String BASE_URL = "http://18.231.121.125/SisAcad/";

    /**
     *
     * Método que define como deve ser feita a chamada ao webservice REST, mostrando parâmetros, verbo HTTP e retorno
     *
     * @param aluno atributos email e senha devem estar preenchidos
     * @return Interface Call, da biblioteca Retrofit, responsável por preparar a chamada ao webservice no formato apropriado.
     */
    @POST(value = "autentica")
    Call<Aluno> autentica(@Body Aluno aluno);

}
