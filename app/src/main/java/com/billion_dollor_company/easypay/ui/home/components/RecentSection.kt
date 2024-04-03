package com.billion_dollor_company.easypay.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.billion_dollor_company.easypay.models.PayeeInfo
import com.billion_dollor_company.easypay.models.RecentInteractionInfo
import com.billion_dollor_company.easypay.ui.components.HeightSpacer


@Composable
fun RecentSection(
    list: List<RecentInteractionInfo>,
    onUserClicked: (RecentInteractionInfo) -> Unit
) {
    Text(
        text = "Recents",
        style = MaterialTheme.typography.titleLarge
    )
    HeightSpacer(16)
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .height(88.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(list) {
            RecentPersonItem(person = it, onUserClicked)
        }
    }
}

@Composable
fun RecentPersonItem(
    person: RecentInteractionInfo,
    onUserClicked: (RecentInteractionInfo) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                onUserClicked(person)
            }
            .padding(vertical = 12.dp),

    ) {
        Image(
            painter = painterResource(id = person.userImage),
            contentDescription = "Image of the person named ${person.firstName}",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        HeightSpacer(4)
        Text(
            text = person.firstName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
