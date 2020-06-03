package com.alves.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alves.organizze.R;
import com.alves.organizze.helper.DateUtil;
import com.alves.organizze.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private EditText campoData, campoCategoria, campoDescricao, campoValor;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);


        // Preenche o campo data com a data atual
        campoData.setText( DateUtil.dataAtual() );
    }

    public void salvarDespesa(View view){

        if(validarCampoDespesa()){
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            movimentacao.setValor( Double.parseDouble( campoValor.getText().toString() ) );
            movimentacao.setCategoria( campoCategoria.getText().toString() );
            movimentacao.setDescricao( campoDescricao.getText().toString());
            movimentacao.setData( data );
            movimentacao.setTipo( "d");

            movimentacao.salvar(data);
        }


    }

    public Boolean validarCampoDespesa(){



        return true;
    }
}
