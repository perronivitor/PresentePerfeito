package com.hacka.presenteperfeito.core.common.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hacka.presenteperfeito.core.common.navigation.Route
import com.hacka.presenteperfeito.core.designSystem.PerfectGiftTheme
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavItem<T>(
    val route: T,
    val icon: @Composable () -> Unit,
    val label: String? = null,
) {

    @Serializable
    data object Home : BottomNavItem<Route.HomeRoute>(
        route = Route.HomeRoute,
        icon = { Icon(Icons.Default.Home, contentDescription = "Início") },
        label = "Início"
    )

    @Serializable
    data object Adds : BottomNavItem<Route.InspectionScreen>(
        route = Route.InspectionScreen,
        icon = { CustomIconAdds() },
        label = ""
    )

    @Serializable
    data object Profile : BottomNavItem<Route.ProfileScreen>(
        route = Route.ProfileScreen,
        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
        label = "Perfil"
    )
}

@Composable
fun CustomIconAdds(
    modifier: Modifier = Modifier,
    iconSize: Dp = 44.dp,
    boxSize: Dp = 48.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector = Icons.Default.Add,
    contentDescription: String = "Adicionar Investigação",
) {
    Box(
        modifier = modifier
            .size(boxSize)
            .border(1.dp, color, CircleShape)
            .background(color, CircleShape)
            .padding(8.dp)

    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(iconSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCustomIconAdds() {
    PerfectGiftTheme {
        CustomIconAdds()
    }
}