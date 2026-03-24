package com.example.morsereader.morse

import android.content.Context
import android.hardware.camera2.CameraManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.text.iterator

class FlashController(context: Context) {

    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraId = cameraManager.cameraIdList.firstOrNull { id ->
        cameraManager.getCameraCharacteristics(id)
            .get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
    } ?: throw Exception("No hay cámara con flash")
    private val scope = CoroutineScope(Dispatchers.IO)
    var pause = 200L

    fun emitMorse(word: String) {
        scope.launch {
            for (char in word) {
                when (char) {
                    '.' -> punto()
                    '-' -> raya()
                    ' ' -> delay(pause * 3) // separación letras
                    '/' -> delay(pause * 7) // separación palabras
                }
            }
        }
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