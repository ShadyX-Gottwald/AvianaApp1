package skosana.avianaapp1.Presentation.Login

import android.graphics.Color
import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import skosana.avianaapp1.Domain.DTOs.LoginUserDTO
import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Login.components.LoginBottomSection
import skosana.avianaapp1.Presentation.Login.components.LoginHeaderSection
import skosana.avianaapp1.Presentation.Login.components.LoginInputs
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.Utils.RegisterValidations
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.WhiteNew
import skosana.avianaapp1.ui.theme.WhiteishBg
import skosana.avianaapp1.ui.theme.Brown
import skosana.avianaapp1.ui.theme.Peach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    vIewModel: LoginRegisterViewModel,
    NavToRegisterPage: () -> Unit,
    LoginButtonClick: suspend (LoginUserDTO) -> Unit ,
    NavToHomePage: () -> Unit

) {
    val context = LocalContext.current
    val Scope_Name = CoroutineName("Login-RegisterScope")
    val Scope = CoroutineScope(Scope_Name)
    val Coroutine_Scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(1F)
            .background(
                DarkPurpleBackGround
            )


    ) { innerPadding ->



        Box(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(
                    DarkPurpleBackGround
                )

        ) {
            Column(
                Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.Center

            )
            {

                val authState  by vIewModel.AuthState.collectAsStateWithLifecycle()
                when(val auth = authState) {
                    is NetworkResponse.Loading -> {

                    }
                    is NetworkResponse.Success -> {
                        //Login Success , Show Message and Navigate
                        ToastMsg(context, "Login Successful .").show()
                        NavToHomePage.invoke()

                    }
                    is NetworkResponse.Error<*> -> {
                        ToastMsg(context , auth.message).show()
                    }
                    else -> {

                    }
                }

                //Branding Section
                LoginHeaderSection(
                    context = context,
                    modifier = Modifier,
                    viewModel = vIewModel,
                )
                //Text(text = Text)
                Spacer(modifier = Modifier.padding(vertical = 6.dp))

                Column(modifier = Modifier.fillMaxWidth(1F)) {

                    //Input Field Section
                    LoginInputs(
                        context = context,
                        modifier = Modifier,
                        viewModel = vIewModel,

                        )
                    Spacer(modifier = Modifier.padding(vertical = 15.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1F)
                            .height(300.dp)
                            .background(
                                color = WhiteNew,
                                shape = RoundedCornerShape(20)
                            )
                    ) {

                        Column(
                            modifier = Modifier.padding(start = 100.dp ,) ,
                            verticalArrangement = Arrangement.Center ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            //Forgot Password
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "Forgot Password?")
                            }
                            //LoginButton
                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp)
                                    .padding(top = 10.dp),
                                colors = ButtonDefaults.outlinedButtonColors(Brown),
                                onClick = {
                                    //Validate

                                    Scope.launch(Dispatchers.IO){
                                        LoginButtonClick.invoke(vIewModel.loginUser)
                                    }

                                } ,

                            ) {
                                Text(
                                    text = "Login" , fontWeight = FontWeight.SemiBold ,
                                    color = WhiteishBg ,
                                    textAlign = TextAlign.Center ,
                                    fontSize = 12.sp
                                )

                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            //Text Break
                            Text(text = "OR")
                            //Create Account Button

                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp)
                                    .padding(top = 10.dp),
                                colors = ButtonDefaults.outlinedButtonColors(Peach),
                                onClick = { NavToRegisterPage() }
                            ) {
                               // Spacer(modifier = Modifier.padding(10.dp))

                                Text(
                                    text = "Create Account" , fontWeight = FontWeight.SemiBold ,
                                    color = androidx.compose.ui.graphics.Color.Gray ,
                                    textAlign = TextAlign.Center ,
                                    fontSize = 12.sp
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}