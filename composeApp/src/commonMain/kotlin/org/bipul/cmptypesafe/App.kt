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
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed interface Destination{

    @Serializable
    data object Auth: Destination

    @Serializable
    data class Dashboard(val dummy: String): Destination

    @Serializable
    data class Dashboard2(val id: Int): Destination
}


@Serializable
data class Dummy(
    val id: Int,
    val name: String
)

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navHostController = rememberNavController()

        NavHost(navController = navHostController, startDestination = Destination.Auth){

            composable<Destination.Auth>{
                AuthScreen(modifier = Modifier.fillMaxSize()){
                    val dummyString = Json.encodeToString(Dummy(id = 1, name = "Dummy Name"))
                    navHostController.navigate(Destination.Dashboard(dummyString))
                }
            }

            composable<Destination.Dashboard>{
                val dummyString = it.toRoute<Destination.Dashboard>().dummy
                val dummy = Json.decodeFromString<Dummy>(dummyString)
                DashboardScreen(modifier = Modifier.fillMaxSize(), dummy){
                    navHostController.navigate(Destination.Dashboard2(id = 12))
                }
            }

            composable<Destination.Dashboard2>{
                val id = it.toRoute<Destination.Dashboard2>().id
                Dashboard2Screen(modifier = Modifier.fillMaxSize(), id){
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
fun DashboardScreen(modifier: Modifier = Modifier, dummy: Dummy, onClick: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard Screen. Dummy: ${dummy.name} (Id: ${dummy.id})")

        Button (onClick) {
            Text("Go to Dashboard2")
        }

    }
}

@Composable
fun Dashboard2Screen(modifier: Modifier = Modifier, id: Int, onClick: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dashboard 2 Screen. Id: ${id}")

        Button (onClick) {
            Text("Pop this Dashboard 2 screen")
        }

    }
}