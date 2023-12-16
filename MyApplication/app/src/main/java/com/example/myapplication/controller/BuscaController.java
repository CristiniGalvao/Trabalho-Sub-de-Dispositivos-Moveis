package com.example.myapplication.controller;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.BuscaDao;
import com.example.myapplication.dto.BuscaDTO;
import com.example.myapplication.model.buscaM;
import com.example.myapplication.retrofit.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscaController {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public String salvarBusca(String codigo, String descricao) {
        try {
            retrofit2.Call<ArrayList<BuscaDTO>> call = new RetrofitConfig().buscaService().getBuscaDTOCall();
            call.enqueue(new Callback<ArrayList<BuscaDTO>>() {
                @Override
                public void onResponse(Call<ArrayList<BuscaDTO>> call, Response<ArrayList<BuscaDTO>> response) {
                    ArrayList<BuscaDTO> listaBusca = response.body();
                    processarListaBusca(listaBusca, codigo, descricao);
                }

                @Override
                public void onFailure(Call<ArrayList<BuscaDTO>> call, Throwable t) {
                    Toast.makeText(context, "Erro ao obter dados da API: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
            return "Erro ao Gravar Busca.";
        }
        return null;
    }

    private void processarListaBusca(ArrayList<BuscaDTO> listaBusca, String codigo, String descricao) {
        for (int i = 0; i < listaBusca.size(); i++) {
            BuscaDTO buscaDTO = listaBusca.get(i);
            salvar(buscaDTO.getCodigo(), buscaDTO.getDescricao(), context, codigo, descricao);
        }
    }

    private void salvar(int codigoBuscaDTO, String descricaoBuscaDTO, Context context, String codigo, String descricao) {
        buscaM busca = BuscaDao.getInstancia(context).getById(codigoBuscaDTO);
        if (busca != null) {
            Toast.makeText(context, "O Código (" + codigo + ") já está cadastrado!", Toast.LENGTH_SHORT).show();
        } else {
            busca = new buscaM();
            busca.setCodigo(codigoBuscaDTO);
            busca.setDescricao(descricaoBuscaDTO);

            BuscaDao.getInstancia(context).insert(busca);
        }
    }

    public ArrayList<buscaM> retornarTodasBuscas() {
        return BuscaDao.getInstancia(context).getAll();
    }
}