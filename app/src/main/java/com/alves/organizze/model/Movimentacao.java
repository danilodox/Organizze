package com.alves.organizze.model;

import com.alves.organizze.config.ConfiguracaoFirebase;
import com.alves.organizze.helper.Base64Custom;
import com.alves.organizze.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Movimentacao {

    private String data, categoria, descricao, tipo;
    private Double valor;

    public Movimentacao(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void salvar(String dataEscolhida) {

        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );
        String mesAno = DateUtil.mesAnoDataEscolhida( dataEscolhida );
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child( "movimentacao" )
                .child( idUsuario )
                .child( mesAno )
                .push()
                .setValue( this );
    }
}
