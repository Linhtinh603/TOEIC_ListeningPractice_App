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
//        sqLiteDatabase.execSQL("Drop table if exists Part1");
//        onCreate(sqLiteDatabase);
//        deleteAll();
//        insertData();
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

    public int getPassPart1(){
        int count = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM Part1 WHERE pass = 1",null);
        if(cursor!=null && cursor.moveToFirst() ){
            count = cursor.getInt(0);
        }
        return count;
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

        listBaiNghe.add(new Part1Object(0,
                "A",
                "One of the men is writing on a document",
                "One of the men is checking his watch",
                "One of the men is looking in a drawer",
                "One of the men is passing out pens from a box",
                false));

        listBaiNghe.add(new Part1Object(0,
                "A",
                "They're hanging a picture on a wall",
                "They're setting the table",
                "They're opening the window ",
                " They're rearranging some furniture",
                false));
        listBaiNghe.add(new Part1Object(0,
                "D",
                "She's putting away a microscopy",
                "She's taking off a coat",
                "She's examining some safety glasses",
                "She's using some laboratory equipment",
                false));
        listBaiNghe.add(new Part1Object(0,
                "C",
                "A man is pushing a shopping cart",
                "A man is waiting to make a purchase",
                "A man is holding some merchandise",
                "A man is assembling some shelves",
                false));
        listBaiNghe.add(new Part1Object(0,
                "B",
                "Some customer are leaving a shop ",
                "A seating area is decorated with plants ",
                "A worker is repairing some light fixtures",
                "A bench is being moved into a corner",
                false));

        listBaiNghe.add(new Part1Object(0,
                "D",
                "Some suitcases are being loaded onto a bus",
                "Some buses are parked in a garage",
                "Some buses are parked in garage",
                "Some people are lined up at the side of a road",
                false));
        listBaiNghe.add(new Part1Object(0,
                "D",
                "Some suitcases are being loaded onto a bus",
                "Some buses are parked in a garage",
                "Some buses are parked in garage",
                "Some people are lined up at the side of a road",
                false));
        listBaiNghe.add(new Part1Object(0,
                "D",
                "He's picking up a bag",
                "He's cycling on a road ",
                "He's climbing some a rocks",
                "He's wearing a jacket",
                false));
        listBaiNghe.add(new Part1Object(0,
                "A",
                "They're seated in awaiting area",
                "One's  of the women is moving a chair ",
                "One's  of the women is watering a plant",
                "The're plancing books on a table",
                false));
        listBaiNghe.add(new Part1Object(0,
                "C",
                "The man is pushing a shopping cart",
                "A man is paying  for some groceries ",
                "Some merchandise is arranged on shelves",
                "Some baskets are lined up on the floor",
                false));

        listBaiNghe.add(new Part1Object(0,
                "A",
                "A wonman's working at a laptop computer",
                "A wonman's drinking some bottle",
                "A woman's stacking some furniture",
                "A wonman's putting iteams in  a backpack",
                false));

        listBaiNghe.add(new Part1Object(0,
                "B",
                "A stage has been set up indoors",
                "Some people are watching a performance",
                "People are waiting in line for tickets",
                "A concert hall  is unoccupied",
                false));
        listBaiNghe.add(new Part1Object(0,
                "C",
                "Some pedestrians are cross at an intersection",
                "Tree branches are being cleared off a walk a walkway",
                "Some vehicles are facing a low wall",
                "A car is exiting a parking garage",
                false));
        listBaiNghe.add(new Part1Object(0,
                "B",
                "A woman is making a pot of coffee",
                "A woman is wearing s pair",
                "A woman is stacking dishes",
                "A woman is carrying some trays",
                false));
    }

    public void insertData(){
        loadData();
        for(Part1Object part1 : listBaiNghe){
            insertPart1(part1);
        }
    }

}
