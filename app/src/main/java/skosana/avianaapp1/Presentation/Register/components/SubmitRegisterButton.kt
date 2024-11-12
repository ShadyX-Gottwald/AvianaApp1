package skosana.avianaapp1.Presentation.Register.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.Utils.RegisterValidations
import skosana.avianaapp1.ui.theme.Peach
import skosana.avianaapp1.ui.theme.WhiteNew

val TAG  = "REGISTER_PAGE_TAG"
@Composable
fun SubmitRegisterButton(
    context: Context,
    viewModel: LoginRegisterViewModel,
    RegisterButtonClick: suspend (RegisterUserDTO) -> Unit,
    Scope: CoroutineScope
) {


    Spacer(modifier = Modifier.padding(vertical = 6.dp))

    Column(
        modifier = Modifier.fillMaxWidth(1F)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.padding(vertical = 40.dp))

        Button(
            modifier = Modifier
                .width(120.dp)
                .height(60.dp)
                .padding(top = 10.dp)
            ,
            colors = ButtonDefaults.outlinedButtonColors(WhiteNew),
            onClick = {
                //val valid  = RegisterValidations.ValidDetails(viewModel.RegisterUser.value)
//                if(viewModel.RegisterUser.value.Email.contains(".com")){
//                    Scope.launch(Dispatchers.IO) {
//                            RegisterButtonClick
//                    }
//                }
                // {
                    Scope.launch(Dispatchers.Default) {
                        Log.i(TAG ,viewModel.RegisterUser.value.toString())
                        ToastMsg(context = context, message = "Invalid Field Inputs. Try Again!")

                    }
              //  }

            } ,

            ) {
            Text(
                text = "Sign Up" , fontWeight = FontWeight.SemiBold ,
                color = Peach ,
                textAlign = TextAlign.Center ,
                fontSize = 18.sp
            )

        }

    }

}


