package org.koreader.launcher

import android.util.Log

/* wrapper on top of android.util.Log;
 *
 * Uses the application name as the logger tag.
 * Discards DEBUG messages on release builds.
 */

@Suppress("ConstantConditionIf")
object Logger {
    private val Tag = MainApp.name
    private enum class LogLevel { VERBOSE, DEBUG, INFO, WARNING, ERROR }

    fun e(message: String) {
        doLog(formatMessage(null, message), LogLevel.ERROR)
    }
    fun e(tag: String, message: String) {
        doLog(formatMessage(tag, message), LogLevel.ERROR)
    }

    fun w(message: String) {
        doLog(formatMessage(null, message), LogLevel.WARNING)
    }
    fun w(tag: String, message: String) {
        doLog(formatMessage(tag, message), LogLevel.WARNING)
    }

    fun i(message: String) {
        doLog(formatMessage(null, message), LogLevel.INFO)
    }
    fun i(tag: String, message: String) {
        doLog(formatMessage(tag, message), LogLevel.INFO)
    }

    fun d(message: String) {
        if (MainApp.debuggable) doLog(formatMessage(null, message), LogLevel.DEBUG)
    }
    fun d(tag: String, message: String) {
        if (MainApp.debuggable) doLog(formatMessage(tag, message), LogLevel.DEBUG)
    }

    fun v(message: String) {
        doLog(formatMessage(null, message), LogLevel.VERBOSE)
    }
    fun v(tag: String, message: String) {
        doLog(formatMessage(tag, message), LogLevel.VERBOSE)
    }

    /* format the message, using or not a subtag. */
    private fun formatMessage(subtag: String?, message: String): String {
        return if (subtag != null) "[$subtag] $message" else message
    }

    /* log using application name as the logger tag */
    private fun doLog(message: String, level: LogLevel) {
        when (level) {
            LogLevel.ERROR -> Log.e(Tag, message)
            LogLevel.WARNING -> Log.w(Tag, message)
            LogLevel.INFO -> Log.i(Tag, message)
            LogLevel.DEBUG -> Log.d(Tag, message)
            LogLevel.VERBOSE -> Log.v(Tag, message)
        }
    }
}