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

import com.example.LifestyleApp.R;

public class HeightPickerDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.height_picker, null);
        final NumberPicker feet = view.findViewById(R.id.picker_feet);
        feet.setMinValue(0);
        feet.setMaxValue(9);

        final NumberPicker inches = view.findViewById(R.id.picker_inches);
        inches.setMinValue(0);
        inches.setMaxValue(11);

        builder.setView(view).setPositiveButton(R.string.weight_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView weight = getActivity().findViewById(R.id.heightTextView);
                weight.setText(feet.getValue() + " ft " + inches.getValue() + " in");
            }
        }).setNegativeButton(R.string.height_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HeightPickerDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }
}
