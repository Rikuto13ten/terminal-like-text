package com.example.terminal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inttextfield.IntTextField
import kotlinx.coroutines.delay

@Composable
fun TextArea(
    modifier: Modifier = Modifier
) {
    val textState = remember { mutableStateOf("") }
    var text by remember { mutableStateOf("terminal like.") }
    var showText by remember { mutableStateOf(false) }
    var delayTime by remember { mutableLongStateOf(200) }
    var textSize by remember { mutableIntStateOf(40) }
    var temporaryTextSize by remember { mutableIntStateOf(40) }

    LaunchedEffect(key1 = showText) {
        text.forEach { char ->
            delay(delayTime)
            textState.value += char
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            )

            Button(
                modifier = Modifier
                    .fillMaxHeight()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    textState.value = ""
                    showText = !showText
                    textSize = temporaryTextSize
                }
            ) {
                Text(
                    text = "push",
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "秒数 ms",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.dotgothic16))
                )
                IntTextField(
                    modifier = Modifier.height(50.dp),
                    value = delayTime.toInt(),
                    onValueChange = {
                        delayTime = it.toLong()
                    }
                )
            }

            Column(
                modifier = Modifier.weight(0.5f)
            ) {
                Text(
                    text = "フォントサイズ",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.dotgothic16))
                )
                IntTextField(
                    modifier = Modifier.height(50.dp),
                    value = temporaryTextSize,
                    onValueChange = {
                        temporaryTextSize = it.toInt()
                    }
                )
            }
        }

        AnimatedVisibility(
            visibleState = MutableTransitionState(textState.value != "").apply {
                targetState = true
            },
            enter = expandHorizontally(),
            exit = shrinkHorizontally()
        ) {
            Text(
                text = textState.value,
                color = Color(0xDD26D62C),
                fontSize = textSize.sp,
                letterSpacing = 1.5.sp,
                lineHeight = (textSize * 1.1).sp,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color(0xFF09FE02),
                        blurRadius = 8f,
                        offset = Offset(0f, 0f)
                    )
                ),
                fontFamily = FontFamily(Font(R.font.dotgothic16))
            )
        }
    }

}