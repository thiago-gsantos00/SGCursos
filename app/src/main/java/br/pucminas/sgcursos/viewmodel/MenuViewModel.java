package br.pucminas.sgcursos.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;

import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.model.RespostaRest;

public class MenuViewModel extends ViewModel{

    private static final String TAG = "MenuViewModel";
    /* Usado no databinding do campo que mostra a mensagem de boas vindas pro aluno*/
    private MutableLiveData<String> informacaoAluno = new MutableLiveData<>();
    /* Resposta recebida do webservice REST */
    private RespostaRest respostaRest;
    /* Será usado para utilizar recursos */
    private Context context;

    public MutableLiveData<String> getInformacaoAluno() {
        if(informacaoAluno == null){
            informacaoAluno = new MutableLiveData<>();
        }
        return informacaoAluno;
    }

    public void setRespostaRest(RespostaRest respostaRest){
        this.respostaRest = respostaRest;
        processaResposta();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Processa a resposta recebida pelo webservice.
     */
    private void processaResposta(){
        String infoAluno = context.getString(R.string.message_login, respostaRest.getAluno().getNome());
        informacaoAluno.setValue(infoAluno);
    }
}
