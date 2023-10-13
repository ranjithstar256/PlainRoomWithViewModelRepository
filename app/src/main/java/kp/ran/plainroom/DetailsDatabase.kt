package kp.ran.plainroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[Students::class], version = 1)
abstract class DetailsDatabase : RoomDatabase() {
    abstract val studentDao: StudentDao
}