package com.example.samplemvvmcompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.samplemvvmcompose.view.MainViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val user = viewModel.user.collectAsStateWithLifecycle()

    ConstraintLayout(modifier = modifier.fillMaxSize().padding(20.dp)) {
        val (topBar, main, bottomBar) = createRefs()

        Text(text=user.value.name, modifier = Modifier.constrainAs(topBar) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Text(text=user.value.location, modifier = Modifier.constrainAs(main) {
            top.linkTo(topBar.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(bottomBar.top)
        })

        Text(text=user.value.bio, modifier = Modifier.constrainAs(bottomBar) {
            top.linkTo(main.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        })
    }
}
