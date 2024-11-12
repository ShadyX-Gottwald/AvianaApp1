package skosana.avianaapp1.Domain.DTOs

data class RegisterUserDTO (
    val UserName: String  = "",
    val Email: String = "",
    val Password: String ="",
    val ConfirmPass: String = ""

) {
    override fun toString(): String {
        return "$UserName :$Email : $Password "
    }
}

