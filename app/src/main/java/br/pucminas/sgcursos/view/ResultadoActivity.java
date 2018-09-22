package br.pucminas.sgcursos.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.databinding.ResultadoActivityBinding;
import br.pucminas.sgcursos.model.RespostaRest;
import br.pucminas.sgcursos.viewmodel.ResultadoViewModel;

/**
 * Classe responsável por configurar a activity resultado com MVVM, utilizando databinding.
 * Redireciona a resposta recebida do webservice rest a classe responsável pelo viewModel.
 */
public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ResultadoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.resultado_activity);
        ResultadoViewModel viewModel = ViewModelProviders.of(this).get(ResultadoViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setContext(this);
        RespostaRest respostaRest = (RespostaRest) getIntent().getSerializableExtra("resposta");
        viewModel.setRespostaRest(respostaRest);
    }
}
