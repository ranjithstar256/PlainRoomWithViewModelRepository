package kp.ran.plainroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(private val repository: Repository) : ViewModel() {

    // Function to insert a student
    fun insertStudent(student: Students) {
        viewModelScope.launch {
            repository.insertUser(student)
        }
    }

    // Function to get all students as a Flow
    suspend fun getAllStudents(): List<Students> {
        return repository.getAllUsers()
    }

    // Function to update a student
    fun updateStudent(student: Students) {
        viewModelScope.launch {
            repository.updateUser(student)
        }
    }

    // Function to delete a student
    fun deleteStudent(student: Students) {
        viewModelScope.launch {
            repository.deleteUser(student)
        }
    }


    suspend fun getLoc(loc: String): String {
        return withContext(Dispatchers.IO) {
            repository.getloc(loc)
        }
    }

    suspend fun getId(id: String): Int {
        return withContext(Dispatchers.IO) {
            repository.getId(id)
        }
    }
}