package skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.DTOs

import skosana.avianaapp1.Features.GoogleMap.GeoCoding.models.GeocodeApiModel

fun GeocodeApiModel.GeocodeToAddressDTO(): String {
    val address = this.results[0].formattedAddress
    return address
}