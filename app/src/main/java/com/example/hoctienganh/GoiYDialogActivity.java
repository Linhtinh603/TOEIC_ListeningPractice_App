package com.example.hoctienganh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GoiYDialogActivity<OnMyDialogResult> extends Dialog {
    TextView tvA, tvB, tvC, tvD;
    OnMyDialogResult onMyDialogResult;

    public GoiYDialogActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_y_dialog);
        tvA = findViewById(R.id.tvA);
        tvA.setText("hihi");

    }
}
