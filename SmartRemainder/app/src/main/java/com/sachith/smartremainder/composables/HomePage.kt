package com.sachith.smartremainder.composables

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sachith.smartremainder.activity.HomeActivity
import com.sachith.smartremainder.model.Remainder
import com.sachith.smartremainder.model.RemainderListModel
import com.sachith.smartremainder.ui.SmartRemainderTheme
import com.sachith.smartremainder.ui.background

@Preview(showBackground = true)
@Composable
fun HomePage() {
    val context = AmbientContext.current
    val remainderListModel = RemainderListModel(context)
    SmartRemainderTheme(false) {
        Box(Modifier.fillMaxHeight().fillMaxWidth().background(background)){

            //Main Column
            Column(Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){

                //Space
                Spacer(Modifier.padding(8.dp))

                //Toolbar
                TopAppBar(title = {
                            Text(text = "SMART REMAINDER", style = TextStyle(fontSize = 20.sp, color = Color.LightGray, fontWeight = FontWeight(800), textAlign = TextAlign.Center, fontFamily = FontFamily.Serif))
                        }, actions = {
                            IconButton(onClick = { HomeActivity().goToAddNewPage(context) }) {
                                Icon(Icons.Filled.AddCircle, tint = Color.LightGray)
                            }
                        }, backgroundColor = Color.Transparent, elevation = 0.dp)

                //Remainder list
                LazyColumn{
                    itemsIndexed(items = remainderListModel.newSearch(context)){ index, remainder ->
                        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), ){
                            Text(text = remainder.date+" "+remainder.time, Modifier.fillMaxWidth(0.85f).wrapContentWidth(Alignment.Start),style = TextStyle(fontSize = 16.sp, color = Color.LightGray, fontWeight = FontWeight(200), textAlign = TextAlign.Center, fontFamily = FontFamily.Serif))
                            Text(text = remainder.title, Modifier.fillMaxWidth(0.85f).wrapContentWidth(Alignment.End),style = TextStyle(fontSize = 18.sp, color = Color.LightGray, fontWeight = FontWeight(800), textAlign = TextAlign.Center, fontFamily = FontFamily.Serif))
                        }

                    }
                }
            }
        }
    }
}