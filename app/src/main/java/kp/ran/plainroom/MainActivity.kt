package kp.ran.plainroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kp.ran.plainroom.ui.theme.PlainRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(applicationContext,DetailsDatabase::class.java,
            "detailsdb.db").build()

        /*
                (1..10).forEach{
                         lifecycleScope.launch {
                            db.studentDao.addStudent(Students(0,"name $it","location $it",(it+5)))
                        }
                }*/
        setContent {
            PlainRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val repository= Repository(db.studentDao)

                    val viewModel = StudentViewModel(repository)

                   Greeting(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun Greeting(viewModel: StudentViewModel) {
    val scope = rememberCoroutineScope()
    var name by remember {
        mutableStateOf("")
    }
    var bc by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxWidth()) {

        OutlinedTextField(value = name, onValueChange = { name = it },
            placeholder = { Text("Name", color = Color.Gray)})
        OutlinedTextField(value = bc, onValueChange = { bc = it },
            placeholder = { Text("Location", color = Color.Gray) })
        OutlinedTextField(value = age, onValueChange = {
            age = it
        },
            placeholder = {  Text("Age (Number only) ", color = Color.Gray) })
        var er by remember {
            mutableStateOf("")
        }
        var isTextVisible by remember { mutableStateOf(false) }

        Button(onClick = {


            if(age.toDoubleOrNull() != null){
                scope.launch {
                    var agein: Int = age.toInt()

                    viewModel.insertStudent(Students(0, name, bc, 200))
                    //db.studentDao.addStudent(Students(0, name, bc, agein))
                }
                er=""
                isTextVisible = false
            }
            else{
                er="Age must be a number"
                isTextVisible=true
            }
        }) {
            Text(text = "Save")
        }

        if (isTextVisible) {
            Text(er)
        }

        var getallbool by remember {
            mutableStateOf(false)
        }
        var getlocbool by remember {
            mutableStateOf(false)
        }
        var delbool by remember {
            mutableStateOf(false)
        }

        if (getlocbool) {
            val showcustomrows by remember {
                derivedStateOf {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                           viewModel.getLoc(name)

                        }
                    }
                }
            }
            Text(text = "$showcustomrows")
        }


        Button(
            onClick = {
                getlocbool = !getlocbool

            }) {
            Text(text = "get loc")
        }

        Button(
            onClick = {
                getallbool = !getallbool
                scope.launch {
                      //alldata=  db.studentDao.getAllStudents()
                    //alldata = viewModel.getAllStudents()
                }
            }) {
            Text(text = "Retrive all")
        }
        Button(onClick = {
            scope.launch {


                // var idint : Int = name.toInt()
                val id = viewModel.getId(name)

                viewModel.deleteStudent(Students(
                    id = id,
                    name = name,
                    age = id,
                    location = ""
                ))

            }
        }) {
            Text(text = "Delete user")
        }
        if (getallbool) {
            val showcustomrows by remember {
                derivedStateOf {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            //db.studentDao.getAllStudents()
                            viewModel.getAllStudents()
                        }
                    }
                }
            }
            showcustomrows.forEach {
                Column() {
                    Text(text = "${it.name} - ${it.location}")

                }
            }
        }

        if (getallbool) {
            val showcustomrows by remember {
                derivedStateOf {
                    runBlocking {
                        withContext(Dispatchers.IO) {
                        //    db.studentDao.getAllStudents()
                            viewModel.getAllStudents()
                        }
                    }
                }
            }


            showcustomrows.forEach {
                Column() {
                    Text(text = "${it.name} - ${it.location}")
                }
            }
        }


    }
}