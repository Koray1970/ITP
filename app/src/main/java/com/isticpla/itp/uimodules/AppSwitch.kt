package com.isticpla.itp.uimodules

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.isticpla.itp.R

@Composable
fun AppSwitch(
    cChecked: MutableState<Boolean>
) = Switch(
    checked = cChecked.value,
    onCheckedChange = { cChecked.value = it },
    colors = SwitchDefaults.colors(
        checkedBorderColor = Color.Transparent,
        uncheckedBorderColor = Color.Transparent,
        checkedTrackColor = AppColors.green_103,
        uncheckedTrackColor = AppColors.grey_133,
        checkedThumbColor = Color.White,
        uncheckedThumbColor = AppColors.grey_135,
        uncheckedIconColor = Color.White,
        checkedIconColor = Color.Black
    ),
    thumbContent = if (cChecked.value) {
        {
            Icon(
                painter = painterResource(id = R.drawable.round_check_24),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }
)