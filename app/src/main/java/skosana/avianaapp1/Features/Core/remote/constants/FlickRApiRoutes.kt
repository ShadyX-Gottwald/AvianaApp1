package skosana.avianaapp1.Features.Core.remote.constants

class FlickRApiRoutes {

    companion object{

        const val FLICKR_API_KEY = "bc3372a01f95e6efcb1c98068b721fb0"
        const val FLICKR_BASE_URL = "https://www.flickr.com/services/rest/"

        fun FlickR_Search_Birds_Url_Builder(tag: String): String {
            return "https://www.flickr.com/services/rest/" +
                    "?method=flickr.photos.search&" +"format=json" +
                    "api_key=$FLICKR_API_KEY&"+"tag=$tag"
        }
    }
}