package iyannah.ivy.roomdb.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iyannah.ivy.roomdb.domain.Person
import iyannah.ivy.roomdb.presentation.PersonEvent

@Composable
fun PersonScreen(
    modifier: Modifier = Modifier,
    data: List<Person>,
    error: String? = null,
    onEvent: (PersonEvent) -> Unit
) {
    Column(modifier = modifier) {
        AddText(error = error, onEvent = onEvent)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(count = data.size) {
                ListItem(person = data[it], onEvent = onEvent)
            }
        }
    }
}

@Composable
fun AddText(error: String?, modifier: Modifier = Modifier, onEvent: (PersonEvent) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            supportingText = {
                Text(text = "* first name")
            }
        )
        TextField(
            value = secondName,
            onValueChange = { secondName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            supportingText = {
                Text(text = "* second name")
            }
        )
        TextField(
            value = telephone,
            onValueChange = { telephone = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            supportingText = {
                Text(text = "* telephone")
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (error != null) {
                Text(text = error, color = Color.Red)
            }
            TextButton(
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                border = BorderStroke(2.dp, Color.Black),
                onClick = {
                    onEvent(PersonEvent.OnSaveClicked(firstName, secondName, telephone))
                    firstName = ""
                    secondName = ""
                    telephone = ""
                }) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun ListItem(
    person: Person,
    onEvent: (PersonEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "${person.firstName} ${person.secondName}",
                modifier = modifier
                    .padding(8.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
            Text(
                text = person.telephone,
                modifier = modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier.clickable {
                onEvent(PersonEvent.OnRemove(person))
            })
    }

}