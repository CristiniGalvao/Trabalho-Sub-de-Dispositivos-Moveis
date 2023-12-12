package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.helper.SQLiteDataHelper;
import com.example.myapplication.model.buscaM;

import java.util.ArrayList;

public class BuscaDao implements IGenericDao<buscaM>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[]colunas = {"CODIGO", "DESCRICAO"};
    private String tabela = "PAISES";
    private Context context;
    private static BuscaDao instancia;
    public static BuscaDao getInstancia(Context context){
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
    @Override
    public long insert (buscaM obj){
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getCodigo());
            valores.put(colunas[1],obj.getDescricao());

            return baseDados.insert(tabela,null,valores);
        }catch (SQLException ex){
            Log.e("TRABALHO_SUB", "ERRO: BuscaDao.insert() "+ex.getMessage());
        }
        return 0;
    }
    @Override
    public long update(buscaM obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDescricao());

            String[]identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("TRABALHO_SUB", "ERRO: BuscaDao.update() "+ex.getMessage());
        }
        return 0;
    }
    @Override
    public long delete(buscaM obj) {
        try{
            String[]identificador = {String.valueOf(obj.getCodigo())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("TRABALHO_SUB", "ERRO: BuscaDao.delete() "+ex.getMessage());
        }
        return 0;
    }
    public ArrayList<buscaM> getAll() {
        ArrayList<buscaM> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    buscaM busca = new buscaM();
                    busca.setCodigo(cursor.getInt(0));
                    busca.setDescricao(cursor.getString(1));

                    lista.add(busca);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("TRABALHO_SUB", "ERRO: BuscaDao.getAll() "+ex.getMessage());
        }

        return lista;
    }
    @Override
    public buscaM getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                buscaM busca = new buscaM();
                busca.setCodigo(cursor.getInt(0));
                busca.setDescricao(cursor.getString(1));

                return busca;
            }

        }catch (SQLException ex){
            Log.e("TRABALHO_SUB", "ERRO: BuscaDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
