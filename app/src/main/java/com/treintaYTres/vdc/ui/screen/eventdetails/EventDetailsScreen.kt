package com.treintaYTres.vdc.ui.screen.eventdetails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.component.InfoCard
import com.treintaYTres.vdc.ui.component.ListCard
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Header
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.RowInfo
import com.treintaYTres.vdc.ui.model.event.DetailedEvent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventDetailsScreen(
    event: DetailedEvent,
    isAdmin: Boolean,
    navigateToMembers: () -> Unit,
    navigateToRollCall: () -> Unit
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(event) {
            ListCard(
                header = Header(
                    "Basic Info",
                    Icon.VectorIcon(Icons.Rounded.Info)
                ),
                items = listOf(
                    RowInfo(
                        icon = Icon.DrawableIcon(R.drawable.event_filled),
                        text = date.substringBefore("T")
                    ),
                    RowInfo(
                        icon = Icon.DrawableIcon(R.drawable.schedule),
                        text = date.substringAfter("T").substringBeforeLast(":")
                    ),
                    RowInfo(
                        icon = Icon.DrawableIcon(R.drawable.location_on),
                        text = location
                    )
                )
            )
            InfoCard(
                New(
                    text = comments,
                    header = Header(
                        "Comments",
                        Icon.DrawableIcon(R.drawable.comment)
                    )
                )
            )
            if (isAdmin) {
                ListCard(
                    header = Header(
                        "Admin Info",
                        Icon.VectorIcon(Icons.Outlined.Star)
                    ),
                    items = listOf(
                        RowInfo(
                            icon = Icon.DrawableIcon(R.drawable.groups),
                            text = "${event.confirmed} members will attend",
                            action = Action.Icon.Vector(
                                icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                action = navigateToMembers
                            )
                        ),
                        RowInfo(
                            icon = Icon.DrawableIcon(R.drawable.list_bulleted),
                            text = if (rollCallRealized) "Check the attendance"
                            else "You haven't taken attendance yet.",
                            action = Action.Icon.Vector(
                                icon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                action = navigateToRollCall
                            )
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}