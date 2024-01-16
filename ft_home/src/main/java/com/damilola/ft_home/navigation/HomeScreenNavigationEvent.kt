package com.damilola.ft_home.navigation


sealed interface HomeScreenNavigationEvent {
    object OpenHistoryScreen : HomeScreenNavigationEvent
    object OpenComposeHomeScreen : HomeScreenNavigationEvent
    object Hidden : HomeScreenNavigationEvent
}