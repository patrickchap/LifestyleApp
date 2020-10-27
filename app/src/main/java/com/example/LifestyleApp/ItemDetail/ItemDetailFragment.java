package com.example.LifestyleApp.ItemDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.LifestyleApp.R;

import org.w3c.dom.Text;

//import util.GetWeatherDataUtil;

public class ItemDetailFragment extends Fragment {

    private TextView mTvItemDetail;

    public ItemDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.itemdetail_fragment, container, false);
        mTvItemDetail = view.findViewById(R.id.tv_detail);
        String detailString = getArguments().getString("item_detail");
        if (detailString != null && !detailString.equals("Weather")) {
            mTvItemDetail.setText(detailString);
        }
//        else if(detailString.equals("Weather")){ // if the detail is weather make api call to openweathermap
//            GetWeatherDataUtil.getWeatherInfo(getContext(), mTvItemDetail);
//        }
        return view;
    }
}
