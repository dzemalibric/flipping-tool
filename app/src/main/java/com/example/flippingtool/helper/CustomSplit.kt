package com.example.flippingtool.helper

/**
 * @author Dzemal at 30/10/2020
 */
class CustomSplit
/**
 * Constructor to create a Split object.
 * @param splitTime the time in milliseconds for which stopwatch has been running
 * @param lapTime the time in milliseconds since the last split/lap
 * @since 1.0
 */(
    /** Gets the split time in milliseconds
     * @return the time in milliseconds since the stopwatch was running at the instant this split was created.
     * @since 1.0
     */
    val splitTime: Long,
    /**
     * Gets the lap time in milliseconds
     * @return the time in milliseconds between this and the last split/lap
     * @since 1.0
     */
    val lapTime: Long
)