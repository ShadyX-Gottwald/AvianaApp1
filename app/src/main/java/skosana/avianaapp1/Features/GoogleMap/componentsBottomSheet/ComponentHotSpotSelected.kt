package skosana.avianaapp1.Features.GoogleMap.componentsBottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.sp
import skosana.avianaapp1.Features.GoogleMap.Constants.BottomSheetOptions
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround

@Composable
fun BottomSheetContent(viewModel: MapsViewModel) {
    val selectedHotspot = viewModel.selectedHotspot.collectAsState()
    val showBottomSheetOptions = viewModel.bottomSheetOptions.collectAsState()

    if(showBottomSheetOptions.value == BottomSheetOptions.HOTSPOT_INFO) {
        Column {
            Text(text = selectedHotspot.value?.locName!!, fontSize = 25.sp,color = DarkPurpleBackGround)
            Row {
                Text(text = "${selectedHotspot.value?.numSpeciesAllTime!!} Species", fontSize = 17.sp)
                Text(text = "|", fontSize = 17.sp)
                Text(text = "Date ${selectedHotspot.value?.latestObsDt!!}", fontSize = 17.sp,color = DarkPurpleBackGround)

            }

        }
    }

}