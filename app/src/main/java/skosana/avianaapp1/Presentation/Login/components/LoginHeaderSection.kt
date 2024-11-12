package skosana.avianaapp1.Presentation.Login.components

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.R
import skosana.avianaapp1.ui.theme.WhiteishBg

@Composable
fun LoginHeaderSection(
    context : Context,
    viewModel: LoginRegisterViewModel,
    modifier: Modifier
) {


      Box (
          modifier
              .height(315.dp)
              .padding(start = 25.dp, end = 25.dp, top = 40.dp),
            contentAlignment = Alignment.Center,

         // modifier.background(color = WhiteishBg)
      ){

        Column (
            modifier
                .fillMaxSize(1f)
                .padding(start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            //contentAlignment = Alignment.Center,
        ){

            Image(
                painter = painterResource(id = R.drawable.avianalogo),
                contentDescription = "Aviana Logo",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
                    , contentScale = ContentScale.FillWidth

            )
            Text(text = "Aviana" , fontWeight = FontWeight.ExtraBold ,
                color = WhiteishBg ,
                textAlign = TextAlign.Center ,
                fontSize = 40.sp
            )
            Text(text = "BIRD WATCHING" , fontWeight = FontWeight.Medium ,
                color = WhiteishBg ,
                textAlign = TextAlign.Center ,
                fontSize = 20.sp
            )

            Spacer(modifier = modifier.padding(2.dp))


        }

      }
    }
