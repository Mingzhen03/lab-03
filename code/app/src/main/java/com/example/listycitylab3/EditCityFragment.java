package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    private static final String ARG_CITY = "arg_city";
    private static final String ARG_POSITION = "arg_position";

    public static EditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(requireContext())
                .inflate(R.layout.fragment_edit_city, null);

        EditText nameEt = view.findViewById(R.id.edit_city_name);
        EditText provEt = view.findViewById(R.id.edit_city_province);

        Bundle args = requireArguments();
        City city = (City) args.getSerializable(ARG_CITY);
        int position = args.getInt(ARG_POSITION, -1);

        if (city != null) {
            nameEt.setText(city.getName());
            provEt.setText(city.getProvince());
        }

        return new AlertDialog.Builder(requireContext())
                .setTitle("Edit City")
                .setView(view)
                .setNegativeButton("Cancel", (d, w) -> dismiss())
                .setPositiveButton("OK", (d, w) -> {

                    String newName = nameEt.getText().toString().trim();
                    String newProv = provEt.getText().toString().trim();


                    if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newProv)) return;

                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity())
                                .applyCityEdit(position, newName, newProv);
                    }
                })
                .create();
    }
}
