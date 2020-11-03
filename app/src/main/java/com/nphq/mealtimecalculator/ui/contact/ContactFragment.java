package com.nphq.mealtimecalculator.ui.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nphq.mealtimecalculator.R;

public class ContactFragment extends Fragment {

    private ContactViewModel slideshowViewModel;
    private EditText mEditTextMessage;
    private EditText mEditTextTo;
    private EditText mEditTextSubject;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact, container, false);


        mEditTextMessage = root.findViewById(R.id.edit_text_message);
        mEditTextTo = root.findViewById(R.id.edit_text_to);
        mEditTextSubject = root.findViewById(R.id.edit_text_subject);

        Button buttonSend = root.findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        hideKeyboard(getActivity(), mEditTextMessage);
        hideKeyboard(getActivity(),mEditTextTo);
        hideKeyboard(getActivity(),mEditTextSubject);

        return root;
    }

    private void sendMail(){
        String recipient = mEditTextTo.getText().toString();
        String[] recipients = recipient.split(",");

        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));

    }

    public static void hideKeyboard(final Context context, final View v){
        v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });


    }
}