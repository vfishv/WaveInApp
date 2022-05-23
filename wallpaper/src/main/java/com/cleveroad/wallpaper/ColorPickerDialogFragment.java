package com.cleveroad.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Color picker
 */
public class ColorPickerDialogFragment extends DialogFragment {

    public static final String KEY_INDEX = "INDEX";
    public static final String KEY_COLOR = "COLOR";
    @BindView(R.id.picker)
    ColorPicker colorPicker;
    @BindView(R.id.svbar)
    SVBar svBar;
    @BindView(R.id.btn_save)
    Button btnSave;

    public static ColorPickerDialogFragment instance(int index, @ColorInt int color) {
        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        args.putInt(KEY_INDEX, index);
        ColorPickerDialogFragment fragment = new ColorPickerDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color_picker, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorPicker.addSVBar(svBar);
        colorPicker.setColor(getArguments().getInt(KEY_COLOR));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(KEY_INDEX, getArguments().getInt(KEY_INDEX));
                data.putExtra(KEY_COLOR, colorPicker.getColor());
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, data);
                dismiss();
            }
        });
    }
}
