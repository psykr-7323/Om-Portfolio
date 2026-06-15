package org.psyduck.portfolio.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.psyduck.portfolio.data.ProjectInfo

class PortfolioViewModel : ViewModel() {

    var selectedProject: ProjectInfo? by mutableStateOf(null)
        private set

    var isTerminalAnimationDone: Boolean by mutableStateOf(false)
        private set

    fun selectProject(project: ProjectInfo) {
        selectedProject = project
    }

    fun goHome() {
        selectedProject = null
    }

    fun onTerminalAnimationDone() {
        isTerminalAnimationDone = true
    }
}
