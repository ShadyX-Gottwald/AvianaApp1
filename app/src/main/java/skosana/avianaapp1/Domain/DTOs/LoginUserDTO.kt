package skosana.avianaapp1.Domain.DTOs

data class LoginUserDTO (
    val email: String = "" ,
    val password: String = ""
) {
    override fun toString(): String {
        return " :$email : $password "
    }
}