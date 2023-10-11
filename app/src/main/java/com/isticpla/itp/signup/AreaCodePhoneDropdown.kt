package com.isticpla.itp.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.dummydata.listofAraeCodes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AreaCodePhoneDropdown(dropdownState: MutableState<Boolean>) {
    return Card(
        modifier = Modifier
            .width(66.dp),
        colors = CardDefaults.cardColors(contentColor = Color.Transparent),
        shape = RoundedCornerShape(6.dp),
        onClick = { dropdownState.value = true }) {
        ListItem(
            headlineContent = {Text(listofAraeCodes.first().first + " " + listofAraeCodes.first().second,style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium))},
            supportingContent = {
                Text(listofAraeCodes.first().first + " " + listofAraeCodes.first().second,style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium))
            },
            trailingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_more_24),
                    contentDescription = null
                )
            }
        )
    }
    DropdownMenu(
        //modifier = Modifier.width(265.dp),
        expanded = dropdownState.value,
        onDismissRequest = { dropdownState.value = false }) {
        listofAraeCodes.forEach { area ->
            DropdownMenuItem(
                text = { Text(area.first + " " + area.second) },
                onClick = { dropdownState.value = false })
        }
    }
}