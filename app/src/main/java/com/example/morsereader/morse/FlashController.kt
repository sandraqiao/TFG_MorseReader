package com.example.morsereader.morse

import android.content.Context
import android.hardware.camera2.CameraManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlashController(context: Context) {
    private var isLocked = false
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private var currentJob: Job? = null
    val cameraId = cameraManager.cameraIdList.firstOrNull { id ->
        cameraManager.getCameraCharacteristics(id)
            .get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
    } ?: throw Exception("No hay cámara con flash")
    private val scope = CoroutineScope(Dispatchers.IO)
    var pause = 200L

    fun emitMorse(word: String) {
        if (isLocked) return
        currentJob?.cancel()
        currentJob = scope.launch {
            isLocked = true
            _isPlaying.value = true
            for (char in word) {
                if (!isActive) break
                when (char) {
                    '.' -> punto()
                    '-' -> raya()
                    ' ' -> delay(pause * 3)
                    '/' -> delay(pause * 7)
                }
            }
            _isPlaying.value = false
            isLocked = false
            flashOff()
        }
    }

    fun stop() {
        currentJob?.cancel()
        currentJob = null
        isLocked = false
        _isPlaying.value = false
        flashOff()
    }

    private suspend fun punto() {
        flashOn()
        delay(pause)
        flashOff()
        delay(pause)
    }
    private suspend fun raya() {
        flashOn()
        delay(pause * 3)
        flashOff()
        delay(pause)
    }

    private fun flashOn() {
        cameraManager.setTorchMode(cameraId, true)
    }
    private fun flashOff() {
        cameraManager.setTorchMode(cameraId, false)
    }
}