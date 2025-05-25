package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Header
import com.treintaYTres.vdc.ui.model.Icon.DrawableIcon
import com.treintaYTres.vdc.ui.model.New
import com.treintaYTres.vdc.ui.model.RowInfo
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.theme.textThin
import com.treintaYTres.vdc.ui.util.Constant

@Composable
fun EventCard(
    date: String,
    title: String,
    location: String,
    confirmationState: Int,
    onClick: () -> Unit
) {
    val confirmationRowAlignment by remember {
        mutableStateOf(if (confirmationState == 0) Arrangement.SpaceBetween else Arrangement.End)
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.titleMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.location_on),
                        contentDescription = "Location Icon"
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = confirmationRowAlignment
            ) {

                when (confirmationState) {
                    Constant.Event.PENDING_CONFIRMATION -> {
                        Text(
                            text = "Confirm your attendance",
                            style = MaterialTheme.typography.titleMedium,
                            color = textThin
                        )
                        PendingConfirm()
                    }

                    Constant.Event.POSITIVE_CONFIRMATION -> PositiveConfirmation()
                    Constant.Event.NEGATIVE_CONFIRMATION -> NegativeConfirmation()
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun EventCardPrev() {
    VdcTheme {
        EventCard(
            date = "Tomorrow at 1",
            title = "Ensayo",
            location = "Silo de trigo",
            confirmationState = Constant.Event.NEGATIVE_CONFIRMATION
        ) {

        }
    }
}

@Composable
fun InstrumentCard(
    url: String,
    isSelected: Boolean = false,
    isPrimary: Boolean = false,
    onClick: (Boolean) -> Unit
) {

    val color = if (isPrimary) {
        MaterialTheme.colorScheme.inversePrimary
    } else if (isSelected) {
        MaterialTheme.colorScheme.outline
    } else Color.Transparent

    ElevatedCard(onClick = {onClick(isSelected)}) {
        Box(
            modifier = Modifier
                .border(2.dp, color)
                .padding(20.dp)
        ) {
            Image(
                url = url,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ListCard(
    header: Header,
    items: List<RowInfo>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SimpleItem(header.icon, header.title)
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier.height(60.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        with(item) {
                            action?.let {
                                SimpleItemClickable(
                                    icon,
                                    text,
                                    it,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } ?: SimpleItem(icon, text, modifier = Modifier.fillMaxWidth())
                        }

                        if (index < items.size - 1)
                            HorizontalDivider(
                                modifier = Modifier.align(alignment = Alignment.BottomCenter)
                            )
                    }
                }

            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ListCardPreview() {
    val header = Header(
        title = "Opciones",
        icon = DrawableIcon(
            resource = android.R.drawable.ic_menu_info_details,
            contentDescription = "Info"
        )
    )

    val items = listOf(
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_call,
                contentDescription = "Llamar"
            ),
            text = "Llamar",
            action = Action.Icon.Drawable(
                icon = android.R.drawable.ic_menu_call,
                action = { }
            )
        ),
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_camera,
                contentDescription = "Cámara"
            ),
            text = "Abrir cámara"
        ),
        RowInfo(
            icon = DrawableIcon(
                resource = android.R.drawable.ic_menu_compass,
                contentDescription = "Navegar"
            ),
            text = "Navegar",
            action = Action.Icon.Drawable(
                icon = android.R.drawable.ic_menu_compass,
                action = { }
            )
        )
    )
    VdcTheme {
        ListCard(header = header, items = items)
    }
}

@Composable
fun InfoCard(
    new: New
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .requiredHeight(240.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            with(new) {
                header?.let {
                    SimpleItem(
                        icon = it.icon,
                        text = it.title,
                        iconSize = 40,
                        textStyle = MaterialTheme.typography.titleMedium
                    )
                }

                TextBody(text = text)

                date?.let {
                    SimpleItem(
                        icon = DrawableIcon(R.drawable.schedule),
                        text = it,
                        modifier = Modifier.align(Alignment.End),
                        iconSize = 16,
                        textStyle = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
