package skosana.avianaapp1.Features.GoogleMap.components

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.ktx.MapsExperimentalFeature
import skosana.avianaapp1.Features.GoogleMap.Utils.LocationUtils
import skosana.avianaapp1.Features.GoogleMap.componentsMaps.MapSimple
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.WhiteishBg


@Composable
fun MainScreen() {
   // var selectedMapOption by rememberSaveable { mutableStateOf(MapOptions.MAP_SIMPLE) }

    // Scaffold with a Multi-Floating Action Button (Multi-FAB)
    Scaffold(floatingActionButton = {
        MultiFloatingActionButton(
            items = listOf(
                FabButtonItem(
                    iconRes = Icons.Filled.Map,
                    label = "Simple Map"
                ) {
                   // selectedMapOption = MapOptions.MAP_SIMPLE
                },


                FabButtonItem(
                    iconRes = Icons.Filled.Streetview,
                    label = "Street View"
                ) {
                    //selectedMapOption = MapOptions.MAP_STREET_VIEW
                }
            ),
            fabIcon = FabButtonMain(),
            fabOption = FabButtonSub()
        )
    }, content = { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            when (selectedMapOption) {
//             //   MapOptions.MAP_SIMPLE -> MapSimple(currentLocation = LocationUtils.getDefaultLocation(),mapsViewModel = m)
//              //  MapOptions.MAP_STREET_VIEW -> MapStreetView()
//                else -> {}
//            }
        }
    })
}

//Maps





//Maps Sub
/**
 * Represents a sub-item for a Floating Action Button (FAB) with customized icon and background tints.
 * Sub-items are secondary action buttons that appear when the main FAB is expanded.
 */
interface FabButtonSub {
    val iconTint: Color
    val backgroundTint: Color
}

/**
 * Implementation of [FabButtonSub] interface.
 *
 * @property iconTint The [Color] used to tint the icon of the sub-item.
 * @property backgroundTint The [Color] used to tint the background of the sub-item.
 */
private class FabButtonSubImpl(
    override val iconTint: Color,
    override val backgroundTint: Color
) : FabButtonSub

/**
 * Creates a new instance of [FabButtonSub] with the provided icon and background tints.
 *
 * @param backgroundTint The [Color] used to tint the background of the sub-item.
 * @param iconTint The [Color] used to tint the icon of the sub-item.
 * @return A new instance of [FabButtonSub] with the specified icon and background tints.
 */
fun FabButtonSub(
    backgroundTint: Color = DarkPurpleBackGround,
    iconTint: Color = WhiteishBg
): FabButtonSub = FabButtonSubImpl(iconTint, backgroundTint)

//FB State
/**
 * Represents the state of a Floating Action Button (FAB), which can be either Collapsed or Expanded.
 * The FAB state is used to determine its visibility and behavior, such as showing or hiding sub-items.
 */
sealed class FabButtonState {
    object Collapsed : FabButtonState()
    object Expand : FabButtonState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()) {
        Collapsed
    } else {
        Expand
    }
}

/**
 * Remembers the state of a Multi-Floating Action Button (FAB) using [remember] and [mutableStateOf].
 *
 * @return A [MutableState] that holds the current state of the Multi-FAB.
 */
@Composable
fun rememberMultiFabState() =
    remember { mutableStateOf<FabButtonState>(FabButtonState.Collapsed) }

//FB Main
/**
 * Represents the main floating action button (FAB) with an icon and optional rotation.
 * The main FAB is the primary action button that can be expanded to reveal sub-items.
 */
interface FabButtonMain {
    val iconRes: ImageVector
    val iconRotate: Float?
}

/**
 * Implementation of [FabButtonMain] interface.
 *
 * @property iconRes The [ImageVector] representing the icon to be displayed on the main FAB.
 * @property iconRotate The optional rotation angle for the main FAB icon. If null, the icon will not be rotated.
 */
private class FabButtonMainImpl(
    override val iconRes: ImageVector,
    override val iconRotate: Float?
) : FabButtonMain

