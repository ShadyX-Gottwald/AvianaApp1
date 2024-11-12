package skosana.avianaapp1.Presentation.Register.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel


@Composable
fun RegisterInputsSection(
    context : Context,
    viewModel: LoginRegisterViewModel,
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
            .padding(start = 12.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = emailText,
            onValueChange = { emailText = it },
            label = {
                Text(text = "Email")

            },
            shape = RoundedCornerShape(5.dp),
            trailingIcon = {

                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                )
            }

        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = emailText,
            onValueChange = { passwordText = it },
            label = {
                Text(text = "Password")

            },
            shape = RoundedCornerShape(5.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password"
                )
            }

        )

    }
}