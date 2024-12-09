package iyannah.ivy.roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import iyannah.ivy.roomdb.domain.Person
import iyannah.ivy.roomdb.presentation.RoomDbSampleViewModel
import iyannah.ivy.roomdb.presentation.screen.PersonScreen
import iyannah.ivy.roomdb.ui.theme.RoomDbSampleTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDbSampleTheme {
                val viewModel: RoomDbSampleViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PersonScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(20.dp),
                        data = state.personList,
                        onEvent = viewModel::onEvent,
                        error = state.isNameError
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDbSampleTheme {
        PersonScreen(
            data = listOf(
                Person(
                    firstName = "John",
                    secondName = "Doe",
                    telephone = "1234567890"
                )
            ), onEvent = {})
    }
}