package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.treintaYTres.vdc.R
import com.treintaYTres.vdc.ui.theme.VdcTheme

@Composable
fun Profile(
    url: String,
    size: Int,
    modifier: Modifier = Modifier,
    isAdministrator: Boolean = false,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(size.dp)
        )
        if (isAdministrator) {
            Icon(
                painter = painterResource(R.drawable.rounded_star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inversePrimary,
            )
        }
    }
}

@Composable
fun ProfileUri(
    uri: Any?,
    size: Int,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = uri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.account_filled),
        modifier = modifier
            .clip(CircleShape)
            .size(size.dp)
    )
}

@Composable
fun Profile(
    icon: Int,
    size: Int,
    modifier: Modifier = Modifier,
    isAdministrator: Boolean = false,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            modifier = Modifier.size(size.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        if (isAdministrator) {
            Icon(
                painter = painterResource(R.drawable.rounded_star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun ProfilePrev() {
    val provider = AsyncImagePreviewHandler {
        ColorImage(Color.DarkGray.toArgb())
    }
    VdcTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides provider) {
            Profile("", 64, isAdministrator = true)
        }
    }
}

@Composable
fun ProfileState(
    url: String,
    size: Int,
    state: Int
) {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .clip(CircleShape)
                .size(size.dp)
        )
        Icon(
            painter = painterResource(
                when (state) {
                    0 -> R.drawable.check_circle
                    1 -> R.drawable.cancel_circle
                    else -> R.drawable.waiting_circle
                }
            ),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun ProfileStatePrev() {
    val provider = AsyncImagePreviewHandler {
        ColorImage(Color.DarkGray.toArgb())
    }
    VdcTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides provider) {
            ProfileState("", 64, 0)
        }
    }
}

