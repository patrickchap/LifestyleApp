package com.example.LifestyleApp.ItemDetail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.LifestyleApp.R;

public class ItemDetailActivity extends AppCompatActivity {

    private ItemDetailFragment mItemDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mItemDetailFragment = new ItemDetailFragment();
        mItemDetailFragment.setArguments(getIntent().getExtras());

        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.replace(R.id.fl_frag_itemdetail_container_phone, mItemDetailFragment, "frag_itemdetail");
        fTrans.commit();
    }
}
