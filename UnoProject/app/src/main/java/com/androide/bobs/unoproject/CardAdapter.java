package com.androide.bobs.unoproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import objects.Carta;

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    private ArrayList<Carta> cartas = new ArrayList<>();

    public CardAdapter(ArrayList cartas) {
        cartas = cartas;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carta, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.carta.setOnClickListener(view -> onclickCarta(position));
    }

    @Override
    public int getItemCount() {
        return cartas != null ? cartas.size() : 0;
    }

    /**
     * Método publico chamado para atualziar a lista.
     * @param carta Novo objeto que será incluido na lista.
     */
    public void updateList(Carta carta) {
        insertItem(carta);
    }

    // Método responsável por inserir uma nova carta na lista
    //e notificar que há novos itens.
    private void insertItem(Carta carta) {
        cartas.add(carta);
        notifyItemInserted(getItemCount());
    }
    private void removerItem(int position) {
        cartas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, cartas.size());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void onclickCarta(int position) {
        Carta cartinha = cartas.get(position);

//        userModel.incrementAge();
        notifyItemChanged(position);
    }

}