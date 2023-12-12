package com.isticpla.itp.uimodules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isticpla.itp.R
import com.isticpla.itp.poppinFamily

private val titleStyle = TextStyle(
    fontFamily = poppinFamily,
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)
private val switchLabelStyle = TextStyle(
    fontFamily = poppinFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.SemiBold
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppGallery(
    showModalSheetState: MutableState<Boolean> = mutableStateOf(false)
) {
    var prvtSwitchCheckState by remember { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState()

    if (showModalSheetState.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showModalSheetState.value = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Görsel İçerik",
                    style = titleStyle
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_camera_alt_24),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_videocam_24),
                            contentDescription = null
                        )
                    }
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_photo_library_24),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier=Modifier
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End)
                    ) {
                        Switch(
                            checked = prvtSwitchCheckState,
                            onCheckedChange = { prvtSwitchCheckState = !prvtSwitchCheckState }
                        )
                        Text(text = "Özel", style = switchLabelStyle)
                    }
                }
            }
        }
    }
}