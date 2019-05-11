package com.santojon.shop.room.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Used to convert Date fields for Database purposes
 */
class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return (if (date == null) null else date.getTime())?.toLong()
    }
}