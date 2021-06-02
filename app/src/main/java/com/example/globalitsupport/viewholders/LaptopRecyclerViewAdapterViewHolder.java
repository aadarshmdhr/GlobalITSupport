package com.example.globalitsupport.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalitsupport.R;

public class LaptopRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewLaptopPosition;
    public TextView textViewLaptopName;
    public TextView textViewLaptopModelNo;
    public TextView textViewLaptopPrice;
    public ImageView imageViewLaptopImage;
    public ConstraintLayout mainContainerLaptopList;

    public LaptopRecyclerViewAdapterViewHolder(View itemView) {
        super(itemView);
        mainContainerLaptopList = itemView.findViewById(R.id.mainContainerLaptopList);
        textViewLaptopPosition = itemView.findViewById(R.id.textViewLaptopPosition);
        textViewLaptopName = itemView.findViewById(R.id.textViewLaptopName);
        textViewLaptopModelNo = itemView.findViewById(R.id.textViewLaptopModelNo);
        textViewLaptopPrice = itemView.findViewById(R.id.textViewLaptopPrice);
        imageViewLaptopImage = itemView.findViewById(R.id.imageViewLaptopImage);
    }
}
