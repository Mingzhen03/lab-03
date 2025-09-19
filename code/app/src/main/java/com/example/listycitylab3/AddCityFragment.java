package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    public interface AddCityDialogListener {
        void addCity(City city);
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new ClassCastException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext())
                .inflate(R.layout.fragment_add_city, null);

        EditText cityEt = view.findViewById(R.id.edit_text_city_text);
        EditText provEt = view.findViewById(R.id.edit_text_province_text);

        return new AlertDialog.Builder(requireContext())
                .setTitle("Add a City")
                .setView(view)
                .setNegativeButton("Cancel", (d, w) -> dismiss())
                .setPositiveButton("ADD", (d, w) -> {
                    String name = cityEt.getText().toString().trim();
                    String province = provEt.getText().toString().trim();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(province) && listener != null) {
                        listener.addCity(new City(name, province));
                    }
                })
                .create();
    }
}
