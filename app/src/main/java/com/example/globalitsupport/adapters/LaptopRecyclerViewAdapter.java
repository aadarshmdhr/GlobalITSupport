package com.example.globalitsupport.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalitsupport.R;
import com.example.globalitsupport.models.Laptop;
import com.example.globalitsupport.viewholders.LaptopRecyclerViewAdapterViewHolder;

import java.util.ArrayList;

public class LaptopRecyclerViewAdapter extends RecyclerView.Adapter<LaptopRecyclerViewAdapterViewHolder> {
    ArrayList<Laptop> laptopArrayList;
    LaptopRecyclerAdapterInteraction listener;

    public LaptopRecyclerViewAdapter(ArrayList<Laptop> laptopArrayList, LaptopRecyclerAdapterInteraction listener){
       this.laptopArrayList = laptopArrayList;
       this.listener = listener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public LaptopRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new LaptopRecyclerViewAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_laptop_view_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull LaptopRecyclerViewAdapterViewHolder holder, int position) {
        if(laptopArrayList != null){
            if(!laptopArrayList.isEmpty()) {
                Laptop laptop = laptopArrayList.get(position);
                holder.textViewLaptopName.setText(laptop.getName());
                holder.textViewLaptopModelNo.setText(laptop.getModelNo());
                holder.textViewLaptopPosition.setText(String.valueOf(position + 1));
                holder.textViewLaptopPrice.setText(String.valueOf(laptop.getPrice()));

                holder.mainContainerLaptopList.setOnClickListener(view -> {
                    listener.onLaptopClicked();
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return laptopArrayList.size();
    }

    public interface LaptopRecyclerAdapterInteraction{
        void onLaptopClicked();
    }
}
