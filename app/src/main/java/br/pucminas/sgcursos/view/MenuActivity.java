package br.pucminas.sgcursos.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.ilhasoft.support.validation.Validator;
import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.databinding.LoginActivityBinding;
import br.pucminas.sgcursos.databinding.MenuActivityBinding;
import br.pucminas.sgcursos.model.RespostaRest;
import br.pucminas.sgcursos.viewmodel.LoginViewModel;
import br.pucminas.sgcursos.viewmodel.MenuViewModel;

/**
 * Classe responsável por configurar a activity menu com MVVM, utilizando databinding.
 * Redireciona a resposta recebida do webservice rest a classe responsável pelo viewModel.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.menu_activity);
        MenuViewModel viewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setContext(this);
        RespostaRest respostaRest = (RespostaRest) getIntent().getSerializableExtra("resposta");
        viewModel.setRespostaRest(respostaRest);
    }
}
