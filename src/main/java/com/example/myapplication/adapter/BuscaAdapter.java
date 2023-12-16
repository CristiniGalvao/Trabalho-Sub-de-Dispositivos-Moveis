package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.buscaM;

import java.util.ArrayList;

public class BuscaAdapter  extends RecyclerView.Adapter<BuscaAdapter.ViewHolder>{
    private ArrayList<buscaM> listabusca;
    private Context context;
    public BuscaAdapter(ArrayList<buscaM> listabusca, Context context) {
        this.listabusca = listabusca;
        this.context = context;
    }
    @NonNull
    @Override
    public BuscaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View listItem = inflater.inflate(R.layout.item_list_busca, parent, false);

    return new ViewHolder(listItem);}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        buscaM paisSelecionado = listabusca.get(position);
        holder.tvCodigo.setText(String.valueOf(paisSelecionado.getCodigo()));
        holder.tvDescricao.setText(paisSelecionado.getDescricao());
    }
    @Override
    public int getItemCount() {
        return this.listabusca.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvCodigo;
        public TextView tvDescricao;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCodigo = itemView.findViewById(R.id.tvCodigo);
            this.tvDescricao = itemView.findViewById(R.id.tvDescricao);
        }
    }
}