/**
 * Creates a new instance of [FabButtonMain] with the provided icon and optional rotation.
 *
 * @param iconRes The [ImageVector] representing the icon to be displayed on the main FAB.
 * @param iconRotate The optional rotation angle for the main FAB icon. If null, the icon will not be rotated.
 * @return A new instance of [FabButtonMain] with the specified icon and rotation.
 */
fun FabButtonMain(iconRes: ImageVector = Icons.Filled.Add, iconRotate: Float = 45f): FabButtonMain =
    FabButtonMainImpl(iconRes, iconRotate)

//FB Item
/**
 * Represents an item for a Floating Action Button (FAB) with an icon and label.
 *
 * @param iconRes The [ImageVector] representing the icon to be displayed on the FAB item.
 * @param label The label or text associated with the FAB item.
 * @param onClick Action to perform on click on the FAB item.
 *
 * @constructor Creates a new instance of [FabButtonItem].
 */
data class FabButtonItem(val iconRes: ImageVector, val label: String, val onClick: () -> Unit)


//FB List
/**
 * Composable function to display a Multi-Floating Action Button (Multi-FAB) that can be expanded to reveal sub-items.
 *
 * @param modifier The optional [Modifier] for customizing the layout of the Multi-FAB.
 * @param items The list of [FabButtonItem] representing the sub-items to be displayed when the Multi-FAB is expanded.
 * @param fabState The [MutableState] representing the current state of the Multi-FAB, whether it is expanded or collapsed.
 * @param fabIcon The [FabButtonMain] representing the main FAB with an icon and optional rotation.
 * @param fabOption The [FabButtonSub] representing the customization options for the sub-items.
 * @param stateChanged The optional callback function to notify when the state of the Multi-FAB changes (expanded or collapsed).
 */
@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<FabButtonItem>,
    fabState: MutableState<FabButtonState> = rememberMultiFabState(),
    fabIcon: FabButtonMain,
    fabOption: FabButtonSub = FabButtonSub(),
    stateChanged: (fabState: FabButtonState) -> Unit = {}
) {
    // Animation for rotating the main FAB icon based on its state (expanded or collapsed)
    val rotation by animateFloatAsState(
        if (fabState.value == FabButtonState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }, label = "Rotation"
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        // AnimatedVisibility to show or hide the sub-items when the Multi-FAB is expanded or collapsed
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            // LazyColumn to display the sub-items in a vertical list
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(items.size) { index ->
                    // Composable to display each individual sub-item
                    MiniFabItem(
                        item = items[index],
                        fabOption = fabOption
                    )
                }
                item {} // Empty item to provide spacing at the end of the list
            }
        }

        // Main FloatingActionButton representing the Multi-FAB
        FloatingActionButton(
            onClick = {
                fabState.value = fabState.value.toggleValue()
                stateChanged(fabState.value)
            },
            containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            // Icon for the main FAB with optional rotation based on its state (expanded or collapsed)
            Icon(
                imageVector = fabIcon.iconRes,
                contentDescription = "Main FAB",
                modifier = Modifier.rotate(rotation),
                tint = fabOption.iconTint
            )
        }
    }
}

/**
 * Composable function to display an individual sub-item of the Multi-Floating Action Button (Multi-FAB).
 *
 * @param item The [FabButtonItem] representing the sub-item with an icon and label.
 * @param fabOption The [FabButtonSub] representing the customization options for the sub-items.
 */
@Composable
fun MiniFabItem(
    item: FabButtonItem,
    fabOption: FabButtonSub,
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text label for the sub-item displayed in a rounded-corner background
        Text(
            text = item.label,
            style = typography.labelSmall,
            color = WhiteishBg,
            modifier = Modifier
                .clip(RoundedCornerShape(size = 8.dp))
                .background(DarkPurpleBackGround.copy(alpha = 0.5f))
                .padding(all = 8.dp)
        )

        // FloatingActionButton representing the sub-item
        FloatingActionButton(
            onClick = item.onClick,
            modifier = Modifier.size(40.dp),
            containerColor = fabOption.backgroundTint,
            contentColor = fabOption.iconTint
        ) {
            // Icon for the sub-item with customized tint
            Icon(
                imageVector = item.iconRes,
                contentDescription = "Float icon",
                tint = fabOption.iconTint
            )
        }
    }
}

// Map Types
