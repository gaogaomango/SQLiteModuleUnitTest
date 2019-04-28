package com.nangokuman.sqlitemoduleunittest.repository.git_repo

import androidx.room.*

@Dao
interface GItRepositoryDao {
    @Insert
    fun insertAll(vararg repositories: GitRepository)

    @Query("SELECT * FROM git_repository WHERE owner = :owner")
    fun findByOwner(owner: String): List<GitRepository>
}

@Database(entities = [GitRepository::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gitRepositoryDao(): GItRepositoryDao
}
