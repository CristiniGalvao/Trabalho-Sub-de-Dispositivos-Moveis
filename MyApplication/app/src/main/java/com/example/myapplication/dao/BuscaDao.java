package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.helper.SQLiteDataHelper;
import com.example.myapplication.model.buscaM;

public class BuscaDao implements IGenericDao<buscaM>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"CODIGO", "DESCRICAO"};
    private String tabela = "PAISES";
    private Context context;
    private static BuscaDao instancia;
    private static BuscaDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new BuscaDao(context);
        }else{
            return instancia;
        }
    }
    private BuscaDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context,"TRABALHO_SUB",null,1);
    }
    public long insert (buscaM obj){
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getCodigo());
            valores.put(colunas[1],obj.getDescricao());

            return baseDados.insert(tabela,null,valores);
        }
    }
}
