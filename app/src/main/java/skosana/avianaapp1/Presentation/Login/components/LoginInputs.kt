package skosana.avianaapp1.Presentation.Login.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.ui.theme.GreenOutline

@Composable
fun LoginInputs(
    context : Context ,
    viewModel: LoginRegisterViewModel,
    modifier: Modifier
) {
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 50.dp, end = 10.dp, bottom = 5.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = emailText,
            onValueChange = {
                emailText = it
                viewModel.UpdateLoginValues(
                    emailText,
                    passwordText,

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
                    contentDescription = "Email",
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
                viewModel.UpdateLoginValues(
                    emailText,
                    passwordText,

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
                    contentDescription = "Password",
                )
            }

        )

    }
}