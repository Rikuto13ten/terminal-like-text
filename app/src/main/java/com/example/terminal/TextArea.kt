package com.example.terminal

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
    var text by remember { mutableStateOf("Perfect.") }
    var showText by remember { mutableStateOf(false) }
    var delayTime by remember { mutableLongStateOf(20) }
    var textSize by remember { mutableIntStateOf(14) }
    var temporaryTextSize by remember { mutableIntStateOf(14) }

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

        Text(
            text = "テキスト",
            color = Color.White
        )
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "秒数 ms",
            color = Color.White
        )
        IntTextField(
            value = delayTime.toInt(),
            onValueChange = {
                delayTime = it.toLong()
            }
        )

        Text(
            text = "フォントサイズ",
            color = Color.White
        )
        IntTextField(
            value = temporaryTextSize,
            onValueChange = {
                temporaryTextSize = it
            }
        )

        Button(onClick = {
            textState.value = ""
            showText = !showText
            textSize = temporaryTextSize
        }) {
            Text(
                text = "push",
            )
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