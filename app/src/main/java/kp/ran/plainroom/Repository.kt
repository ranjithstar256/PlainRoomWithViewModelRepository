package kp.ran.plainroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Repository(val stdao:StudentDao) {
    suspend fun insertUser(user: Students) {
        stdao.insert(user)
    }

    suspend fun getAllUsers() = stdao.getAllStudents()

    suspend fun updateUser(user: Students) {
        stdao.update(user)
    }

    suspend fun deleteUser(user: Students) {
        stdao.delete(user)
    }

    suspend fun getloc(loc : String):String{
        return stdao.getloc(loc)
    }
    suspend fun getId(id : String):Int{
        return stdao.getId(id)
    }
}

