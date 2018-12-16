package com.androide.bobs.unoproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

//    private final List<UserModel> mUsers;

    public CardAdapter(ArrayList users) {
//        mUsers = users;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.carta, parent, false));
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
//        holder.moreButton.setOnClickListener(view -> updateItem(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}