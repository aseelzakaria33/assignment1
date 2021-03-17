package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextContactName;
    EditText editTextContactNumber;
    EditText editTextContactaddress;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextContactName = findViewById(R.id.ContactName);
        editTextContactNumber = findViewById(R.id.ContactNumber);
        editTextContactaddress = findViewById(R.id.ContactAddress);


    }

    public void save_btn(View v) {
        String ContactName = editTextContactName.getText().toString();
        String ContactNumber = editTextContactNumber.getText().toString();
        String ContactAddress = editTextContactaddress.getText().toString();

        // Create a new user with a first and last name
        Map<String, Object> contact = new HashMap<>();
        contact.put("Contact_Name", ContactName);
        contact.put("Contact_Number", ContactNumber);
        contact.put("Contact_Address", ContactAddress);

// Add a new document with a generated ID
        db.collection("contacts")
                .add(contact)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });


    }
}