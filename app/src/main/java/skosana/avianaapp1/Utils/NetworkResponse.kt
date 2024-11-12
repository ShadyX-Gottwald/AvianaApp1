package skosana.avianaapp1.Utils



sealed class NetworkResponse<out T> {

    data class Success<out T>(val data: T):NetworkResponse<T>()
    data class  Error<Any>(val data: Any , val message: String):NetworkResponse<Nothing>()
    data object Loading:NetworkResponse<Nothing>()
    data class Idle<out T>(val default: T) :NetworkResponse<Nothing> ()

}