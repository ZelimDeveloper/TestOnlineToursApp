package com.example.testonlinetoursapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.testonlinetoursapp.R
import com.example.testonlinetoursapp.screens.FavoriteScreen
import com.example.testonlinetoursapp.screens.HelpScreen
import com.example.testonlinetoursapp.screens.SearchResultScreen
import com.example.testonlinetoursapp.screens.SearchScreen
import com.example.testonlinetoursapp.screens.SettingsScreen
import com.example.testonlinetoursapp.ui.theme.BottomSelectedColor
import com.example.testonlinetoursapp.ui.theme.BottomUnselectedColor



@Composable
fun AppNavigation() {

    val navController = rememberNavController()

     val items = listOf(
        NavigationItem.Search,
        NavigationItem.Help,
        NavigationItem.Favorite,
        NavigationItem.Settings,
    )

        Scaffold(
            bottomBar = {
                BottomNavigation(backgroundColor = MaterialTheme.colorScheme.background) {

                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false
                        BottomNavigationItem(
                            selected = selected,
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = null,
                                    tint = if (selected) BottomSelectedColor else BottomUnselectedColor)
                            },
                            label = { Text(stringResource(item.titleResId)) },
                            selectedContentColor = BottomSelectedColor,
                            unselectedContentColor = BottomUnselectedColor,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->

            NavHost(navController, startDestination = Screen.SearchGraph.route, Modifier.padding(innerPadding))
            {
                searchScreenNavGraph(
                    searchScreenContent = {
                        SearchScreen(
                            onClickConfirm = { navController.navigate(Screen.SearchResult.route)})
                    },
                    searchResultScreenContent = { SearchResultScreen() })

                composable(Screen.Help.route) { HelpScreen() }
                composable(Screen.Favorite.route) { FavoriteScreen() }
                composable(Screen.Settings.route) { SettingsScreen() }

            }
        }

}

sealed class NavigationItem(val screen: Screen, @StringRes val titleResId: Int, @DrawableRes val icon: Int) {
    data object Search: NavigationItem(
        screen = Screen.SearchGraph,
        titleResId =  R.string.search,
        icon = R.drawable.search
    )
    data object Help: NavigationItem(
        screen = Screen.Help,
        titleResId =  R.string.help,
        icon =  R.drawable.help
    )
    data object Favorite: NavigationItem(
        screen = Screen.Favorite,
        titleResId = R.string.favorite,
        icon = R.drawable.favorite
    )
    data object Settings: NavigationItem(
        screen = Screen.Settings,
        titleResId =  R.string.settings,
        icon =  R.drawable.settings
    )
}

sealed class Screen(val route: String) {
    data object SearchGraph : Screen("search_graph_route")
    data object Search : Screen("search")
    data object Help : Screen("help", )
    data object Favorite : Screen("favorite")
    data object Settings : Screen("settings")
    data object SearchResult : Screen("searchResult")
}




fun NavGraphBuilder.searchScreenNavGraph(
    searchScreenContent: @Composable () -> Unit,
    searchResultScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.Search.route,
        route = Screen.SearchGraph.route
    ) {
        composable(Screen.Search.route) { searchScreenContent() }
        composable(Screen.SearchResult.route) { searchResultScreenContent() }
    }
}