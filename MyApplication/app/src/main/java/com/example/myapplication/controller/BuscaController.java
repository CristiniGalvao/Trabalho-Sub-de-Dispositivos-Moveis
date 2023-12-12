package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.dao.BuscaDao;
import com.example.myapplication.model.buscaM;

import java.util.ArrayList;

public class BuscaController {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }
    public String salvarBusca(String codigo, String descricao){
        try{
            if(codigo.equals("") || codigo.isEmpty()){
                return "Informe o Código do País!";
            }
            if(descricao.equals("") || descricao.isEmpty()){
                return "Informe a  Descrição do País!";
            }

            buscaM busca = BuscaDao.getInstancia(context)
                    .getById(Integer.parseInt(codigo));
            if(busca != null){
                return "O Código ("+codigo+") já está cadastrado!";
            }else{
                busca = new buscaM();
                busca.setCodigo(Integer.parseInt(codigo));
                busca.setDescricao(descricao);

                BuscaDao.getInstancia(context).insert(busca);
            }

        }catch (Exception ex){
            return "Erro ao Gravar País.";
        }
        return null;
    }
    public ArrayList<buscaM> retornarTodasBuscas(){
        return BuscaDao.getInstancia(context).getAll();
    }
}
