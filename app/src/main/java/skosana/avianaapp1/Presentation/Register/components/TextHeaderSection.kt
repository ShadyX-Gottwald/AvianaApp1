package skosana.avianaapp1.Presentation.Register.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.ui.theme.Brown
import skosana.avianaapp1.ui.theme.WhiteNew
import skosana.avianaapp1.ui.theme.WhiteishBg

@Composable
fun TextHeaderSection() {

    Box(

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(1F)
                .background(
                    color = WhiteishBg,
                    shape = RoundedCornerShape(12)
                )
                .padding(start = 10.dp)

        ) {
            Text(text = "Let's"
                , fontWeight = FontWeight.Bold ,
                color = Brown ,
                textAlign = TextAlign.Center ,
                fontSize = 30.sp,

            )

            Spacer(modifier = Modifier.padding(all = 5.dp))

            Text(text = "Create " , fontWeight = FontWeight.ExtraBold ,
                color = Brown ,
                textAlign = TextAlign.Center ,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(all = 5.dp))

            Text(text = "Your " , fontWeight = FontWeight.ExtraBold ,
                color = Brown ,
                textAlign = TextAlign.Center ,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(all = 5.dp))
            Text(text = "Account" , fontWeight = FontWeight.ExtraBold ,
                color = Brown ,
                textAlign = TextAlign.Center ,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(all = 26.dp))
        }
    }
}