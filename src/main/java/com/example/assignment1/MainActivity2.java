package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView Name;
    TextView Number;
    TextView Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Name = (TextView)findViewById(R.id.name);
        Number =(TextView) findViewById(R.id.number);
        Address = (TextView)findViewById(R.id.address);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("contacts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String Contact_Address = document.getString("Contact_Address");
                                String Contact_Number = document.getString( "Contact_Number");
                                String Contact_Name = document.getString( "Contact_Name");
                                //Log.d("TAG", title + " => " + document.getData());
                                Name.setText(Contact_Name);
                                Number.setText(Contact_Address);
                                Address.setText(Contact_Number);



                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });



    }


}
