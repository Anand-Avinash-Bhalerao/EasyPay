package com.billion_dollor_company.easypay.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.billion_dollor_company.easypay.util.Helper

@Composable
fun HeightSpacer(height: Int = 24) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun WidthSpacer(width: Int = 12) {
    Spacer(modifier = Modifier.width(width.dp))
}

@Composable
fun IPAddressChangeDialog(
    currentIPAddress: String,
    saveClicked: (String) -> Unit,
    cancelClicked: () -> Unit
) {
    var ipAddressText by remember {
        mutableStateOf("")
    }
    var hasTextError by remember {
        mutableStateOf(false)
    }
    Dialog(
        onDismissRequest = {
            cancelClicked()
        }
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                HeightSpacer(24)
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "PSP IP Address",
                    style = MaterialTheme.typography.titleLarge.copy(
                        textAlign = TextAlign.Center
                    )
                )
                HeightSpacer(12)
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "The current IP address is:",
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = currentIPAddress,
                    style = MaterialTheme.typography.titleLarge.copy(
                        textAlign = TextAlign.Center
                    )
                )
                HeightSpacer(16)
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = ipAddressText,
                    onValueChange = {
                        ipAddressText = it
                    },
                    placeholder = {
                        Text(text = "Enter new IP Address")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = "IP Address icon"
                        )
                    },
                    supportingText = {
                        if (hasTextError) {
                            Text(text = "This is the IP address which the CL uses to communicate with the PSP server. This is saved in DataStore. The format should be 255.255.255.255")
                        }
                    },
                    isError = hasTextError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        if (hasTextError)
                            Icon(
                                Icons.Filled.Error,
                                "error",
                                tint = MaterialTheme.colorScheme.error
                            )
                    },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = {
                            if (Helper.isIpValid(ipAddressText)) {
                                saveClicked(ipAddressText)
                            } else {
                                hasTextError = true
                            }
                        }
                    ) {
                        Text(text = "Save")
                    }
                    TextButton(
                        onClick = {
                            cancelClicked()
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}
