package xyz.teamgravity.neumorphicclickablecircle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import xyz.teamgravity.neumorphicclickablecircle.ui.theme.Gray100
import xyz.teamgravity.neumorphicclickablecircle.ui.theme.Gray200
import xyz.teamgravity.neumorphicclickablecircle.ui.theme.NeumorphicClickableCircleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeumorphicClickableCircleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Gray100)
                            .fillMaxSize()
                    ) {
                        var pressed by remember { mutableStateOf(false) }
                        val yOffset by animateIntAsState(
                            targetValue = if (pressed) 1 else 5,
                            label = "offset"
                        )
                        val blur by animateDpAsState(
                            targetValue = if (pressed) 4.dp else 10.dp,
                            label = "blur"
                        )

                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = -yOffset
                                    )
                                }
                                .blur(
                                    radius = blur,
                                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                                )
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                        )
                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = yOffset
                                    )
                                }
                                .blur(
                                    radius = blur,
                                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                                )
                                .background(
                                    color = Color.Black.copy(alpha = 0.2F),
                                    shape = CircleShape
                                )
                        )
                        Box(
                            modifier = Modifier
                                .size(160.dp)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Gray100,
                                            Gray200
                                        )
                                    ),
                                    shape = CircleShape
                                )
                                .border(
                                    width = 1.dp,
                                    shape = CircleShape,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.White,
                                            Color.Black.copy(alpha = 0.15F)
                                        )
                                    )
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            pressed = true
                                            tryAwaitRelease()
                                            pressed = false
                                        }
                                    )
                                }
                        )
                    }
                }
            }
        }
    }
}