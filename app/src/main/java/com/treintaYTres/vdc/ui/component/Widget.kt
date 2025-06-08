package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.model.Action
import com.treintaYTres.vdc.ui.model.Icon
import com.treintaYTres.vdc.ui.model.Icon.DrawableIcon
import com.treintaYTres.vdc.ui.model.Icon.VectorIcon
import com.treintaYTres.vdc.ui.model.people.Instrument
import com.treintaYTres.vdc.ui.theme.VdcTheme
import com.treintaYTres.vdc.ui.theme.textThin

@Composable
fun PendingConfirm(
    onClick: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CheckButton {onClick(true)}
        CrossButton {onClick(false)}
    }
}

@Composable
fun PositiveConfirmation(
    onClick: (Boolean) -> Unit
) {
    AssistChip(
        title = "Iré",
        icon = Icons.Rounded.Check,
        chipColors = SuggestionChipDefaults.suggestionChipColors(
            labelColor = Color.Green,
            containerColor = Color.Transparent,
            iconContentColor = Color.Green
        ),
        borderStroke = BorderStroke(2.dp, Color.Green)
    ) {
        onClick(true)
    }
}

@Composable
fun NegativeConfirmation(
    onClick: (Boolean) -> Unit
) {
    AssistChip(
        title = "No Iré",
        icon = Icons.Rounded.Close,
        chipColors = SuggestionChipDefaults.suggestionChipColors(
            labelColor = Color.Red,
            containerColor = Color.Transparent,
            iconContentColor = Color.Red
        ),
        borderStroke = BorderStroke(2.dp, Color.Red)
    ) {
        onClick(false)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ConfirmationChipPrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            PositiveConfirmation {

            }
            NegativeConfirmation {

            }
        }

    }
}

@Composable
fun AssistanceAmount(
    confirmedAssistance: Int,
    cancellationAssistance: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = "$confirmedAssistance", style = MaterialTheme.typography.bodyLarge)
        CheckButton(
            modifier = Modifier.clickable(false) {},
            size = 32
        ) {}
        Text(text = "$cancellationAssistance", style = MaterialTheme.typography.bodyLarge)
        CrossButton(
            modifier = Modifier.clickable(false) {},
            size = 32
        ) {}
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AssistanceAmountPrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            AssistanceAmount(10, 1)
        }

    }
}

@Composable
fun UserInfo(
    name: String,
    instrument: Instrument?
) {
    if (instrument == null) return
    
    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(instrument.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(16.dp)
            )
            Text(text = instrument.name, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun UserInfoSheet(
    name: String,
    instrument: Instrument?
) {
    if (instrument == null) return

    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.titleLarge)
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(instrument.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(32.dp)
            )
            Text(text = instrument.name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UserInfoPrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            UserInfo(
                "Sergio Doblado Muñoz",
                Instrument(
                    0,
                    "Clarinet",
                    ""
                )
            )
        }
    }
}

@Composable
fun SimpleItem(
    icon: Icon,
    text: String,
    modifier: Modifier = Modifier,
    gap: Int = 16,
    iconSize: Int = 24,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (icon) {
            is VectorIcon -> {
                Icon(
                    imageVector = icon.resource,
                    contentDescription = icon.contentDescription,
                    modifier = Modifier.size(iconSize.dp)
                )
            }

            is DrawableIcon -> {
                Icon(
                    painter = painterResource(icon.resource),
                    contentDescription = icon.contentDescription,
                    modifier = Modifier.size(iconSize.dp),
                    tint = color
                )
            }
        }
        Text(
            text = text,
            style = textStyle,
            color = color
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SimpleItemPrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SimpleItem(DrawableIcon(R.drawable.banda_avatar), "Aún no has pasado lista", color = Color.Unspecified)
        }
    }
}

@Composable
fun SimpleItemClickable(
    icon: Icon,
    text: String,
    action: Action.Icon,
    modifier: Modifier = Modifier,
    gap: Int = 16,
    iconSize: Int = 24,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = LocalContentColor.current,
) {
    Row(
        modifier = modifier.clickable {
            action.action()
        }.padding(end = gap.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(gap.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (icon) {
                is VectorIcon -> {
                    Icon(
                        imageVector = icon.resource,
                        contentDescription = icon.contentDescription,
                        modifier = Modifier.size(iconSize.dp),
                        tint = color
                    )
                }

                is DrawableIcon -> {
                    Icon(
                        painter = painterResource(icon.resource),
                        contentDescription = icon.contentDescription,
                        modifier = Modifier.size(iconSize.dp),
                        tint = color
                    )
                }
            }
            Text(
                text = text,
                style = textStyle,
                color = color
            )
        }
        when (action) {
            is Action.Icon.Vector -> {
                Icon(
                    imageVector = action.icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp)
                )
            }

            is Action.Icon.Drawable -> {
                Icon(
                    painter = painterResource(action.icon),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp)
                )
            }
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SimpleItemClickablePrev() {
    VdcTheme {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            SimpleItemClickable(
                VectorIcon(Icons.Rounded.Lock),
                "Aún no has pasado lista",
                Action.Icon.Vector(
                    Icons.AutoMirrored.Rounded.KeyboardArrowRight
                ) {}
            )
        }
    }
}

@Composable
fun HeaderItem(
    icon: Icon,
    text: String,
) {
    SimpleItem(
        icon = icon,
        text = text,
        modifier = Modifier.padding(bottom = 4.dp),
        gap = 8,
        textStyle = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}
@Composable
fun EventFinished() {
    Icon(
        painter = painterResource(R.drawable.hourglass),
        contentDescription = null
    )
    Text(
        text = "Event finished",
        style = MaterialTheme.typography.titleMedium,
        color = textThin
    )
}