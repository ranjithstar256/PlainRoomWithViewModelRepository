package kp.ran.plainroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Students(
 @PrimaryKey(autoGenerate = true)
 var id:Int,

 var name : String,

 var location : String,

 var age : Int,

 )
