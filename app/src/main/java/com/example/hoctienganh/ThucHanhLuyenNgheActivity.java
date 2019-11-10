package com.example.hoctienganh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ThucHanhLuyenNgheActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    Button btnPause, btnStop, btnGoiY, btnKetThuc, btnA, btnB, btnC, btnD;
    TextView tvBoDe, tvCau;
    ImageView img;
    SeekBar seekBar;
    int totalQuestion = 0;
    int position = 0;
    int questionLoaded = 0;
    int rightAnswer = 0;
    private ArrayList<Integer> questions;
    private ArrayList<Part1Object> listBaiNghe;
    MediaPlayer fileNghe;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_hanh_luyen_nghe);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnGoiY = findViewById(R.id.btnGoiY);
        btnKetThuc = findViewById(R.id.btnKetThuc);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        img = findViewById(R.id.imageView);
        tvBoDe = findViewById(R.id.tvBoDe);
        tvCau = findViewById(R.id.tvCau);
        seekBar = findViewById(R.id.seekBar);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(ThucHanhLuyenNgheActivity.this);
        loadAllData();
        totalQuestion = listBaiNghe.size();
        fileNghe.start();
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fileNghe.isPlaying()){
                    fileNghe.pause();
                    btnPause.setBackgroundResource(R.drawable.play);
                }else{
                    fileNghe.start();
                    btnPause.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNghe.stop();
                KhoiTaoFileNghe();
                btnPause.setBackgroundResource(R.drawable.play);
            }
        });
        btnGoiY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFinishClick();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b){
                        fileNghe.seekTo(i);
                    }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnA:{
                checkAnswer("A");
                onAnswerClick();
                break;
            }
            case R.id.btnB:{
                checkAnswer("B");
                onAnswerClick();
                break;
            }
            case R.id.btnC:{
                checkAnswer("C");
                onAnswerClick();
                break;
            }
            case R.id.btnD:{
                checkAnswer("D");
                onAnswerClick();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.all:
                if(fileNghe.isPlaying()){
                    fileNghe.stop();
                    fileNghe.release();
                }
                loadAllData();
                fileNghe.start();
                return true;
            case R.id.notpass:
                if(fileNghe.isPlaying()){
                    fileNghe.stop();
                    fileNghe.release();
                }
                loadNotPassData();
                fileNghe.start();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
         final AlertDialog dialog = new AlertDialog.Builder(this)
         .setTitle("Đoạn hội thoại tiếng Anh")
         .setMessage("A: " + listBaiNghe.get(position).getAnswerAScript() +
                 "\nB: " + listBaiNghe.get(position).getAnswerBScript() +
                 "\nC: " + listBaiNghe.get(position).getAnswerCScript() +
                 "\nD: " + listBaiNghe.get(position).getAnswerDScript()
         )
         .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {

             }
         }).setNegativeButton("Nghe lại", null ).create();

         dialog.setOnShowListener(new DialogInterface.OnShowListener() {
             @Override
             public void onShow(DialogInterface dialogInterface) {
                 Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                 button.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         fileNghe.stop();
                         KhoiTaoFileNghe();
                         fileNghe.start();
                     }
                 });
             }
         });
         dialog.show();
    }

    private void KhoiTaoFileNghe(){
        if(position > listBaiNghe.size() - 1){
            position = 0;
        }
        if(listBaiNghe.size() == 0){
            Toast.makeText(getApplicationContext(),"Bạn đã hoàn thành hết tất cả các câu!", Toast.LENGTH_SHORT).show();

        }else{
            int index = listBaiNghe.get(position).getId();
            String id = String.valueOf("f"+index);
            int audio = this.getResources().getIdentifier(id, "raw", getPackageName());
            fileNghe = MediaPlayer.create(this, audio);

            String idh = String.valueOf("h"+index);
            int image = this.getResources().getIdentifier(idh, "drawable", getPackageName());
            img.setImageResource(image);

            btnPause.setBackgroundResource(R.drawable.pause);
            questionLoaded++;
            int bode = (index/6)+1;
            int cau = index%6;
            tvBoDe.setText("Bộ đề số: "+bode );
            tvCau.setText("Câu số: "+cau+"/6");
            seekBar.setProgress(0);
            seekBar.setMax(fileNghe.getDuration());
            new Thread(this).start();
        }
    }

    private void loadAllData(){
        listBaiNghe = databaseHelper.getListPart1();
        Log.v("==============LIST============",listBaiNghe.toString());
        KhoiTaoFileNghe();
    }
    private void loadNotPassData(){
        listBaiNghe = databaseHelper.getListNotPassPart1();
        KhoiTaoFileNghe();
        Log.v("==============LIST============",listBaiNghe.toString());
    }

    private void onAnswerClick(){

        if(fileNghe.isPlaying()){
            fileNghe.stop();
            fileNghe.release();
            btnPause.setBackgroundResource(R.drawable.pause);
        }
        position++;
        KhoiTaoFileNghe();
        fileNghe.start();
    }

    public void checkAnswer(String key){
        if(listBaiNghe.get(position).getRightAnswer().equalsIgnoreCase(key)){
            rightAnswer++;
            if(databaseHelper.setPassQuestion(listBaiNghe.get(position).getId())){
                Toast.makeText(getApplicationContext(),"Chính xác", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onFinishClick(){
        Intent intent = new Intent(ThucHanhLuyenNgheActivity.this,KetQua.class);
        intent.putExtra("right", rightAnswer );
        intent.putExtra("questionLoaded", questionLoaded );
        intent.putExtra("totalQuestion", totalQuestion );
        int passedQuestion = databaseHelper.getPassPart1();
        intent.putExtra("passedQuestion", passedQuestion );
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        fileNghe.stop();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fileNghe.stop();
        finish();
    }

    @Override
    public void run() {
        int currentPosition = fileNghe.getCurrentPosition();
        int total = fileNghe.getDuration();


        while (fileNghe != null && fileNghe.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(10);
                currentPosition = fileNghe.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }
}
