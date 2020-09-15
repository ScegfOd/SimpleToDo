package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> todo_list;

    Button addbtn;
    EditText todotext;
    RecyclerView todolistview;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ItemsAdapter.OnLongClickListener olcl = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                todo_list.remove(position);
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "todo removed from list!", Toast.LENGTH_SHORT).show();
                save_data();
            }
        };

        load_data();

        addbtn = findViewById(R.id.btnadd);
        todotext = findViewById(R.id.newtodotxt);
        todolistview = findViewById(R.id.todolist);

        itemsAdapter = new ItemsAdapter(todo_list, olcl);
        todolistview.setAdapter(itemsAdapter);
        todolistview.setLayoutManager(new LinearLayoutManager(this));

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo_item = todotext.getText().toString();
                todo_list.add(todo_item);
                itemsAdapter.notifyItemInserted(todo_list.size() - 1);
                todotext.setText("");
                Toast.makeText(getApplicationContext(), "todo added to list!", Toast.LENGTH_SHORT).show();
                save_data();
            }
        });
    }

    private File get_data() {
        return new File(getFilesDir(), "todo.txt");
    }

    private void load_data() {
        try {
            this.todo_list = new ArrayList<>(FileUtils.readLines(get_data(), Charset.defaultCharset()));
        }catch (java.io.IOException e){
            Log.e("Main Activity", "Error loading todo list", e);

            this.todo_list = new ArrayList<>();
        }
    }

    private void save_data(){
        try {
            FileUtils.writeLines(get_data(),
                    this.todo_list);
        } catch (IOException e) {
            Log.e("Main Activity", "Error saving todo list", e);
        }
    }
}