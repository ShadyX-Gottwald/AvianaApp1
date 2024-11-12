package skosana.avianaapp1.Utils

import skosana.avianaapp1.Domain.DTOs.RegisterUserDTO

class RegisterValidations {

    companion object {

        fun ValidDetails(user: RegisterUserDTO):Boolean {
            if(user.Email.contains("@") ) {
                return true;
            }
            return false
        }
    }
}