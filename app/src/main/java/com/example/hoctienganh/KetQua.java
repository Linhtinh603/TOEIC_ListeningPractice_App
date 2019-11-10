package com.example.hoctienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KetQua extends AppCompatActivity {
    TextView tvHienTai, tvTong;
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        tvHienTai = findViewById(R.id.tvKetQuaHienTai);
        tvTong = findViewById(R.id.tvKetQuaTong);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int questionLoaded = intent.getExtras().getInt("questionLoaded");
        int right = intent.getExtras().getInt("right");
        int totalQuestion = intent.getExtras().getInt("totalQuestion");
        int passedQuestion = intent.getExtras().getInt("passedQuestion");
        String tyleTraLoi = String.format("%.1f",(double)right/questionLoaded*100);
        String tyleToanBo = String.format("%.1f",(double)passedQuestion/totalQuestion*100);

        tvHienTai.setText("Bạn đã vừa trả lời đúng "+right+ "/" + questionLoaded + " bài nghe vừa làm" +
                "\n Tỷ lệ chính xác: "+tyleTraLoi+"%");

        tvTong.setText("Bạn đã hoàn thành "+passedQuestion+ " trên " + totalQuestion + " toàn bộ bài nghe"+
                "\n Tỷ lệ hoàn thành: "+ tyleToanBo+"%");
    }
}
