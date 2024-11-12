package skosana.avianaapp1.Features.GoogleMap.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.GoogleMap.componentsBottomSheet.BottomSheetContent

import skosana.avianaapp1.Presentation.Navigation.MapsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePartialBottomSheet(
    viewModel: MapsViewModel
) {
     val Name = CoroutineName("Test")
    val Scope = CoroutineScope(Name)

    var showBottomSheet by remember { mutableStateOf(false) }
    val showBottomSheet2  =  viewModel.showBottomSheet.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val selectedHotspot  =  viewModel.selectedHotspot.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        if (showBottomSheet2.value) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = {


                    Scope.launch {
                        viewModel.ShowBottomSheet()
                    }

                }
            ) {
                Text(
                    if(selectedHotspot.value == null) {
                        "No Hotspot Selected"
                    }
                    else {
                        ""
                    }
                    ,
                    modifier = Modifier.padding(16.dp)
                )
                if(selectedHotspot.value != null) {
                    BottomSheetContent(viewModel)
                }
            }
        }
    }
}