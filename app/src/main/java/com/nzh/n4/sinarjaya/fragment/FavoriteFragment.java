package com.nzh.n4.sinarjaya.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nzh.n4.sinarjaya.R;
import com.nzh.n4.sinarjaya.adapter.AgenAdapter;
import com.nzh.n4.sinarjaya.db.DatabaseHelper;
import com.nzh.n4.sinarjaya.model.Agenbus;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    RecyclerView rvFavorit;
    DatabaseHelper database;
    ArrayList<Agenbus>listAgenbusFavorite;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rvFavorit = (RecyclerView) view.findViewById(R.id.rv_favorit);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvFavorit.setLayoutManager(llm);

        //mencetak database
        database = new DatabaseHelper(getActivity());

        //mendapatkan data wisata favorit & dimasukan ke dalam listAgenFavoritr
        listAgenbusFavorite = database.getDataFavorite();

        AgenAdapter adapter = new AgenAdapter(getActivity(), listAgenbusFavorite);

        rvFavorit.setAdapter(adapter);

        return view;

    }

}
