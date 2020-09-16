package com.example.LifestyleApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.ViewHolder>{
    private List<String> mListItems;
    private Context mContext;
    private DataPasser mDataPasser;

    public MyRVAdapter(List<String> inputList) {
        mListItems = inputList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected View itemLayout;
        protected Button itemTvData;

        public ViewHolder(View view){
            super(view);
            itemLayout = view;
            itemTvData = view.findViewById(R.id.tv_data);
        }
    }

    @NonNull
    @Override
    public MyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        try{
            mDataPasser = (DataPasser) mContext;
        }catch(ClassCastException e){
            throw new ClassCastException(mContext.toString()+ " must implement OnDataPass");
        }

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View myView = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.itemTvData.setText(mListItems.get(position));
        holder.itemTvData.setOnClickListener(new View.OnClickListener(){
                                                 @Override
                                                 public void onClick(View view) {
                                                     mDataPasser.passData(position);
                                                 }
                                             }
        );
    }


    @Override
    public int getItemCount() {

        return mListItems.size();
    }

    public interface DataPasser{
        void passData(int position);
    }
}
