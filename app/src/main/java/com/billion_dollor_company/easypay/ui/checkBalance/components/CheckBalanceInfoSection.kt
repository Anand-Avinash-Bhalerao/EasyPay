package com.billion_dollor_company.easypay.ui.checkBalance.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.billion_dollor_company.easypay.ui.checkBalance.CheckBalanceViewModel
import com.billion_dollor_company.easypay.ui.theme.light_grey


@Composable
fun CheckBalanceInfoSection(
    viewModel: CheckBalanceViewModel,
    modifier: Modifier,
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }
    ConstraintLayout(
        modifier = modifier
            .background(color = light_grey)
            .padding(12.dp)
    ) {
        val (texts, expandButton) = createRefs()

        val sectionSeparatorGuideline = createGuidelineFromEnd(0.08f)

        Column(modifier = Modifier.constrainAs(texts) {
            start.linkTo(parent.start)
            end.linkTo(sectionSeparatorGuideline)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }) {
            Text(
                text = "Check Balance", style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.DarkGray,
                ), modifier = Modifier.width(150.dp)
            )
            AnimatedVisibility(visible = isExpanded) {
                Column {
                    CheckBalanceSingleRow(
                        title = "REF ID",
                        mainText = viewModel.getRefID()
                    )
                    CheckBalanceSingleRow(
                        title = "ACCOUNT",
                        mainText = "${viewModel.getAccountNo()}"
                    )
                }
            }
        }

        val interactionSource = remember { MutableInteractionSource() }
        IconButton(
            onClick = {
                isExpanded = !isExpanded
            }, enabled = true, modifier = Modifier
                .indication(
                    interactionSource = interactionSource, indication = rememberRipple(
                        color = Color.Gray, radius = 18.dp
                    )
                )
                .constrainAs(expandButton) {
                    start.linkTo(sectionSeparatorGuideline)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }, interactionSource = interactionSource
        ) {
            Icon(
                imageVector = if(isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand payee section button",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }


    }
}

@Composable
fun CheckBalanceSingleRow(
    title: String, mainText: String
) {
    Row {
        Text(
            text = "$title:", style = MaterialTheme.typography.titleMedium.copy(
                color = Color.DarkGray,
            ), modifier = Modifier.width(85.dp)
        )
        Text(
            text = mainText.uppercase(), style = MaterialTheme.typography.titleMedium.copy(
                color = Color.DarkGray,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
