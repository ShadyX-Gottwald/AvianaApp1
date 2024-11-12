package skosana.avianaapp1.Presentation.Home.TabComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.Constants.AuthenticationState
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.GreenOutline
import skosana.avianaapp1.ui.theme.Peach
import skosana.avianaapp1.ui.theme.WhiteNew

@Composable
fun  CreateNewTabComponent(

) {
    val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       

        SaveBirdForm()

    }
}

@Composable
private fun SaveBirdForm(
    auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    var emailText by remember {
        mutableStateOf(auth.currentUser?.email.toString())
    }

    var latitudeText by remember {
        mutableStateOf("")
    }

    var longitudeText by remember {
        mutableStateOf("")
    }

    var birdCommonNameText by remember {
        mutableStateOf("")

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkPurpleBackGround)
            .padding(start = 12.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),

    ) {


        //Email Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = emailText,
            onValueChange = {
                emailText = it

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


        //Latitude Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = latitudeText,
            onValueChange = {
                latitudeText = it

            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Location Latitude")

            },
            //Password Keyboard
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "Password", tint = GreenOutline
                )
            }

        )
        //Longitude Field
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = longitudeText,
            onValueChange = {
                longitudeText = it

            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Location Longitude")

            },
            shape = RoundedCornerShape(5.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "Password"
                )
            }

        )

        //Bird Common Name
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
            value = birdCommonNameText,
            onValueChange = {
                birdCommonNameText = it

            },
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            label = {
                Text(text = "Location Longitude")

            },
            shape = RoundedCornerShape(5.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.RemoveRedEye,
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

                },

                ) {
                Text(
                    text = "Save Sighting", fontWeight = FontWeight.SemiBold,
                    color = Peach,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )

            }

        }

    }


}