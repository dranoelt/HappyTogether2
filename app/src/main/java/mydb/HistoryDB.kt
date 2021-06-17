package mydb

import android.provider.BaseColumns

object historyDB {
    class historyTable:BaseColumns {
        companion object {
            val TABLE_HISTORY: String = "title"
            val COLUMN_ID: String = "_id"
            val COLUMN_TITLE: String = "title"
            val COLUMN_TGL: String = "tanggal"
            val COLUMN_DESC: String = "desc"
        }
    }
}