package com.postcare.app

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class ImageClassifier(private val context: Context) {
    private val interpreter: Interpreter by lazy { loadModel() }

    private fun loadModel(): Interpreter {
        val assetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        return Interpreter(modelBuffer)
    }

    fun classify(bitmap: Bitmap): FloatArray {
        val inputShape = interpreter.getInputTensor(0).shape()
        val inputWidth = if (inputShape.size >= 2) inputShape[inputShape.size - 3] else inputShape[0]
        val inputHeight = if (inputShape.size >= 2) inputShape[inputShape.size - 2] else inputShape[0]

        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, inputWidth, inputHeight, true)
        val byteBuffer = ByteBuffer.allocateDirect(4 * inputWidth * inputHeight * 3)
            .order(ByteOrder.nativeOrder())

        val intValues = IntArray(inputWidth * inputHeight)
        resizedBitmap.getPixels(intValues, 0, inputWidth, 0, 0, inputWidth, inputHeight)
        var pixel = 0
        for (i in 0 until inputHeight) {
            for (j in 0 until inputWidth) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value shr 8 and 0xFF) / 255.0f))
                byteBuffer.putFloat(((value and 0xFF) / 255.0f))
            }
        }

        byteBuffer.rewind()

        val outputSize = interpreter.getOutputTensor(0).numElements()
        val output = Array(1) { FloatArray(outputSize) }
        interpreter.run(byteBuffer, output)
        return output[0]
    }
}
