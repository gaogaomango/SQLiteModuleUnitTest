package com.nangokuman.sqlitemoduleunittest.repository.git_repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "git_repository")
data class GitRepository (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String,
    @ColumnInfo(index = true)
    val owner: String)
