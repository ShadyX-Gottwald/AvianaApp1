package skosana.avianaapp1.Presentation.Register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Navigation.LoginRegisterViewModel
import skosana.avianaapp1.Presentation.Register.components.RegisterFieldsFullSection
import skosana.avianaapp1.Presentation.Register.components.SubmitRegisterButton
import skosana.avianaapp1.Presentation.Register.components.TextHeaderSection
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround


@Composable
fun RegisterPage(vIewModel: LoginRegisterViewModel,
                 function: () -> Unit ,
                 RegisterButtonClick: suspend (RegisterUserDTO) -> Unit
) {
    val context = LocalContext.current
    val Scope_Name = CoroutineName("Login-RegisterScope")
    val Scope = CoroutineScope(Scope_Name)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(1F)
            .background(
                DarkPurpleBackGround
            )


    ) { innerPadding ->

        val authState = vIewModel.AuthState.collectAsStateWithLifecycle()
        when(val auth = authState.value) {
            is NetworkResponse.Loading -> {

            }
            is NetworkResponse.Success -> {
                ToastMsg(context, "Register Successful . Login Now?").show()
                function()

            }
            is NetworkResponse.Error<*> -> {
                ToastMsg(context , auth.message).show()
            }
            else -> {

            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(
                    color = DarkPurpleBackGround,
                    shape = RoundedCornerShape(8)
                )
        ) {



            TextHeaderSection()

            Box(
                modifier = Modifier
                    .fillMaxSize(1F)
                    .background(
                        DarkPurpleBackGround
                    )

            ) {
                Column(
                    Modifier.padding(innerPadding),
                   // verticalArrangement = Arrangement.Center
                )
                {


                    RegisterFieldsFullSection(context = context,
                        viewModel = vIewModel ,
                        Scope ,
                        RegisterButtonClick = RegisterButtonClick
                    )

                   // SubmitRegisterButton(context = context,
                      //  viewModel = vIewModel,RegisterButtonClick ,Scope)

                }
            }
        }
    }
}
