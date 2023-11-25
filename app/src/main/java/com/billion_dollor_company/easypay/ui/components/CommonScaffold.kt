package com.billion_dollor_company.easypay.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.ui.home.CompanyLogo
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScaffold(
    hasFAB: Boolean = false,
    iconFAB: ImageVector? = null,
    onFABClick: () -> Unit = {},
    content: @Composable (ColumnScope) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            if (hasFAB) {
                FloatingActionButton(
                    onClick = {
                        onFABClick()
                    }
                ) {
                    Icon(
                        imageVector = iconFAB!!,
                        contentDescription = "FAB Icon"
                    )
                }
            }
        }
    ) { paddingValue ->
        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Column( // or whatever your parent composable is
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                content(this)
            }
        }
    }
}