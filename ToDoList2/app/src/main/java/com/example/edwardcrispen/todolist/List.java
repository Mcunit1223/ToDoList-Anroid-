package com.example.edwardcrispen.todolist;

import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by adena on 10/21/2016.
 */

public class List {
    ArrayList<Task> list = new ArrayList<>();
    String name;

    public List(){}

    public List(String name){
        this.name = name;
    }

    public void addTask(String task, EditText text, LinearLayout layout){
        if(task.equals("")) {
            layout.removeView(text);
        }else{
            list.add(new Task(task, false));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void addTask(String task){
        list.add(new Task(task, false));
    }

    public ArrayList<Task> toArrayList(){
        return list;
    }
}
