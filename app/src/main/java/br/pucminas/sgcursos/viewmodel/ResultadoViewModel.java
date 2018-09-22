package br.pucminas.sgcursos.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.model.RespostaRest;
import br.pucminas.sgcursos.view.LoginActivity;

public class ResultadoViewModel extends ViewModel{

    private static final String TAG = "ResultadoViewModel";
    /* Usado no databinding para mostrar a mensagem de autenticação*/
    private MutableLiveData<String> resposta = new MutableLiveData<>();
    /* Usado no databinding para mostrar a imagem de erro de autenticação*/
    private MutableLiveData<Drawable> icone = new MutableLiveData<>();
    /* Resposta recebida do webservice REST */
    private RespostaRest respostaRest;
    /* Será usado caso haja a necessidade de abrir a tela de login, para nova tentativa de autenticação */
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRespostaRest(RespostaRest responseRest) {
        this.respostaRest = responseRest;
        processaResposta();
    }

    public RespostaRest getRespostaRest() {
        return respostaRest;
    }

    public MutableLiveData<String> getResposta() {
        if(resposta == null){
            resposta = new MutableLiveData<>();
        }
        return resposta;
    }

    public MutableLiveData<Drawable> getIcone() {
        if (icone == null){
            icone = new MutableLiveData<>();
        }
        return icone;
    }

    /**
     * Processa a resposta recebida pelo webservice.
     */
    private void processaResposta() {
        icone.setValue(ContextCompat.getDrawable(context, R.drawable.ic_error));
        resposta.setValue(respostaRest.getErrorMessage());
    }

    /**
     * Método é responsável por abrir a tela de login para que o usuário tente fazer login novamente.
     */
    public void voltarFormularioLogin(){
        Intent voltaTelaLogin = new Intent(context, LoginActivity.class);
        context.startActivity(voltaTelaLogin);
        ((Activity)(context)).finish();
    }

}
