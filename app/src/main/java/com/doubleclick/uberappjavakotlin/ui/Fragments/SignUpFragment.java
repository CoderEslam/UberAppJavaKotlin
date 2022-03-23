package com.doubleclick.uberappjavakotlin.ui.Fragments;

import static com.doubleclick.uberappjavakotlin.Model.Constant.USER;

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
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Objects;

public class SignUpFragment extends BaseFragment {


    private LottieAnimationView animationView;
    private TextInputEditText name,email,password;
    private Button login;
    private TextView haveAccount;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        animationView = view.findViewById(R.id.animationView);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.Login);
        haveAccount = view.findViewById(R.id.haveAccount);
        haveAccount.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(),R.id.main_fragment).navigate(SignUpFragmentDirections.actionSignUpFragment2ToSignInFragment());
        });
        login.setOnClickListener(v -> {
            Login(Objects.requireNonNull(name.getText()).toString(), Objects.requireNonNull(email.getText()).toString(), Objects.requireNonNull(password.getText()).toString());
        });

        return view;
    }

    private void Login(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("password",password);
                    map.put("id",myId);
                    map.put("image","");
                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            map.put("token",task.getResult());
                            reference.child(USER).child(myId).setValue(map);
                            Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }


}