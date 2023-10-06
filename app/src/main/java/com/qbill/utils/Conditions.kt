package com.qbill.utils

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.qbill.R
import java.util.regex.Pattern


object Conditions {
    /**
     * for check null case
     */
    fun checkNull(text: String): Boolean {
        return !(text.trim() == "" || text.trim() == null || text.trim() == "null" || text.trim()
            .isEmpty())
    }


    /**
     * for check null case for integer
     */
    fun checkIntNull(text: Int): Boolean {
        return !(text == 0 || text == null)
    }


    /**
     * for check null long case
     */
    fun checkDoubleNull(text: Double): Boolean {
        return !(text == 0.0 || text == null || text == 0.00)
    }

    /**
     * for hide the view
     */
    fun View.gone() {
        visibility = View.GONE
    }

    /**
     * for invisible the view
     */
    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    /**
     * for show the view
     */
    fun View.show() {
        visibility = View.VISIBLE
    }

    /***
     * manage progress on image
     */
    fun manageProgressListener(progressBar: ProgressBar): RequestListener<Drawable> {
        val listener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.gone()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.gone()
                return false
            }

        }
        return listener
    }

    /**
     * for avoid double click
     */
    fun View.avoidDoubleClick() {
        isEnabled = false
        postDelayed(
            { isEnabled = true },
            800
        )
    }

    /**
     * for manage click using one function
     */
    fun View.setOnClick(function: (View) -> Unit) {
        val action = View.OnClickListener { view ->
            avoidDoubleClick()
            startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
            function(view)
        }
        setOnClickListener(action)
    }

    /**
     * for manage click using one function
     */
    fun View.setOnNormalClick(function: (View) -> Unit) {
        val action = View.OnClickListener { view ->
            function(view)
        }
        setOnClickListener(action)
    }

    /**
     * for manage click using one function
     */
    fun View.setOnWithoutAvoidDoubleClick(function: (View) -> Unit) {
        val action = View.OnClickListener { view ->
            startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
            function(view)
        }
        setOnClickListener(action)
    }

    /**
     * for request focus on edit text
     */
    fun EditText.setRequestFocus(): Boolean {
        var requestFocus = false
        setOnFocusChangeListener { view, b ->
            if (b) {
                requestFocus = true
            }
        }
        return requestFocus
    }

    /**
     *  Snack Bar
     **/
    fun View.snackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * this is for email validation
     *
     */
    fun isValidEmail(s: String): Boolean {
        val emailPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(s)

        return !matcher.matches()
    }


    /**
     * for set error condition
     */
    fun TextInputLayout.setErrorCondition(errorMessage: String = "", value: Boolean = false) {
        if (value) {
            isErrorEnabled = true
            error = errorMessage
            showKeyBoard()
            requestFocus()
        } else {
            isErrorEnabled = false
            error = ""
        }
    }

    /***
     * for show keyboard
     */
    fun View.showKeyBoard() {
        val inputMethodManager: InputMethodManager? =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.toggleSoftInputFromWindow(
            applicationWindowToken,
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    /**
     * Method use to hide keyboard.
     *
     * @param ctx context of current activity.
     */

    fun Activity.hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    /**
     *
     * password Visibility and Invisibility
     *
     */

    fun Activity.passwordVisibilityAndInvisibility(
        et: EditText,
        iv: ImageView,
        isVisible: Boolean
    ) {

        if (isVisible) {
            et.transformationMethod =
                HideReturnsTransformationMethod.getInstance()
            iv.setImageResource(R.drawable.ic_eye_hover)
            et.setSelection(et.text.toString().length)
        } else {
            et.transformationMethod = PasswordTransformationMethod.getInstance()
            iv.setImageResource(R.drawable.ic_eye_close)
            et.setSelection(et.text.toString().length)
        }
    }

    /**
     * for action listener on edit text
     */
    fun EditText.setOnEditSearch(context: Activity, action: () -> Unit) {
        val listener = TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                action()
                context.hideKeyboard()
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false

        }
        return setOnEditorActionListener(listener)
    }

    /**
     * for set intent data after checking null
     */
    fun Context.setStringIntentData(intent: Intent, key: String): String? {
        return if (intent.hasExtra(key)) {
            if (checkNull(intent.getStringExtra(key).toString())) {
                intent.getStringExtra(key)
            } else {
                ""
            }
        } else {
            ""
        }
    }

    /**
     * for set intent data after checking 0
     */
    fun Context.setIntIntentData(intent: Intent, key: String): Int? {
        return if (intent.hasExtra(key)) {
            if (checkIntNull(intent.getIntExtra(key, 0))) {
                intent.getIntExtra(key, 0)
            } else {
                0
            }
        } else {
            0
        }
    }

    /**
     * for set intent data after checking 0
     */
    fun Context.setDoubleIntentData(intent: Intent, key: String): Double {
        return if (intent.hasExtra(key)) {
            if (checkDoubleNull(intent.getDoubleExtra(key, 0.0))) {
                intent.getDoubleExtra(key, 0.0)
            } else {
                0.0
            }
        } else {
            0.0
        }
    }

    /***
     * set drag offset from x and y
     */
    fun DrawerLayout.dragSet(view: View) {
        addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val diffScaledOffset: Float = slideOffset * (1 - 0.7f)
                val offsetScale = 1 - diffScaledOffset
                view.scaleX = offsetScale
                view.scaleY = offsetScale
                val xOffset: Float = drawerView.width * slideOffset
                val xOffsetDiff: Float = view.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                view.translationX = xTranslation
            }

            override fun onDrawerClosed(drawerView: View) {
            }
        }
        )
    }


}