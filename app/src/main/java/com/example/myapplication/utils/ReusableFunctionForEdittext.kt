package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.model.User

object ReusableFunctionForEdittext {

    fun clearAllEdittext(group: ViewGroup) {
        var i = 0
        val count = group.childCount
        while (i < count) {
            val view: View = group.getChildAt(i)
            if (view is ViewGroup) {
                clearAllEdittext(view)
            } else if (view is EditText) {
                view.setText("")
            }
            i++
        }
    }

    fun getAllEditText(group: ViewGroup, textList: ArrayList<EditText>): ArrayList<EditText> {
        var i = 0
        val count = group.childCount
        while (i < count) {
            val view: View = group.getChildAt(i)
            if (view is ViewGroup) {
                getAllEditText(view, textList)
            } else if (view is EditText) {
                textList.add(view)
            }
            i++
        }
        Log.i("lala", textList.size.toString())
        return textList
    }

    fun isTextFullfill(textList: ArrayList<EditText>): Boolean {
        for (textField: EditText in textList) {
            if (textField.text.toString().isEmpty()) {
                return false
            }
        }
        return true
    }

    /*fun EditText.setupClearButtonWithAction() {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_clear else 0
                setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        setOnTouchListener(View.OnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    this.setText("")
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }*/

    fun Context.hideKeyboardInFragment(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}