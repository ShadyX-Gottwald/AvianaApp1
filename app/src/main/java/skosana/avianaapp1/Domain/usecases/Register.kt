package skosana.avianaapp1.Domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import skosana.avianaapp1.Data.LoginRegisterImpl

class Register {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
   // private val _firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _service: LoginRegisterImpl = LoginRegisterImpl(auth)

    suspend operator fun invoke(email: String , password: String)= _service.signUpToFirebase(email ,password)
}