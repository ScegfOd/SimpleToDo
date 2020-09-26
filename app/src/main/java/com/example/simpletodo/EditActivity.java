package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText text_box;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        text_box = findViewById(R.id.edit_text_box);
        save_button = findViewById(R.id.save_button);

        getSupportActionBar().setTitle("Edit item");
        text_box.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));
        //getIntent.getintExtra(MainActivity.KEY_ITEM_POS);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra(MainActivity.KEY_ITEM_TEXT, text_box.getText().toString());
                i.putExtra(MainActivity.KEY_ITEM_POS, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POS));
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}