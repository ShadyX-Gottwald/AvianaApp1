package skosana.avianaapp1.Data

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import skosana.avianaapp1.Domain.DTOs.LoginUserDTO
import skosana.avianaapp1.Domain.Models.User
import skosana.avianaapp1.Features.LoginRegister.ILoginRegister


private val TAG2 = "LR_IMPL_TEST"
class LoginRegisterImpl(
    //private val store: FirebaseFirestore ,
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

): ILoginRegister {
    @SuppressLint("SuspiciousIndentation")
    override suspend fun signUpToFirebase(email: String, password: String): Task<AuthResult>? {

        try{
            val _auth = FirebaseAuth.getInstance()

            val res =  _auth.createUserWithEmailAndPassword(email , password).addOnSuccessListener {
                val email =  it.user?.email
                Log.i(TAG2 , email!!)

        }
            return res

        }catch (e: Exception ) {
                return null
        }


    }

    override suspend fun loginToFirebase(user: LoginUserDTO): Task<AuthResult>? {
       val result = auth.signInWithEmailAndPassword(user.email ,user.password)
        return result
    }

}