package com.example.myapplication.service;

import com.example.myapplication.dto.BuscaDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BuscaService {
    @GET("busca/paises")
    Call<ArrayList<BuscaDTO>> getBuscaDTOCall();
}
