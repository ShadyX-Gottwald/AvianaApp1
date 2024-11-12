package skosana.avianaapp1.Features.LoginRegister

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import skosana.avianaapp1.Domain.DTOs.LoginUserDTO
import skosana.avianaapp1.Domain.Models.User


interface ILoginRegister {

    suspend fun signUpToFirebase(user: String, password: String): Task<AuthResult>?

    suspend fun loginToFirebase(user: LoginUserDTO): Task<AuthResult>?
}


