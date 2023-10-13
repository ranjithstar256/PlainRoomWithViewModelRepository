package kp.ran.plainroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun addStudent(students: Students)

    @Query("SELECT * from Students")
    suspend fun getAllStudents(): List<Students>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: Students)

    @Update
    suspend fun update(student: Students)

    @Delete
    suspend fun delete(student: Students)

    /*@Query("SELECT location FROM Students where name like :namex")
    suspend fun getloc(namex:String)
*/
    @Query("SELECT location FROM Students WHERE name LIKE :namex")
    suspend fun getloc(namex: String): String

    //@Query("select * from Students where name like name")
    // @Query("SELECT * FROM notes WHERE id=:noteId")
    //   @Query("SELECT * FROM task WHERE task LIKE :taskname ")

    @Query("SELECT id FROM Students where name = :itm")
    suspend fun getId(itm:String):Int



}