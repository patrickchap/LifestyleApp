package com.example.LifestyleApp.MasterList;

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
import com.example.LifestyleApp.R;

import java.util.List;

public class MasterListFragment extends Fragment {
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.masterlist_layout, container, false);
        mRecyclerView = fragmentView.findViewById(R.id.rv_master);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        CustomMasterList customListData = getArguments().getParcelable("item_list");
        List<String> inputList = customListData.getItemList();

        mAdapter = new RvAdapter(inputList);
        mRecyclerView.setAdapter(mAdapter);

        return fragmentView;
    }
}