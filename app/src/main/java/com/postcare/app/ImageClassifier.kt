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

    // Labels binaires
    private val labels = listOf("Non cancer", "Cancer")

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
        val inputShape = interpreter.getInputTensor(0).shape() // [1, 224, 224, 1]
        val inputWidth = inputShape[2]
        val inputHeight = inputShape[1]

        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, inputWidth, inputHeight, true)
        val byteBuffer = ByteBuffer.allocateDirect(4 * inputWidth * inputHeight) // 1 canal (grayscale)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(inputWidth * inputHeight)
        resizedBitmap.getPixels(intValues, 0, inputWidth, 0, 0, inputWidth, inputHeight)

        var pixel = 0
        for (i in 0 until inputHeight) {
            for (j in 0 until inputWidth) {
                val value = intValues[pixel++]
                val r = (value shr 16 and 0xFF)
                val g = (value shr 8 and 0xFF)
                val b = (value and 0xFF)
                val gray = (0.299f * r + 0.587f * g + 0.114f * b) / 255.0f
                byteBuffer.putFloat(gray)
            }
        }

        byteBuffer.rewind()

        val output = Array(1) { FloatArray(2) } // Binaire : 2 classes
        interpreter.run(byteBuffer, output)
        return output[0]
    }

    fun classifyReadable(bitmap: Bitmap): String {
        val results = classify(bitmap)
        val cancerProbability = results[1]  // index 1 = "Cancer"
        val prediction = if (cancerProbability > 0.5f) "Cancer" else "Non cancer"
        val confidence = cancerProbability * 100
        return "Prédiction : $prediction\nConfiance : ${"%.2f".format(confidence)}%"
    }

    fun getInputImageSize(): Pair<Int, Int> {
        val shape = interpreter.getInputTensor(0).shape()
        return shape[2] to shape[1] // (width, height)
    }
}
