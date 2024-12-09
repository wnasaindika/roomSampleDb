package iyannah.ivy.roomdb.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "personTable")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "secondName")
    val secondName: String,
    @ColumnInfo(name = "telephone")
    val telephone: String
)