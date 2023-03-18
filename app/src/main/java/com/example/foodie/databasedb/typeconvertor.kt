package com.example.foodie.databasedb

import androidx.core.graphics.rotationMatrix
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class typeconvertor {
    @TypeConverter
    fun fromanytostring(attributes: Any?):String {
if(attributes == null)
    return ""
        return attributes as String
    }
    @TypeConverter
    fun fromstringtoany(attributes: String?):Any {
        if(attributes == null)
            return ""
        return attributes
    }
}