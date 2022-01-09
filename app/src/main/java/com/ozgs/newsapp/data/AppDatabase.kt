package com.ozgs.newsapp.data

import android.content.Context
import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.*
import com.ozgs.newsapp.utilities.DATABASE_NAME
import com.ozgs.newsapp.utilities.DATAFILE_NAME
import com.ozgs.newsapp.workers.SeedDatabaseWorker
import com.ozgs.newsapp.workers.SeedDatabaseWorker.Companion.KEY_FILENAME

@Keep
@Database(entities = [News::class],version = 1,exportSchema = false)
@TypeConverters(NewsItemConverter::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun newsDao():NewsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context):AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java,DATABASE_NAME)
                .createFromAsset("newsdb")
//                .build()
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to DATAFILE_NAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                ).build()
        }
    }
}