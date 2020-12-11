package com.example.flippingtool.helper.stopwatch

import android.os.Handler
import android.util.Log
import android.widget.TextView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * @author Dzemal at 30/10/2020
 */
class CustomStopwatch {
    private val splits: LinkedList<CustomSplit>
    private var textView: TextView?

    /**
     * Returns the clock time (in milliseconds) when the stopwatch was started
     *
     * @return time when the stopwatch was started in milliseconds.
     * @since 1.0
     */
    var start: Long
        private set
    private var current: Long

    /**
     * Gets the current elapsed time the stopwatch has been running for in milliseconds
     *
     * @return the time in milliseconds the stopwatch has been running for.
     * @since 1.0
     */
    var elapsedTime: Long
        private set
    private var lapTime: Long

    /**
     * Returns true if the stopwatch has started
     *
     * @return true if the stopwatch has been started by calling start(). False otherwise
     * @since 1.0
     */
    var isStarted: Boolean
        private set

    /**
     * Returns true if the stopwatch is paused
     *
     * @return true if the stopwatch is paused. False otherwise
     * @since 1.0
     */
    var isPaused: Boolean
        private set
    private var logEnabled: Boolean
    private var onTickListener: OnTickListener?
    /**
     * Returns the currently set clock delay
     *
     * @return currently set clock delay in milliseconds (default: 100ms)
     * @since 1.0
     */
    /**
     * Set a custom clock delay to increase/decrease update frequency.
     * Clock delay is the delay in between each successive clock update.
     *
     * @param clockDelay clock delay in milliseconds (default : 100ms)
     * @see Thread.sleep
     * @since 1.0
     */
    var clockDelay: Long
    private val handler: Handler

    /**
     * The runnable used to call the thread.
     *
     * @since 1.1
     */
    private val runnable = Runnable { this.run() }

    fun start() {
        if (!isStarted) {
            isStarted = true
            isPaused = false
            start = System.currentTimeMillis()
            current = System.currentTimeMillis()
            lapTime = 0
            elapsedTime = 0
            splits.clear()
            handler.post(runnable)
        }
    }

    /**
     * Pauses the stopwatch. Using this allows you to resume the stopwatch from the current state.
     *
     * @throws IllegalStateException if stopwatch is already paused or not started yet.
     * @see .resume
     * @see .isPaused
     * @since 1.0
     */
    fun pause() {
        if (!isPaused && isStarted) {
            updateElapsed(System.currentTimeMillis())
            isPaused = true
            handler.removeCallbacks(runnable)
        }
    }

    /**
     * Used to resume the stopwatch from the current time after being paused.
     *
     * @throws IllegalStateException if stopwatch is not paused or not started yet.
     * @see .pause
     * @see .isPaused
     * @since 1.0
     */
    fun resume() {
        if (isPaused) {
            check(isStarted) { "Not Started" }
            isPaused = false
            current = System.currentTimeMillis()
            handler.post(runnable)
        }
    }

    /**
     * Updates the time in elapsed and lap time and then updates the current time.
     *
     * @param time Current time in millis. Passing any other value may result in odd behaviour
     * @since 1.1
     */
    private fun updateElapsed(time: Long) {
        elapsedTime += time - current
        lapTime += time - current
        current = time
    }

    /**
     * The main thread responsible for updating and displaying the time
     *
     * @since 1.1
     */
    private fun run() {
        if (!isStarted || isPaused) {
            handler.removeCallbacks(runnable)
            return
        }
        updateElapsed(System.currentTimeMillis())
        handler.postDelayed(runnable, clockDelay)
        if (logEnabled) Log.d(
                "STOPWATCH",
                (elapsedTime / 1000).toString() + " seconds, " + elapsedTime % 1000 + " milliseconds"
        )
        if (onTickListener != null) onTickListener!!.onTick(this)
        if (textView != null) {
            val displayTime = getFormattedTime(elapsedTime)
            textView!!.text = displayTime
        }
    }

    /**
     * Interface to listen for stopwatch tick events every time clock is updated. Useful for scenarios where you want to do more than update a textField based on time.
     * Created by Yashovardhan99 on 10/12/18 as a part of TimeIt.
     *
     * @author Yashovardhan Dhanania
     * @since 1.2
     */
    interface OnTickListener {
        /**
         * Called every time the clock 'ticks'. The stopwatch ticks after a delay of 100ms (or as specified).
         *
         * @param stopwatch Reference to the currently calling stopwatch.
         * @since 1.2
         */
        fun onTick(stopwatch: CustomStopwatch?)
    }

    companion object {
        /**
         * Formats time in either of the following formats depending on time passed - SS.ss, MM:SS.ss, HH:MM:SS.
         *
         * @param elapsedTime time in milliseconds which has to be formatted
         * @return formatted time in String form
         * @since 1.1
         */
        fun getFormattedTime(elapsedTime: Long): String {
            val displayTime = StringBuilder()
            val seconds = (elapsedTime / 1000 % 60).toInt()
            val minutes = (elapsedTime / (60 * 1000) % 60).toInt()
            val hours = (elapsedTime / (60 * 60 * 1000)).toInt()
            val f: NumberFormat = DecimalFormat("00")
            if (hours == 0) displayTime.append(f.format(minutes.toLong())).append(":")
                    .append(f.format(seconds.toLong())) else displayTime.append(hours).append(":")
                    .append(f.format(minutes.toLong())).append(":").append(f.format(seconds.toLong()))
            return displayTime.toString()
        }
    }

    /**
     * The default constructor should be called to create an object to call functions accordingly.
     *
     * @since 1.0
     */
    init {
        start = System.currentTimeMillis()
        current = System.currentTimeMillis()
        elapsedTime = 0
        isStarted = false
        isPaused = false
        logEnabled = false
        splits = LinkedList<CustomSplit>()
        textView = null
        lapTime = 0
        onTickListener = null
        clockDelay = 100
        handler = Handler()
    }
}