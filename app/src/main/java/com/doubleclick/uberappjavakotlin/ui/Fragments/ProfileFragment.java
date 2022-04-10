package com.doubleclick.uberappjavakotlin.ui.Fragments;

import static android.app.Activity.RESULT_OK;
import static com.doubleclick.uberappjavakotlin.Model.Constant.USER;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleclick.uberappjavakotlin.R;
import com.doubleclick.uberappjavakotlin.Views.photoview.PhotoView;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class ProfileFragment extends BaseFragment {

    private PhotoView myPhoto;
    private TextView MyName, MyPhone;
    private ImageView editName, editPhone;
    private AlertDialog.Builder builder;
    private FloatingActionButton ChangFoto;
    private int request_code = 100;
    private Uri imageUri;
    private StorageTask task;
    private StorageReference referenceImages;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        referenceImages = FirebaseStorage.getInstance().getReference().child("Images");
        myPhoto = view.findViewById(R.id.myPhoto);
        MyName = view.findViewById(R.id.MyName);
        MyPhone = view.findViewById(R.id.MyPhone);
        editName = view.findViewById(R.id.editName);
        editPhone = view.findViewById(R.id.editPhone);
        ChangFoto = view.findViewById(R.id.ChangFoto);

        editName.setOnClickListener(v -> {
            Edit("name");
        });

        editPhone.setOnClickListener(v -> {
            Edit("phone");
        });

        ChangFoto.setOnClickListener(v -> {
            openImage();
        });
        return view;


    }

    public void Edit(String typeEdit) {
        builder = new AlertDialog.Builder(getContext());
        View editView = LayoutInflater.from(getContext()).inflate(R.layout.layout_edit, null, false);
        TextInputEditText edit = editView.findViewById(R.id.edit);
        TextView ok = editView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put(typeEdit, edit.getText().toString());
                reference.child(USER).child(myId).updateChildren(map);
            }
        });
        builder.setView(editView);
        builder.show();
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, request_code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code && resultCode == RESULT_OK && data.getData() != null) {
            imageUri = data.getData();
            myPhoto.setImageURI(imageUri);
            uploadImage();
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
//            loading.setVisibility(View.VISIBLE);
            StorageReference referenceStorage = referenceImages.child(myId);
            task = referenceStorage.putFile(imageUri);
            task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (task.isSuccessful()) {
                        return referenceStorage.getDownloadUrl();
                    }
                    return null;
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri uri = task.getResult();
                    String image = uri.toString();
                    Map<String, Object> map = new HashMap<>();
                    map.put("Image", image);
                    reference.child(USER).child(myId).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
//                            loading.setVisibility(View.GONE);
                        }
                    });

                }
            });

        }

    }
}