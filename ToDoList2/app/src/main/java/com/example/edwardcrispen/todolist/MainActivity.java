package com.example.edwardcrispen.todolist;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout ListLayout;
    ArrayList<String> lists = new ArrayList<>();
    protected static String currentList = "";


    void loadList(){
        lists.add("+ New List");
        for (int i = 0; i < 100; i++){
            lists.add("List " + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListLayout = (LinearLayout) findViewById(R.id.ScrollLayout);
        loadList();
        for(final String listName : lists){
            Button button = new Button(getApplicationContext());
            button.setText(listName);
            ListLayout.addView(button);
            if(listName.equals("+ New List")){

            }else{
                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        loadNextActivity(listName);
                    }
                });
            }
        }
    }

    void loadNextActivity(String name){
        currentList = name;
        Intent intent = new Intent(this, ShowList.class);
        startActivity(intent);
    }
}