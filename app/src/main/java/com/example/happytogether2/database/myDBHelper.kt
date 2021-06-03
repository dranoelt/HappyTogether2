package com.example.happytogether2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " + historyDB.historyTable.TABLE_HISTORY + "(" +
                historyDB.historyTable.COLUMN_ID + "INTEGER PRIMARY KEY," +
                historyDB.historyTable.COLUMN_TITLE + " TEXT," +
                historyDB.historyTable.COLUMN_TGL + " TEXT," +
                historyDB.historyTable.COLUMN_DESC + " TEXT" + ")")
        db?.execSQL(CREATE_HISTORY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " +
        historyDB.historyTable.TABLE_HISTORY)
        onCreate(db)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mypreload.db"
    }
}