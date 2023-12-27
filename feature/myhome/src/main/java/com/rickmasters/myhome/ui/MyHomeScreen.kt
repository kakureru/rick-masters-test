package com.rickmasters.myhome.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rickmasters.cameras.navigation.CamerasNavHost
import com.rickmasters.doors.navigation.DoorsNavHost
import com.rickmasters.myhome.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun MyHomeScreen(
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabItems = remember {
        listOf(
            MyHomeTab.Cameras,
            MyHomeTab.Doors,
        )
    }
    val pagerState = rememberPagerState(1) { tabItems.size }

    Scaffold(
        modifier = modifier,
        topBar = {
            Text(
                text = stringResource(id = R.string.my_home),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) {
                tabItems.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = stringResource(id = tabItem.titleResId),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    )
                }
            }
            when (tabItems[pagerState.currentPage]) {
                MyHomeTab.Cameras -> CamerasNavHost()
                MyHomeTab.Doors -> DoorsNavHost()
            }
//            HorizontalPager(state = pagerState) { index ->
//                when (tabItems[index]) {
//                    MyHomeTab.Cameras -> CamerasNavHost()
//                    MyHomeTab.Doors -> DoorsNavHost()
//                }
//            }
        }
    }
}