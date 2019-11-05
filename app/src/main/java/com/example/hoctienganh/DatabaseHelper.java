package com.example.hoctienganh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    ArrayList<Part1Object> listBaiNghe = new ArrayList<>();

    DatabaseHelper(Context context){
        super(context,"Database_HocTiengAnh",null,1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("Drop table if exists Part1");
        onCreate(sqLiteDatabase);
        deleteAll();
        insertData();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table Part1 ("+"id integer primary key autoincrement,"
                +" rightAnswer text,"
                +"answerAScript text,"
                +"answerBScript text,"
                +"answerCScript text,"
                +"answerDScript text,"
                +"pass integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Part1");
        onCreate(sqLiteDatabase);
    }

    public int insertPart1 (Part1Object part1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rightAnswer", String.valueOf(part1.getRightAnswer()));
        contentValues.put("answerAScript", part1.getAnswerAScript());
        contentValues.put("answerBScript", part1.getAnswerBScript());
        contentValues.put("answerCScript", part1.getAnswerCScript());
        contentValues.put("answerDScript", part1.getAnswerDScript());
        contentValues.put("pass", part1.isPass());
        int result = (int)db.insert("Part1",null,contentValues);
        return result;
    }

    public ArrayList<Part1Object> getListPart1(){
        ArrayList<Part1Object> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Part1",null);
        if(cursor!=null && cursor.moveToFirst() ){
            while (cursor.isAfterLast()==false){
                int id = cursor.getInt(0);
                String rightAnswer = cursor.getString(1);
                String answerAScript = cursor.getString(2);
                String answerBScript = cursor.getString(3);
                String answerCScript = cursor.getString(4);
                String answerDScript = cursor.getString(5);
                int pass = cursor.getInt(6);

                list.add(new Part1Object(id,rightAnswer,answerAScript,answerBScript,answerCScript,answerDScript,pass == 1));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return  list;
    }


    public ArrayList<Part1Object> getListNotPassPart1() {
        ArrayList<Part1Object> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Part1 WHERE pass = 0",null);
        if(cursor!=null && cursor.moveToFirst() ){
            while (cursor.isAfterLast()==false){
                int id = cursor.getInt(0);
                String rightAnswer = cursor.getString(1);
                String answerAScript = cursor.getString(2);
                String answerBScript = cursor.getString(3);
                String answerCScript = cursor.getString(4);
                String answerDScript = cursor.getString(5);
                int pass = cursor.getInt(6);

                list.add(new Part1Object(id,rightAnswer,answerAScript,answerBScript,answerCScript,answerDScript,pass == 1));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return  list;
    }

    public boolean setPassQuestion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pass", 1);
        int updated = db.update("Part1",contentValues,"id"+ "=" + id,null);
        if(updated > 0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Part1",null,null);
    }

    public void loadData(){

        Part1Object bai1 = new Part1Object(0,"A", "A","B","C","D",true);
        Part1Object bai2 = new Part1Object(0,"B", "A","B","C","D",false);
        Part1Object bai3 = new Part1Object(0,"C", "A","B","C","D",true);
        Part1Object bai4 = new Part1Object(0,"D", "A","B","C","D",false);


        listBaiNghe.add(bai1);
        listBaiNghe.add(bai2);
        listBaiNghe.add(bai3);
        listBaiNghe.add(bai4);
    }

    public void insertData(){
        loadData();
        for(Part1Object part1 : listBaiNghe){
            insertPart1(part1);
        }
    }

}
