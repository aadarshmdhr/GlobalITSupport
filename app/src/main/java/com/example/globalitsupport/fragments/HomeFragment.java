package com.example.globalitsupport.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globalitsupport.R;
import com.example.globalitsupport.adapters.LaptopRecyclerViewAdapter;
import com.example.globalitsupport.models.Laptop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    HomeFragmentInteraction listener;
    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        if(context instanceof HomeFragmentInteraction){
            listener = (HomeFragmentInteraction) context;
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        RecyclerView recyclerViewLaptop = requireActivity().findViewById(R.id.recyclerViewLaptop);
        recyclerViewLaptop.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewLaptop.setAdapter(new LaptopRecyclerViewAdapter(getDummyData(), () -> listener.onLaptopClicked())); //Fetch & Send laptop data from database
    }

    private ArrayList<Laptop> getDummyData(){
        ArrayList<Laptop> laptops = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Laptop laptop = new Laptop();
            laptop.setId(i + 1);
            laptop.setName("Name "+i);
            laptop.setModelNo("Model No. "+i);
            laptop.setPrice(i * 1000);
            laptops.add(laptop);
        }
        return laptops;
    }

    public interface HomeFragmentInteraction{
        void onLaptopClicked();
    }
}
