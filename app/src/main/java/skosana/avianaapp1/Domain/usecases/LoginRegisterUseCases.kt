package skosana.avianaapp1.Domain.usecases

import com.google.firebase.firestore.FirebaseFirestore
import skosana.avianaapp1.Data.LoginRegisterImpl

data class LoginRegisterUseCases(
    val login: Login = Login(),
    val register: Register = Register() ,
)