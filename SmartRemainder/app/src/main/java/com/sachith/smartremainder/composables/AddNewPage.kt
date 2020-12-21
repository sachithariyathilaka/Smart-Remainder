package com.sachith.smartremainder.composables

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachith.smartremainder.activity.AddNewActivity
import com.sachith.smartremainder.model.Remainder
import com.sachith.smartremainder.ui.SmartRemainderTheme
import com.sachith.smartremainder.ui.background
import com.sachith.smartremainder.ui.blue

@Preview(showBackground = true)
@Composable
fun AddNewPage() {
    val context = AmbientContext.current
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val dateState = remember { mutableStateOf(TextFieldValue()) }
    val timeState = remember { mutableStateOf(TextFieldValue()) }

    SmartRemainderTheme(false) {
        Box(Modifier.fillMaxHeight().fillMaxWidth().background(background)){

            //Main Column
            Column(Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){

                //Space
                Spacer(Modifier.padding(8.dp))

                //Toolbar
                TopAppBar(
                    title = { Text(text = "ADD NEW REMAINDER", style = TextStyle(fontSize = 20.sp, color = Color.LightGray, fontWeight = FontWeight(800), textAlign = TextAlign.Center, fontFamily = FontFamily.Serif)) },
                    navigationIcon = { IconButton(onClick = { AddNewActivity().goBack(context)}) { Icon(Icons.Filled.ArrowBack, tint = Color.LightGray) } },
                    backgroundColor = Color.Transparent, elevation = 0.dp
                )

                //Title text field
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = titleState.value,
                        onValueChange = { titleState.value = it },
                        Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.None,false,
                            KeyboardType.Text),
                        textStyle = TextStyle(color = Color.LightGray),
                        label = { Text(text = "Title", style = TextStyle(color = Color.LightGray)) },
                        leadingIcon = {Icon(Icons.Filled.Home)},
                        inactiveColor = Color.LightGray
                    )
                }

                //Date text field
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = dateState.value,
                        onValueChange = { dateState.value = it },
                        Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.None,false,KeyboardType.Number),
                        textStyle = TextStyle(color = Color.LightGray),
                        label = { Text(text = "Date", style = TextStyle(color = Color.LightGray)) },
                        leadingIcon = {Icon(Icons.Filled.DateRange)},
                        inactiveColor = Color.LightGray
                    )
                }

                //Time text field
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = timeState.value,
                        onValueChange = { timeState.value = it },
                        Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            KeyboardCapitalization.None,false,
                            KeyboardType.Number),
                        textStyle = TextStyle(color = Color.LightGray),
                        label = { Text(text = "Time", style = TextStyle(color = Color.LightGray)) },
                        leadingIcon = {Icon(Icons.Filled.LocationOn)},
                        inactiveColor = Color.LightGray
                    )
                }

                //Submit Button
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Button(
                        onClick = { AddNewActivity().insertRemainder(context, Remainder(titleState.value.text, dateState.value.text, timeState.value.text))},
                        Modifier.fillMaxWidth().height(45.dp),
                        colors = ButtonConstants.defaultButtonColors(backgroundColor = blue),
                    )
                    {
                        Text(text = "SUBMIT", style = TextStyle(color = Color.White, fontWeight = FontWeight(600)))
                    }
                }
            }
        }
    }
}