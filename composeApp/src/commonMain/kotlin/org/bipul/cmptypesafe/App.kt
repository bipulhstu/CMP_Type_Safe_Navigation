package org.bipul.cmptypesafe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

sealed interface Destination{

    @Serializable
    data object Auth: Destination

    @Serializable
    data object Dashboard: Destination

    @Serializable
    data object Dashboard2: Destination
}

@Composable
fun App() {
    MaterialTheme {

        val navHostController = rememberNavController()

        NavHost(navController = navHostController, startDestination = Destination.Auth){

            composable<Destination.Auth>{
                AuthScreen(modifier = Modifier.fillMaxSize()){
                    navHostController.navigate(Destination.Dashboard)
                }
            }

            composable<Destination.Dashboard>{
                DashboardScreen(modifier = Modifier.fillMaxSize()){
                    navHostController.navigate(Destination.Dashboard2)
                }
            }

            composable<Destination.Dashboard2>{
                Dashboard2Screen(modifier = Modifier.fillMaxSize()){
                    navHostController.popBackStack()
                }
            }

        }

    }
}


@Composable
fun AuthScreen(modifier: Modifier = Modifier, onClick: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Auth Screen")

        Button (onClick) {
            Text("Go to Dashboard")
        }

    }
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, onClick: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard Screen")

        Button (onClick) {
            Text("Go to Dashboard2")
        }

    }
}

@Composable
fun Dashboard2Screen(modifier: Modifier = Modifier, onClick: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard 2 Screen")

        Button (onClick) {
            Text("Pop this screen")
        }

    }
}