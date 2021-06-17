package com.example.happytogether2.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import mydb.historyDB

class HistoryTransaction(context: Context) {
    private val myDBHelper = myDBHelper(context)

    private val dbwrite = myDBHelper.writableDatabase

    fun viewAllHistory() : List<History> {
        val meetList:ArrayList<History> = ArrayList<History> ()
        val selectQuery = "SELECT ${historyDB.historyTable.COLUMN_TITLE}" +
                ",${historyDB.historyTable.COLUMN_TGL}" +
                ",${historyDB.historyTable.COLUMN_DESC}" +
                " FROM ${historyDB.historyTable.TABLE_HISTORY}"
        val db = myDBHelper.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var meetTitle: String
        var meetTgl: String
        var meetDesc: String
        if (cursor.moveToFirst()) {
            do {
                meetTitle = cursor.getString(cursor.getColumnIndex(historyDB.historyTable.COLUMN_TITLE))
                meetTgl = cursor.getString(cursor.getColumnIndex(historyDB.historyTable.COLUMN_TGL))
                meetDesc = cursor.getString(cursor.getColumnIndex(historyDB.historyTable.COLUMN_DESC))
                meetList.add(History(meetTitle, meetTgl, meetDesc))
            }while (cursor.moveToNext())
        }
        return meetList
    }

    fun addHistory(meeting: History): Long {
        val db = myDBHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(historyDB.historyTable.COLUMN_TITLE, meeting.title)
        contentValues.put(historyDB.historyTable.COLUMN_TGL, meeting.tgl)
        contentValues.put(historyDB.historyTable.COLUMN_DESC, meeting.desc)
        val success = db.insert(historyDB.historyTable.TABLE_HISTORY, null, contentValues)
        db.close()
        return success
    }
    fun beginHistoryTransaction() {
        dbwrite.beginTransaction()
    }
    fun successHistoryTransaction() {
        dbwrite.setTransactionSuccessful()
    }
    fun endHistoryTransaction() {
        dbwrite.endTransaction()
    }
    fun addHistoryTransaction(meeting: History): Unit {
        val sqlString = "INSERT INTO ${historyDB.historyTable.TABLE_HISTORY} " +
                "(${historyDB.historyTable.COLUMN_TITLE}" +
                ",${historyDB.historyTable.COLUMN_TGL}" +
                ",${historyDB.historyTable.COLUMN_DESC}) VALUES (?,?,?)"
        val myStatement = dbwrite.compileStatement(sqlString)
        myStatement.bindString(1, meeting.title)
        myStatement.bindString(2, meeting.tgl)
        myStatement.bindString(3, meeting.desc)
        myStatement.execute()
        myStatement.clearBindings()
    }

    fun deleteAll() :Int {
        val db = myDBHelper.writableDatabase
        val success = db.delete(historyDB.historyTable.TABLE_HISTORY, "",null)
        db.close()
        return success
    }
}