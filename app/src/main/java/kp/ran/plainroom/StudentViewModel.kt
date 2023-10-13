package kp.ran.plainroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
 import kotlinx.coroutines.launch

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
}