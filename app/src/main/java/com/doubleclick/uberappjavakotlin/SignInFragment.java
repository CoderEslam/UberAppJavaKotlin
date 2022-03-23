package com.doubleclick.uberappjavakotlin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {


    private LottieAnimationView animationView;
    private TextInputEditText email, passweord;
    private Button signin;
    private TextView donthaveacconnt;


    public SignInFragment() {
        // Required empty public constructor
    }


    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        animationView = view.findViewById(R.id.animationView);
        email = view.findViewById(R.id.email);
        passweord = view.findViewById(R.id.password);
        signin = view.findViewById(R.id.signin);
        donthaveacconnt = view.findViewById(R.id.donthaveacconnt);
        donthaveacconnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.main_fragment).navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment());
            }
        });


        return view;
    }
}