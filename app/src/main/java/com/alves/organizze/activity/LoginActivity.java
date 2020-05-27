package com.alves.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alves.organizze.R;
import com.alves.organizze.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.buttonEntrar);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                if( !textEmail.isEmpty() ){
                    if( !textoSenha.isEmpty()){

                    }else{
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
