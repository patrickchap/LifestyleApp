package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
//import com.example.LifestyleApp.GoalManager.GoalManagerActivity;
import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.R;

public class ActivityLevelPicker extends DialogFragment {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_picker, null);

        final Spinner spinner = view.findViewById(R.id.activity_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setView(view);
        builder.setPositiveButton(R.string.gender_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView activityLevel = getActivity().findViewById(R.id.activityLevelTextView);
                activityLevel.setText(spinner.getSelectedItem().toString());
//                if(isTablet()){
//                    ((MasterList)getActivity()).passActivityLeve(spinner.getSelectedItem().toString());
//                }
//                else {
//                    ((GoalManagerActivity)getActivity()).passActivityLeve(spinner.getSelectedItem().toString());
//                }
            }
        });

        builder.setNegativeButton(R.string.gender_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityLevelPicker.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    boolean isTablet()
    {
        return getResources().getBoolean(R.bool.isTablet);
    }
}
