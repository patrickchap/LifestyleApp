package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.LifestyleApp.GoalManager.GoalManagerActivity;
import com.example.LifestyleApp.MasterList.MasterList;
import com.example.LifestyleApp.R;

public class GoalWeightPicker extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.weight_picker, null);
        final NumberPicker number = view.findViewById(R.id.weightPickerNumber);
        number.setMinValue(0);
        number.setMaxValue(900);

        final NumberPicker decimal = view.findViewById(R.id.weightPickerDecimal);
        decimal.setMinValue(0);
        decimal.setMaxValue(99);

        builder.setView(view).setPositiveButton(R.string.weight_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView goalWeight = getActivity().findViewById(R.id.goalWeightTextView);
                goalWeight.setText(number.getValue() + "." + decimal.getValue() + " lbs >");

//                if(isTablet()){
//                    ((MasterList)getActivity()).passGoalWeight(number.getValue() + "." + decimal.getValue() + " lbs >");
//                }else{
//                    ((GoalManagerActivity)getActivity()).passGoalWeight(number.getValue() + "." + decimal.getValue() + " lbs >");
//                }
            }
        }).setNegativeButton(R.string.weight_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GoalWeightPicker.this.getDialog().cancel();
            }
        });

        return builder.create();
    }


    boolean isTablet()
    {
        return getResources().getBoolean(R.bool.isTablet);
    }
}
