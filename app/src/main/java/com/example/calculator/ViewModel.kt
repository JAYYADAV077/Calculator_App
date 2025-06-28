package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class ViewModel: ViewModel() {
    private var _lowerText = MutableLiveData("0")
    val LowerButton: LiveData<String> = _lowerText

    private val _upperText = MutableLiveData("")
    val upperButton: LiveData<String> = _upperText

    fun onButtonClick(btn: String) {

        _upperText.value?.let {
            if (btn == "AC") {
                _upperText.value = ""
                _lowerText.value = "0"
                return
            }
            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _upperText.value = it.substring(0, it.length - 1)
                    return
                }
            }
            if (btn == "=") {
                _upperText.value = _lowerText.value
                return

            }
            _upperText.value = it + btn

            try {
                _lowerText.value = result(_upperText.value.toString())

            } catch (_: Exception){}
        }
    }
        fun result(result: String): String {
            val context: Context = Context.enter()
            context.optimizationLevel = -1
            val scriptable: Scriptable = context.initStandardObjects()
            var final = context.evaluateString(scriptable, result, "Javascript", 1, null).toString()
            if (final.endsWith(".0")){
                final = final.replace(".0","")
            }
            return final
        }

}


