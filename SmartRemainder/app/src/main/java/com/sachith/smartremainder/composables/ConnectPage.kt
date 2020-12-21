package com.sachith.smartremainder.composables

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
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
import com.sachith.smartremainder.activity.ConnectActivity
import com.sachith.smartremainder.ui.SmartRemainderTheme
import com.sachith.smartremainder.ui.background
import com.sachith.smartremainder.ui.blue

@Preview(showBackground = true)
@Composable
fun loginPage() {
    val mobileState = remember { mutableStateOf(TextFieldValue()) }
    val otpState = remember { mutableStateOf(TextFieldValue()) }
    val context = AmbientContext.current

    SmartRemainderTheme(false) {
        Box(Modifier.fillMaxHeight().fillMaxWidth().background(background)){

            //Main Column
            Column(Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){

                //Space
                Spacer(Modifier.padding(24.dp))

                //Header text
                Text(text = "SMART REMAINDER", style = TextStyle(fontSize = 30.sp, color = Color.LightGray, fontWeight = FontWeight(800), textAlign = TextAlign.Center, fontFamily = FontFamily.Serif))

                //Space
                Spacer(Modifier.padding(24.dp))

                //Mobile number text field
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = mobileState.value,
                        onValueChange = { mobileState.value = it },
                        Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.None,false,KeyboardType.Phone),
                        textStyle = TextStyle(color = Color.LightGray),
                        label = { Text(text = "Mobile Number", style = TextStyle(color = Color.LightGray)) },
                        leadingIcon = {Icon(Icons.Filled.Phone)},
                        inactiveColor = Color.LightGray
                    )
                }

                //OTP text field
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    OutlinedTextField(
                        value = otpState.value,
                        onValueChange = { otpState.value = it },
                        Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(KeyboardCapitalization.None,false,KeyboardType.Phone),
                        textStyle = TextStyle(color = Color.LightGray),
                        label = { Text(text = "OTP", style = TextStyle(color = Color.LightGray)) },
                        leadingIcon = {Icon(Icons.Filled.Lock)},
                        inactiveColor = Color.LightGray
                    )
                }

                //Get OTP Button
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Button(
                        onClick = {ConnectActivity().getOTP(context, mobileState.value.text)},
                        Modifier.fillMaxWidth().height(45.dp),
                        colors = ButtonConstants.defaultButtonColors(backgroundColor = blue),
                    )
                    {
                        Text(text = "GET OTP", style = TextStyle(color = Color.White, fontWeight = FontWeight(600)))
                    }
                }

                //Connect Button
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Button(
                        onClick = { ConnectActivity().connectUser(context, otpState.value.text) },
                        Modifier.fillMaxWidth().height(45.dp),
                        colors = ButtonConstants.defaultButtonColors(backgroundColor = blue),
                    )
                    {
                        Text(text = "CONNECT", style = TextStyle(color = Color.White, fontWeight = FontWeight(600)))
                    }
                }
            }
        }
    }
}