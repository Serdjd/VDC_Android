package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.ui.component.InfoCard
import com.treintaYTres.vdc.ui.component.ListCard
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.event.EventDetails

@Composable
fun EventDetailsScreen(
    details: EventDetails
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(details.basicInfo) {
            ListCard(
                header = header,
                items = info
            )
        }
        with(details.comments) {
            InfoCard(
                New(
                    text = info,
                    header = header
                )
            )
        }
        details.adminInfo?.let {
            ListCard(
                header = it.header,
                items = it.info
            )
        }
    }
}