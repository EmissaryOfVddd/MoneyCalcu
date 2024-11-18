package ru.acceptthevoid.moneycalculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.acceptthevoid.moneycalculator.models.RecurringTransaction

@Database(entities = [RecurringTransaction::class], version = 1)
abstract class MoneyDatabase : RoomDatabase() {
    abstract fun recTransactionDao(): RecTransactionDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyDatabase? = null

        fun getDatabase(context: Context): MoneyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

