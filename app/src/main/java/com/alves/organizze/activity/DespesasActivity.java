package com.alves.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alves.organizze.R;
import com.alves.organizze.config.ConfiguracaoFirebase;
import com.alves.organizze.helper.DateUtil;
import com.alves.organizze.model.Movimentacao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class DespesasActivity extends AppCompatActivity {

    private EditText campoData, campoCategoria, campoDescricao, campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseref = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

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
        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if ( !textoValor.isEmpty() ){
            if ( !textoData.isEmpty() ){
                if ( !textoCategoria.isEmpty() ){
                    if ( !textoDescricao.isEmpty() ){
                        return true;
                    }else{
                        Toast.makeText(DespesasActivity.this, "Descrição não foi preenchido!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(DespesasActivity.this, "Categoria não foi preenchido!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(DespesasActivity.this, "Data não foi preenchido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(DespesasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarDespesatotal(){
        
    }
}
