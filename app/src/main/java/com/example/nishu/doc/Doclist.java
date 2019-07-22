package com.example.nishu.doc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Doclist extends AppCompatActivity {

    ArrayList<String> personNames = new ArrayList<>(Arrays.asList("Dr.Biswas Morey", "Dr.R Rastogi", "Dr.Mallinath", "Dr.Anthony Rick", "Dr. Subh Mandol", "Dr.Abhinash Adi", "Dr.Swadesh Abhigyan","Dr.Ranvijay Bhatia", "Dr.Hanh Malhotra", "Person 10", "Person 11", "Person 12", "Person 13", "Person 14"));
    ArrayList<Integer> personImages = new ArrayList<>(Arrays.asList(R.drawable.doc1, R.drawable.doc2, R.drawable.doc3, R.drawable.doc4, R.drawable.doc4, R.drawable.doc5, R.drawable.doc6,R.drawable.doc7, R.drawable.doc1, R.drawable.doc2, R.drawable.doc3, R.drawable.doc4, R.drawable.doc4, R.drawable.doc5));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doclist);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
// set a LinearLayoutManager with default vertical orientaion
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
// call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(Doclist.this, personNames,personImages);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}
