package skosana.avianaapp1.Presentation.Register.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.Constants.AuthenticationState
import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.GreenOutline
import skosana.avianaapp1.ui.theme.Peach
import skosana.avianaapp1.ui.theme.WhiteNew
import skosana.avianaapp1.ui.theme.WhiteishBg

private val TAG1 = "RF_TAG"
@Composable
fun RegisterFieldsFullSection(
    context : Context,
    viewModel: LoginRegisterViewModel,
    Scope: CoroutineScope ,
    RegisterButtonClick: suspend(RegisterUserDTO) -> Unit
) {
    val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    var userNameText by remember {
        mutableStateOf("")
    }

    var emailText by remember {
        mutableStateOf("")
    }

    var passwordText by remember {
        mutableStateOf("")
    }

    var retypePasswordText by remember {
        mutableStateOf("")
    }

    var acceptConditionsBool by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
        // verticalArrangement = Arrangement.Center
    ) {


        //UserName
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = userNameText,

            onValueChange = {
                userNameText = it
                viewModel.UpdateRegisterValues(
                    userNameText,
                    emailText,
                    passwordText,
                    retypePasswordText
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),


            label = {
                Text(text = "UserName",)

            },

            shape = RoundedCornerShape(12.dp),
            trailingIcon = {

                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "UserName", tint = GreenOutline
                )
            }

        )


        //Email Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = emailText,
            onValueChange = {
                emailText = it
                viewModel.UpdateRegisterValues(
                    userNameText,
                    emailText,
                    passwordText,
                    retypePasswordText
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Email")

            },
            //Email Keyboard
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {

                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email", tint = GreenOutline
                )
            }

        )


        //Password Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = passwordText,
            onValueChange = {
                passwordText = it
                viewModel.UpdateRegisterValues(
                    userNameText,
                    emailText,
                    passwordText,
                    retypePasswordText
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Password")

            },
            //Password Keyboard
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password", tint = GreenOutline
                )
            }

        )
        //Retype Password Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = retypePasswordText,
            onValueChange = {
                retypePasswordText = it
                viewModel.UpdateRegisterValues(
                    userNameText,
                    emailText,
                    passwordText,
                    retypePasswordText
                )
            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Retype Password")

            },
            shape = RoundedCornerShape(5.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            }

        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Column(
            modifier = Modifier.fillMaxWidth(1F), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.padding(vertical = 40.dp))

            Button(
                modifier = Modifier
                    .width(120.dp)
                    .height(60.dp)
                    .padding(top = 10.dp),
                colors = ButtonDefaults.outlinedButtonColors(WhiteNew),
                onClick = {

                    viewModel.viewModelScope.launch (Dispatchers.IO){
                        RegisterButtonClick.invoke(viewModel.registerUser)
                        viewModel.AuthState.map { NetworkResponse.Success(AuthenticationState.Authenticated) }
                            .collect()
                    }

                },

                ) {
                Text(
                    text = "Sign Up", fontWeight = FontWeight.SemiBold,
                    color = Peach,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )

            }

        }

    }

}



