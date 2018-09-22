package br.pucminas.sgcursos.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import br.pucminas.sgcursos.model.Aluno;
import br.pucminas.sgcursos.model.RespostaRest;
import br.pucminas.sgcursos.view.MenuActivity;
import br.pucminas.sgcursos.view.ResultadoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase responsável por tratar a resposta recebida do webservice REST
 */
public class RequisicaoDeAutenticacao {

    private static final String TAG = "RequisicaoAutenticacao";
    private Context context;
    private Aluno alunoDaRequisicao;
    private RespostaRest respostaRest;
    private ObservableBoolean sucessoRequisicao;

    public RequisicaoDeAutenticacao(Context context, Aluno aluno){
        this.context = context;
        this.alunoDaRequisicao= new Aluno();
        this.alunoDaRequisicao.setEmail(aluno.getEmail());
        this.alunoDaRequisicao.setSenha(Hashing.sha256().hashString(aluno.getSenha(), StandardCharsets.UTF_8).toString());
    }

    //
    public ObservableBoolean isSucessoRequisicao() {
        if(sucessoRequisicao == null){
            sucessoRequisicao = new ObservableBoolean(new Boolean(true));
        }
        return sucessoRequisicao;
    }

    /**
     * Responsável por preparar a requisição que será enviada ao webservice REST.
     * Define a URL, conversor e objeto que será enviado como parâmetro da requisição.
     *
     * @return Interface Call, com todas configurações necessárias para realizar a chamada ao webservice REST.
     */
    private Call<Aluno> preparaRequisicao() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AutenticaoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AutenticaoService autenticaoService = retrofit.create(AutenticaoService.class);
        Call<Aluno> requisicao = autenticaoService.autentica(alunoDaRequisicao);
        return requisicao;
    }

    /**
     * Responsável por executar a chamada ao webservice REST e verificação da resposta recebida.
     * Caso a requisição tenha sido executada com sucesso e tenha retornado código HTTP 200, o menu
     * principal aberta a tela menu principal, caso o código HTTP seja 401, será aberta uma nova
     * tela com o resultado da autenticação.
     * Em caso de falha na execução da chamada ao webservice REST, ou retorno HTTP diferentes dos citados
     * acima, será atribuido a variável sucessoRequisicao o valor false, que indica algum erro na
     * chamada REST.
     *
     */
    public void executa(){
        Call<Aluno> requisicao = preparaRequisicao();
        requisicao.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, Response<Aluno> resposta) {
                if (resposta.code() == 200 || resposta.code() == 401) {
                    respostaRest = new RespostaRest(resposta);
                    // Seleciona a activity que será aberta baseado no código HTTP retornado
                    Intent resultadoIntent = new Intent(context,
                            resposta.code() == 200 ? MenuActivity.class : ResultadoActivity.class);
                    resultadoIntent.putExtra("resposta", respostaRest);
                    context.startActivity(resultadoIntent);
                    ((Activity)(context)).finish();
                }
                else {
                   sucessoRequisicao.set(false);
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                sucessoRequisicao.set(false);
            }
        });
    }

}
