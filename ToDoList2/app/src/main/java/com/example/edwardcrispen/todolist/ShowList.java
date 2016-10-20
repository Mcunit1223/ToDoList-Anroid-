package com.example.edwardcrispen.todolist;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    LinearLayout ListLayout;
    final float CHECKBOX_SCALE_SIZE_Y = 1.5f;
    final float CHECKBOX_SCALE_SIZE_X = 1.0f;
    final int CHECKBOX_PADDING = 25;

    void loadList(){
        list.add("+ New Task");
        for (int i = 0; i < 10; i++){
            list.add("Do This #" + i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ListLayout =(LinearLayout) findViewById(R.id.ListView);
        loadList();
        for(final String listName : list) {
            CheckBox checkBox = new CheckBox(getApplicationContext());
            checkBox.setText(listName);
            checkBox.setScaleY(CHECKBOX_SCALE_SIZE_Y);
            checkBox.setScaleX(CHECKBOX_SCALE_SIZE_X);
            checkBox.setPadding(0, CHECKBOX_PADDING, 0, CHECKBOX_PADDING);
            checkBox.setTextColor(Color.DKGRAY);
            ListLayout.addView(checkBox);
        }
    }
}
