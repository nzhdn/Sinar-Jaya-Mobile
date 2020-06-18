package com.nzh.n4.sinarjaya.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.nzh.n4.sinarjaya.R;
import com.nzh.n4.sinarjaya.adapter.AgenAdapter;
import com.nzh.n4.sinarjaya.helper.ServiceClient;
import com.nzh.n4.sinarjaya.helper.ServiceGenerator;
import com.nzh.n4.sinarjaya.model.ListAgen;
import com.nzh.n4.sinarjaya.model.Agenbus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    //deklarasi
    RecyclerView rvHome;
    List<Agenbus>listAgenbus = new ArrayList<>();
    ProgressDialog pd;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //menghubungkan logic dengan kulitnya
        rvHome = (RecyclerView)view.findViewById(R.id.rv_home);
        //menentukan bentuk recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //memasukan settingan bentuk ke rvHome
        rvHome.setLayoutManager(llm);

        //membuat objek servisnya
        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        //memilih jenis service yang dibutuhkan
        Call<ListAgen> getListAgen = service.getAgenbus("sinjay");

        //efek loading mengambil data
        pd = new ProgressDialog(getActivity());
        pd.setMessage("L O A D I N G");
        pd.show();

        //mengirim requestdan menerima respon dari server
        getListAgen.enqueue(new Callback<ListAgen>() {
            @Override
            public void onResponse(Call<ListAgen> call, Response<ListAgen> response) {
                //menghilangkan efek loading
                pd.dismiss();
                //menyimpan respon server ke listAgenbus
                listAgenbus = response.body().getListAgenSinarJaya();
                //memasukan listAgenbus ke dalam adapter
                AgenAdapter adapter = new AgenAdapter(getActivity(), listAgenbus);
                //setting adapter di rvHome sesuai adapter yg terbentuk
                rvHome.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListAgen> call, Throwable t) {
                Toast.makeText(getActivity(),""+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
