package skosana.avianaapp1.Domain.Constants

sealed class AuthenticationState {

    data object Authenticated : AuthenticationState()
    data object NotAuthenticated : AuthenticationState()
}