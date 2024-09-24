import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    fontSize: Int,
    color: Color = Color.White,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        color = color,
        modifier = modifier,
        textAlign = textAlign
    )
}
