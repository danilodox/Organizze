package com.alves.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alves.organizze.R;
import com.alves.organizze.config.ConfiguracaoFirebase;
import com.alves.organizze.helper.Base64Custom;
import com.alves.organizze.helper.DateUtil;
import com.alves.organizze.model.Movimentacao;
import com.alves.organizze.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private EditText campoData, campoCategoria, campoDescricao, campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseref = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);


        // Preenche o campo data com a data atual
        campoData.setText( DateUtil.dataAtual() );
        recuperarReceitatotal();
        //finish();
    }

    public void salvarReceita(View view){

        if(validarCampoReceita()){
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();

            Double valorRecuperado = Double.parseDouble( campoValor.getText().toString() );

            movimentacao.setValor( valorRecuperado);
            movimentacao.setCategoria( campoCategoria.getText().toString() );
            movimentacao.setDescricao( campoDescricao.getText().toString());
            movimentacao.setData( data );
            movimentacao.setTipo( "d");

            Double receitaAtualizada = receitaTotal + valorRecuperado;
            atualizarReceita( receitaAtualizada );

            movimentacao.salvar(data);
        }




    }

    public Boolean validarCampoReceita(){
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
                        Toast.makeText(ReceitasActivity.this, "Descrição não foi preenchido!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(ReceitasActivity.this, "Categoria não foi preenchido!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(ReceitasActivity.this, "Data não foi preenchido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(ReceitasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void recuperarReceitatotal(){
        //AULA 20.

        String emailUsuario= autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );

        DatabaseReference usuarioRef = firebaseref.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarReceita( Double receita){
        String emailUsuario= autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );

        DatabaseReference usuarioRef = firebaseref.child("usuarios").child(idUsuario);
    }
}
