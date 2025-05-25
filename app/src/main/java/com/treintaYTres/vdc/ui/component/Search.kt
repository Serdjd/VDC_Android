package com.treintaYTres.vdc.ui.component

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarState
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.treintaYTres.vdc.ui.theme.VdcTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    placeholder: String,
    modifier: Modifier = Modifier,
    results: @Composable (() -> Unit)
) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()

    val inputField = InputField(placeholder, searchBarState, textFieldState, scope)

    SearchBar(
        state = searchBarState,
        inputField = { inputField },
        modifier = modifier
    )
    ExpandedFullScreenSearchBar(
        state = searchBarState,
        inputField = { inputField }
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    placeholder: String,
    searchBarState: SearchBarState,
    textFieldState: TextFieldState,
    scope: CoroutineScope
) {
    SearchBarDefaults.InputField(
        searchBarState = searchBarState,
        textFieldState = textFieldState,
        onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
        placeholder = { Text(placeholder) },
        leadingIcon = {
            if (searchBarState.currentValue == SearchBarValue.Expanded) {
                IconButton(onClick = { scope.launch { searchBarState.animateToCollapsed() } }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                }
            } else {
                Icon(Icons.Rounded.Search, contentDescription = "Search")
            }
        }
    )
}


@Preview
@Composable
fun SearchPrev() {
    VdcTheme {
        Search("Find a Instrument") {}
    }
}