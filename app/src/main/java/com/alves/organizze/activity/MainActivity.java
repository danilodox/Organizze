package com.alves.organizze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alves.organizze.R;
import com.alves.organizze.activity.CadastroActivity;
import com.alves.organizze.activity.LoginActivity;
import com.alves.organizze.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        //setContentView(R.layout.activity_main);



        setButtonBackVisible( false );
        setButtonNextVisible( false );

        addSlide( new FragmentSlide.Builder()
                 .background(android.R.color.white)
                 .fragment(R.layout.intro_um)
                 .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_dois)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_tres)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_quatro)
                .build());

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward( false )
                .build());


    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if ( autenticacao.getCurrentUser() != null ){ //se o usuario diferente de nulo..
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal (){
        startActivity(new Intent(this, PrincipalActivity.class));

    }
}
