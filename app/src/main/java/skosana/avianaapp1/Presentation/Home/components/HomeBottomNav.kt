package skosana.avianaapp1.Presentation.Home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import skosana.avianaapp1.Presentation.Common.ToastMsg
import skosana.avianaapp1.Presentation.Home.Models.BottomNavItem
import skosana.avianaapp1.Presentation.Home.constants.HomePageConstants
import skosana.avianaapp1.ui.theme.DarkPurpleBackGround
import skosana.avianaapp1.ui.theme.Peach

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeBottomNav(
    navController: NavController ,
    NavToHome: () -> Unit ,
    NavToBirds: () -> Unit ,

) {

    //val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    NavigationBar(
        containerColor = DarkPurpleBackGround,
        contentColor = Peach,
        //tonalElevation = 5.dp,
        modifier = Modifier.height(115.dp)
            .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp)),
    ) {
//Iterate through Bottom Navigation Items.

        HomePageConstants.BottomNavItems.forEachIndexed { index, NavItem ->
            NavigationBarItem(selected = index == selectedItemIndex,
                onClick = {
                    selectedItemIndex = index

                      navController.navigate(NavItem.route)

                },
                icon = {
                    Icon(imageVector = NavItem.icon, contentDescription = "Icon", tint = Peach)
                },
                label = {
                    Text(text = NavItem.label, color = Peach)
                }
            )

        }
    }

}




