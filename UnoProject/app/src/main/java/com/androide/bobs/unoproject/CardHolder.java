package com.androide.bobs.unoproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CardHolder extends RecyclerView.ViewHolder {

    public ImageView moreButton;

    public CardHolder(View itemView) {
        super(itemView);
        moreButton = (ImageView) itemView.findViewById(R.id.img_carta);
    }

}
