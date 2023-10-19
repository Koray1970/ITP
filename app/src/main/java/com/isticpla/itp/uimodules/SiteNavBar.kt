package com.isticpla.itp.uimodules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.isticpla.itp.R

@Composable
fun NavBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 120.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.nav_bg_b),
            contentDescription = "Bg",
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .offset(x = 0.dp,
                    y = 0.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 106.dp))
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 303.dp,
                    y = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 40.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 40.dp)
                        .background(color = Color.White))
                Icon(
                    painter = painterResource(id = R.drawable.nav_home_a),
                    contentDescription = "Icon/Nav/Profile/Inactive",
                    tint = Color(0xffc1c1c1),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp))
            }
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 239.dp,
                    y = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 40.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 40.dp)
                        .background(color = Color.White))
                Icon(
                    painter = painterResource(id = R.drawable.nav_bookmark_i),
                    contentDescription = "Icon/Nav/Notification/Inactive",
                    tint = Color(0xffc1c1c1),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp))
            }
        }
        StateCreate(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 164.dp,
                    y = 0.dp))
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 96.dp,
                    y = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 40.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 40.dp)
                        .background(color = Color.White))
                Icon(
                    painter = painterResource(id = R.drawable.nav_notification_i),
                    contentDescription = "Icon/Nav/Bookmark/Inactive",
                    tint = Color(0xffc1c1c1),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp))
            }
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 32.dp,
                    y = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 40.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 40.dp)
                        .background(color = Color.White))
                Icon(
                    painter = painterResource(id = R.drawable.nav_profile_i),
                    contentDescription = "Icon/Nav/Home/Active",
                    tint = Color(0xffe23e3e),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 8.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape))
    }
}

@Composable
fun StateCreate(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredSize(size = 48.dp)
    ) {
        Box(
            modifier = Modifier
                .requiredSize(size = 40.dp)
                .background(color = Color.White))
        Image(
            painter = painterResource(id = R.drawable.nav_bg_b),
            contentDescription = "Bg",
            modifier = Modifier
                .fillMaxSize())
        Icon(
            painter = painterResource(id = R.drawable.nav_create),
            contentDescription = "Icon/General/Plus",
            tint = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 13.5.dp))
    }
}

