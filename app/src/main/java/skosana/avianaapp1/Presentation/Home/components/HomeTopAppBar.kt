package skosana.avianaapp1.Presentation.Home.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    navController: NavController

) {
   CenterAlignedTopAppBar(title = {
       navController.currentBackStackEntry?.destination?.route?.let { Text(text = it) }
       //navController.currentBackStackEntry?.destination.toString()
   })

}