package br.pucminas.sgcursos.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import br.com.ilhasoft.support.validation.Validator;
import br.pucminas.sgcursos.R;
import br.pucminas.sgcursos.databinding.LoginActivityBinding;
import br.pucminas.sgcursos.viewmodel.LoginViewModel;

/**
 * Classe responsável por configurar a activity login com MVVM, utilizando databinding.
 * A criação de um validador de campos da tela login é feita.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setContext(this);
        Validator validator = new Validator(binding);
        validator.enableFormValidationMode();
        viewModel.setValidator(validator);
    }

}
