package skosana.avianaapp1.Presentation.Navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import skosana.avianaapp1.Data.LoginRegisterImpl
import skosana.avianaapp1.Domain.Constants.AuthenticationState
import skosana.avianaapp1.Domain.DTOs.LoginUserDTO
import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO
import skosana.avianaapp1.Domain.Models.User
import skosana.avianaapp1.Domain.usecases.LoginRegisterUseCases
import skosana.avianaapp1.Utils.NetworkResponse
import skosana.avianaapp1.Utils.Resource

private val Tag = "LR_VM_TAG"
class LoginRegisterViewModel(
    private val usecases: LoginRegisterUseCases = LoginRegisterUseCases() ,
    private val auth: FirebaseAuth = FirebaseAuth.getInstance() ,

): ViewModel() {

    //Services implementation
    private val _services = LoginRegisterImpl(auth)

    //Auth state Check
    private var _authState: MutableStateFlow<NetworkResponse<AuthenticationState>?> = MutableStateFlow(null)
    var AuthState= _authState.asStateFlow()

    private var _authState2: MutableStateFlow<Resource<AuthenticationState>?> = MutableStateFlow(null)
    var AuthState2= _authState2.asStateFlow()


    //Check User Register
    private var _registerUser: MutableStateFlow<RegisterUserDTO> = MutableStateFlow(RegisterUserDTO())
    var RegisterUser = _registerUser.asStateFlow()
    var registerUser = RegisterUserDTO()

    //Check Login User
    private var _loginUser: MutableStateFlow<LoginUserDTO> = MutableStateFlow(LoginUserDTO())
    var LoginUser = _loginUser.asStateFlow()
    var loginUser = LoginUserDTO()


    fun UpdateRegisterValues(name: String , email: String ,
                             password: String ,
                             confirmpass: String
    ): RegisterUserDTO {
       val updated =  RegisterUser.value.copy(name ,email ,password,confirmpass)
        Log.i(Tag, updated.toString())
        Log.i(Tag, RegisterUser.value.toString())
        registerUser = updated
        return updated

        //RegisterUser.value
    }

    fun UpdateLoginValues(email: String ,
                          password: String): LoginUserDTO{

        val updated =  LoginUser.value.copy(email ,password)
        Log.i(Tag, updated.toString())
        Log.i(Tag, LoginUser.value.toString())
        loginUser = updated
        return updated

    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun loginToFirebase(user: LoginUserDTO) = withContext(Dispatchers.IO) {

        try {
          val result = _services.loginToFirebase(user)?.await(CancellationTokenSource())

            if(result?.user != null) _authState.emit(NetworkResponse.Success(AuthenticationState.Authenticated))
            else throw Exception()

        }catch(e: Exception) {

            _authState.emit(NetworkResponse.Error(AuthenticationState.NotAuthenticated ,
                e.message!!)
            )
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun registerToFirebase(user: RegisterUserDTO) = withContext(Dispatchers.IO) {

        try {
           val res =  _services.signUpToFirebase(user.Email ,
               user.Password)?.await(CancellationTokenSource()
               )

            if(res?.user?.email == user.Email) _authState.emit(NetworkResponse.Success(
                AuthenticationState.Authenticated)
            )

                else throw Exception()


        }catch(e: Exception) {
            _authState.emit(NetworkResponse.Error(AuthenticationState.NotAuthenticated ,
                "Failed To Register")
            )
            //_authState
           // AuthState.map {  NetworkResponse.Error(AuthenticationState.NotAuthenticated ,"Failed To Register.")}

        }



    }

}