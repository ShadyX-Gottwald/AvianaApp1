package skosana.avianaapp1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import skosana.avianaapp1.Features.Core.remote.FlickrApiClient
import skosana.avianaapp1.Features.GoogleMap.components.MapScreen2
import skosana.avianaapp1.Presentation.Home.HomePage
import skosana.avianaapp1.Presentation.Navigation.BirdViewModel
import skosana.avianaapp1.Presentation.Navigation.MainNav
import skosana.avianaapp1.Presentation.Navigation.MapsViewModel
import skosana.avianaapp1.ui.theme.AvianaApp1Theme
import skosana.avianaapp1.ui.theme.WhiteishBg

private val TAG = "Main-Tag"
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
            AvianaApp1Theme {
                // Main App Navigation
                val viewModel by lazy { MapsViewModel(fusedLocationProviderClient) }
                val birdViewModel by lazy { BirdViewModel() }
                MainNav(
                    viewModel,
                    birdViewModel,
                    fusedLocationProviderClient
                )


                val context = LocalContext.current

                //BottomSheetMyScreen()
                //PartialBottomSheet()
               // MainScreen()
                //PartialBottomSheet()
                //ApiTestResultScreen(viewModel,birdViewModel)

              // HomePage(context = context ,viewModel,birdViewModel,fusedLocationProviderClient)

            }
        }
    }
}

@Composable
fun MainScreen(
    fusedLocationProviderClient: FusedLocationProviderClient,viewModel: MapsViewModel,
    useSystemUIController: Boolean = true) {

   // SimpleGoogleMapAppTheme(useSystemUIController = useSystemUIController) {
        MapScreen2(fusedLocationProviderClient, viewModel)
   // }
}

@Composable
fun ApiTestResultScreen(mapViewModel: MapsViewModel, birdViewModel: BirdViewModel) {
    LaunchedEffect(true) {
       // mapViewModel.GetPostFromApi()
        try{
           // mapViewModel.NearbyHotspots()
           // mapViewModel.GetAddressFromApi()
          //  birdViewModel.getTaxonomicBirds()
           // birdViewModel.NotableBirdSightingsByRegion(mapViewModel.subRegionCode.value,mapViewModel.countyCode.value)
        }catch (e:Exception){
            Log.i(TAG,e.message.toString())
        }

    }
  //  val postResult = mapViewModel.Post.collectAsState()
    val hotspots = mapViewModel.HotspotsSimple.collectAsState()
    val birds = birdViewModel.BirdsDefault.collectAsState()
    val sightings = birdViewModel.NotableBirdSightings.collectAsState()
    Scaffold (


    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(1F)
                .background(WhiteishBg)
                .padding(vertical = 6.dp)

        ) {
            Spacer(modifier = Modifier.padding(it))
            Column(
               // horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
//                Text(
//                    text = "Notification Settings",
//                    color = Color.Black,
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.W800
//                )

                ShowBirdWithImage(birdViewModel,mapViewModel)

                    //Text(text = if(postResult.value.isEmpty()) "Empty" else postResult.value[0].title ,color = Color.Blue)
                    //Text(text = if( "Empty" else hotspots.value.isEmpty()) "Empty" else postResult.value[0].title ,color = Color.Blue)

//                LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//
//
//                ) {
//                    sightings.value.forEachIndexed { index, notableBirdSightings ->
//                        item {
//                            BirdCardComponent(titleText = notableBirdSightings.sciName ,
//                                subtitleText = notableBirdSightings.locName,
//                                otherName = notableBirdSightings.comName,
//                                dateText = notableBirdSightings.obsDt
//                            )
//                        }
//                    }
//                }

//                    sightings.value.forEach{
//                        //BirdCardComponent(titleText = it.sciName , subtitleText = it.locName)
//                        LazyColumn(
//                            verticalArrangement = Arrangement.spacedBy(10.dp),
//                            content = {
//                                items(sightings.value.size){ birds ->
//                                    BirdCardComponent(titleText = it.sciName , subtitleText = it.locName)
//                                }
//                            }
//
//                        ) {
//
//                        }
//                        BirdProfileCard(titleText = it.sciName , subtitleText = it.locName,
//                            locationText = it.sciName
//                        )
//                    }


                   // Text(text = sightings.value.size.toString(),color = Color.Blue)


            }

        }
    }

}

@Composable
fun ShowBirdWithImage(
    birdViewModel: BirdViewModel,
    mapViewModel: MapsViewModel

) {
    LaunchedEffect(true) {
        // mapViewModel.GetPostFromApi()
        try{
            mapViewModel.NearbyHotspots()
            // mapViewModel.GetAddressFromApi()
            //birdViewModel.getTaxonomicBirds()
            // birdViewModel.NotableBirdSightingsByRegion(mapViewModel.subRegionCode.value,mapViewModel.countyCode.value)
        }catch (e:Exception){
            Log.i(TAG,e.message.toString())
        }

    }
    val birds = birdViewModel.BirdsDefault.collectAsState()
    val photourls = birdViewModel.BirdPhotoUrls.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        val responseRoute = mutableStateOf("")


        birds.value.take(50).forEachIndexed { index, it ->
            item {
                LaunchedEffect(true) {
                    // val urlFlickerInit = FlickRApiRoutes.FlickR_Search_Birds_Url_Builder(it.sciName)
                    try {
                        val client = FlickrApiClient.flickrService.GetImagesOfSubject(it.sciName)
                        if (client.isSuccessful) {
                            val response = client.body()!![0]

                            Log.d("Response", response.toString())
                            val urlFlicker = "https://live.staticflickr.com/" +
                                    "${response.server}/" +
                                    "${response.id}_${response.secret}" +
                                    "_b.jpg"

                            Log.d("URL", urlFlicker)

                            photourls.value.plus(it.sciName to urlFlicker)

                            //responseRoute.value = urlFlicker

                        }

                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())
                    }

                }

                Card {
                    ListItem(
                        headlineContent = { Text(text = it.comName) },
                        supportingContent = { Text(text = it.sciName) },
                        leadingContent = {

                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(photourls.value[it.sciName])
                                    .build(),
                                placeholder = painterResource(id = R.drawable.avianalogo),
                                contentDescription = "ImageRequest example",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    )
                }
            }

        }


        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun BottomSheetMyScreen() {
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            var showBottomSheet by remember { mutableStateOf(false) }
            Scaffold(
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text("Show bottom sheet") },
                        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                        onClick = {
                            showBottomSheet = true
                        }
                    )
                }
            ) { contentPadding ->
                // Screen content

                Box(modifier = Modifier.padding(contentPadding))

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState
                    ) {
                        // Sheet content
                        Button(onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }) {
                            Text("Hide bottom sheet")
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun ProfileImage(
//    text: String,
//    text2: String
//) {
//    Card(
//        modifier = Modifier.size(70.dp),
//        shape = CircleShape,
//
//        ) {
//        Column() {
//            Text(text = text)
//            Text(text = text2)
//        }
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = "image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//    }
//}




