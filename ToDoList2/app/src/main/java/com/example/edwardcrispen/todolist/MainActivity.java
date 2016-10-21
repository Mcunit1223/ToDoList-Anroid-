package com.example.edwardcrispen.todolist;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout ListLayout;
    ArrayList<String> lists = new ArrayList<>();
    protected static String currentList = "";


    void loadList(){
        lists.add("+ New List");
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
                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        newList();
                    }
                });
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

    void newList(){
        EditText editText = new EditText(getApplicationContext());
        editText.setText("Enter new Task");
        editText.setTextColor(Color.BLACK);
        editText.setBackgroundColor(Color.GRAY);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    Button button = new Button(getApplicationContext());
                    final String s = v.getText().toString();
                    button.setText(s);
                    ListLayout.removeView(v);
                    ListLayout.addView(button);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    button.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            loadNextActivity(s);
                        }
                    });
                }
                return false;
            }
        });
        ListLayout.addView(editText);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        editText.setSelection(0, editText.length());
    }

    void loadNextActivity(String name){
        currentList = name;
        Intent intent = new Intent(this, ShowList.class);
        startActivity(intent);
    }
}