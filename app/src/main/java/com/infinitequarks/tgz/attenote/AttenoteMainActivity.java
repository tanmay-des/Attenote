package com.infinitequarks.tgz.attenote;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AttenoteMainActivity extends AppCompatActivity {

    private Button mAddSubButton;

    private Button mSetTimeTableButton;
    private List<Subject> mSubjects = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attenote_main);

        mAddSubButton = (Button) findViewById(R.id.add_subject);

        mAddSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttenoteMainActivity.this , AddSubjectActivity.class);
                startActivityForResult(intent , 0);
            }
        });
    }


}
