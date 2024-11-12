package skosana.avianaapp1.Features.GoogleMap.models

sealed class MapLoadState {
   data  object Loading : MapLoadState()
   data object Loaded : MapLoadState()
   data class Error(val message: String ) : MapLoadState()

}