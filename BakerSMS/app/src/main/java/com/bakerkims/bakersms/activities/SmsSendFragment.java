package com.bakerkims.bakersms.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bakerkims.bakersms.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SmsSendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SmsSendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SmsSendFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.sms_my_number_edittext)
    EditText mMyNumEdit;
    @BindView(R.id.sms_receiver_number_edittext)
    EditText mReceiverEdit;
    @BindView(R.id.sms_send_btn)
    Button mSend;
    @BindView(R.id.sms_cancel_btn)
    Button mCancel;

    public SmsSendFragment() {
        // Required empty public constructor
    }

    public static SmsSendFragment newInstance() {
        SmsSendFragment fragment = new SmsSendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_send_fragment, container, false);
        ButterKnife.bind(this, view);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Send!!", Toast.LENGTH_SHORT).show();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancel!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
