package com.example.daggerhiltmock.model.localRepository

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec

@Database(entities = [ProductDetails::class], version = 3, exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3, spec = AppDatabase.PhoneTableMigration::class)
    ])
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {
    @RenameTable(fromTableName = "phone_details", toTableName = "phoneDetails")
    @RenameColumn(tableName = "phoneDetails", fromColumnName = "newPrice", toColumnName = "price")
    class PhoneTableMigration : AutoMigrationSpec
    abstract fun getPhoneDao() : PhoneDao
}