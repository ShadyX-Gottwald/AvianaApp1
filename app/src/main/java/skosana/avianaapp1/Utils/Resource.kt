package skosana.avianaapp1.Utils

sealed class Resource< out T>(
    open var data: @UnsafeVariance T? = null,
    open var message: String? = null
) {

    data class Success <T>(override var data: T?, override var message: String? = ""): Resource<T>()
    data class Failure<T>(override var message: String?): Resource<T>(message = message)
    data class Loading<T>(override var data: T?): Resource<T>()
    class Unspecified<T>(): Resource<T>()


}