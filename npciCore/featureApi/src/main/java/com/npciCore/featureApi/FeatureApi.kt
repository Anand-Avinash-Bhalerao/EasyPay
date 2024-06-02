package com.npciCore.featureApi

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    val startDestination : String
    val route : String

    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )

}