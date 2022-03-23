package com.doubleclick.uberappjavakotlin.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.doubleclick.uberappjavakotlin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends BaseFragment {


    private LottieAnimationView animationView;
    private TextInputEditText email, passweord;
    private Button signin;
    private TextView donthaveacconnt;
    private TextView forgetpassword;


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
        forgetpassword = view.findViewById(R.id.forgetpassword);
        donthaveacconnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.main_fragment).navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment());
            }
        });

        signin.setOnClickListener(v -> {
            SignIn(Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(passweord.getText()).toString());
        });

        forgetpassword.setOnClickListener(v -> {
            Navigation.findNavController(requireActivity(),R.id.main_fragment).navigate(SignInFragmentDirections.actionSignInFragmentToForgetpassword2());
        });
        return view;
    }

    private void SignIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
            }
        });

    }
}