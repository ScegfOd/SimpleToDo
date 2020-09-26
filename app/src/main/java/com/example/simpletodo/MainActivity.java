package com.example.simpletodo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POS = "item_pos";
    public static final int EDIT_TODO_TEXT_CODE = 1337;

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
        ItemsAdapter.OnClickListener ocl = new ItemsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra(KEY_ITEM_TEXT, todo_list.get(position));
                i.putExtra(KEY_ITEM_POS, position);
                startActivityForResult(i,EDIT_TODO_TEXT_CODE);
            }
        };

        load_data();

        addbtn = findViewById(R.id.btnadd);
        todotext = findViewById(R.id.newtodotxt);
        todolistview = findViewById(R.id.todolist);

        itemsAdapter = new ItemsAdapter(todo_list, olcl, ocl);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TODO_TEXT_CODE && resultCode == RESULT_OK) {
            String new_text = data.getStringExtra(MainActivity.KEY_ITEM_TEXT);
            int i = data.getExtras().getInt(MainActivity.KEY_ITEM_POS);
            todo_list.set(i, new_text);
            itemsAdapter.notifyItemChanged(i);
            save_data();
            Toast.makeText(getApplicationContext(), "todo updated!", Toast.LENGTH_SHORT).show();
        } else {
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
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