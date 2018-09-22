package br.pucminas.sgcursos.viewmodel;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import br.com.ilhasoft.support.validation.Validator;
import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.model.Aluno;
import br.pucminas.sgcursos.service.RequisicaoDeAutenticacao;

public class LoginViewModel extends ViewModel{

    private static final String TAG = "LoginViewModel";
    /* Usado no databinding para campos de e-mail e senha*/
    private ObservableField<Aluno> aluno;
    /* Usado para mensagens Toast, ProgressDialog e abrir nova activity*/
    private Context context;
    /* Usado para validação dos campos e-mail e senha da activity login*/
    private Validator validator;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ObservableField<Aluno> getAluno() {
        if (aluno == null){
            aluno = new ObservableField<>(new Aluno());
        }
        return aluno;
    }

    public void autenticarAluno(final View view){
        ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (validator.validate()) {
            if(isOnline()) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();

                RequisicaoDeAutenticacao requisicaoDeAutenticacao = new RequisicaoDeAutenticacao(context, aluno.get());
                requisicaoDeAutenticacao.executa();

                //
                requisicaoDeAutenticacao.isSucessoRequisicao().addOnPropertyChangedCallback(
                        new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        if(!requisicaoDeAutenticacao.isSucessoRequisicao().get()) {
                            progressDialog.dismiss();
                            Toast.makeText(context, context.getText(R.string.error_message_communication_rest), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(context, context.getText(R.string.error_message_no_connection), Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }

    /**
     * Verifica se a conexão de internet está ativa.
     * @return true se há conexão de internet ativa ou false se a conexão estiver inativa.
     */
    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
