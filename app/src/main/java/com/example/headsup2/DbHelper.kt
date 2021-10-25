package com.example.headsup2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,"celebritiesDetails.db",null,1) {
    val SQLiteDatabase:SQLiteDatabase=writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table celebrities(Name text,Taboo1 text,Taboo2 text,Taboo3 text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addCelebrity(n:String,t1:String,t2:String,t3:String): Long {
        val cv=ContentValues()
        cv.put("Name",n)
        cv.put("Taboo1",t1)
        cv.put("Taboo2",t2)
        cv.put("Taboo3",t3)
        val status=SQLiteDatabase.insert("celebrities",null,cv)
        return status
    }

    fun getCelebrity(): ArrayList<ArrayList<String>> {
        var celeInf=ArrayList<ArrayList<String>>()
        val c:Cursor=SQLiteDatabase.query("celebrities",null,null,null,null,null,null)
        while (c.moveToNext()){
            celeInf.add(arrayListOf(c.getString(c.getColumnIndex("Name")),
                c.getString(c.getColumnIndex("Taboo1")),
                c.getString(c.getColumnIndex("Taboo2")),
                c.getString(c.getColumnIndex("Taboo3"))))
        }
        return celeInf
    }
}