package ir.jetpack.challenge.view.ui

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import ir.jetpack.challenge.model.dto.Article
import ir.jetpack.challenge.view.ui.theme.GeneralTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(item: Article, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = GeneralTheme.colors.secondBackground,
        tonalElevation = BottomSheetDefaults.SheetPeekHeight,
    ) {
        NewsDetail(item = item)
    }
}