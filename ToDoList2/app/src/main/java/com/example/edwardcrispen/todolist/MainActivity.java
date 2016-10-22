package com.example.edwardcrispen.todolist;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected final static String FILENAME = "Lists_File";
    protected final static String DEBUG_TAG = "DEBUG_D";
    LinearLayout ListLayout;
    ArrayList<List> lists = new ArrayList<>();
    protected static String currentList = "";
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListLayout = (LinearLayout) findViewById(R.id.ScrollLayout);
        handler = new DBHandler(getApplicationContext(), null, null, 1);
        loadList();
        for(final List list : lists){
            Button button = new Button(getApplicationContext());
            button.setText(list.getName());
            ListLayout.addView(button);
            if (list.getName().equals("+ New List")) {
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        newList();
                    }
                });
            } else {
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        loadNextActivity(list.getName());
                    }
                });
            }
        }
    }

    private void loadList(){
        lists = handler.toArrayList();
        lists.add(0, new List("+ New List"));
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    void newList(){
        EditText editText = new EditText(getApplicationContext());
        editText.setText("Enter new List");
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
                lists.add(new List(s));
                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        loadNextActivity(s);
                    }
                });
                handler.addList(s);
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