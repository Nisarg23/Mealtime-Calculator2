package com.nphq.mealtimecalculator.ui.sleep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nphq.mealtimecalculator.R;

public class SleepFragment extends Fragment {

    private SleepViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SleepViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sleep, container, false);
        final TextView textView = root.findViewById(R.id.text_sleep);

        return root;
    }
}