package skosana.avianaapp1.Presentation.Login.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.ui.theme.WhiteNew

@Composable
fun LoginBottomSection (
    context : Context,
    viewModel: LoginRegisterViewModel,
    modifier: Modifier
){

    Spacer(modifier = Modifier.padding(vertical = 40.dp))
    Column (
        modifier = Modifier.fillMaxWidth(1F) ,

    ){
        Box (
            modifier = Modifier
                .fillMaxWidth(1F)
                .background(
                    color = WhiteNew,
                    shape = RoundedCornerShape(8)
                )
        ){

        }
    }


}