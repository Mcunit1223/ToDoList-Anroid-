package com.example.edwardcrispen.todolist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowList extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    LinearLayout ListLayout;
    final float CHECKBOX_SCALE_SIZE_Y = 1.5f;
    final float CHECKBOX_SCALE_SIZE_X = 1.0f;
    final int CHECKBOX_PADDING = 25;

    void loadList(){
        list.add("+ New Task");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ListLayout =(LinearLayout) findViewById(R.id.ListView);
        if(savedInstanceState != null){
            list = savedInstanceState.getStringArrayList(MainActivity.currentList + "_contents");
        }
        loadList();
        for(final String listName : list) {
            if(listName.equals("+ New Task")){
                Button button = new Button(getApplicationContext());
                button.setText(listName);
                button.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        newTask();
                    }
                });
                ListLayout.addView(button);
            }else {
                CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setText(listName);
                checkBox.setScaleY(CHECKBOX_SCALE_SIZE_Y);
                checkBox.setScaleX(CHECKBOX_SCALE_SIZE_X);
                checkBox.setPadding(CHECKBOX_PADDING, CHECKBOX_PADDING, 0, CHECKBOX_PADDING);
                checkBox.setTextColor(Color.DKGRAY);
                checkBox.setBottom(10);
                ListLayout.addView(checkBox);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        String name = MainActivity.currentList;
        bundle.putStringArrayList(name + "_contents", list);
    }

    void newTask(){
        EditText editText = new EditText(getApplicationContext());
        editText.setText("Enter new Task");
        editText.setTextColor(Color.BLACK);
        editText.setPadding(CHECKBOX_PADDING, CHECKBOX_PADDING, 0, CHECKBOX_PADDING);
        editText.setBackgroundColor(Color.GRAY);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    CheckBox checkBox = new CheckBox(getApplicationContext());
                    String s = v.getText().toString();
                    checkBox.setText(s);
                    checkBox.setScaleY(CHECKBOX_SCALE_SIZE_Y);
                    checkBox.setScaleX(CHECKBOX_SCALE_SIZE_X);
                    checkBox.setPadding(CHECKBOX_PADDING, CHECKBOX_PADDING, 0, CHECKBOX_PADDING);
                    checkBox.setTextColor(Color.DKGRAY);
                    checkBox.setBottom(10);
                    ListLayout.removeView(v);
                    ListLayout.addView(checkBox);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }else{
                    ListLayout.removeView(v);
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
}
