package com.nangokuman.sqlitemoduleunittest.repository.git_repo

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class GitRepositoryLocalDataSourceTest {
    lateinit var gitRepositoryLocalDataSource: GitRepositoryLocalDataSource
    lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room
            .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        gitRepositoryLocalDataSource = GitRepositoryLocalDataSource(db)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun insertAll() {
    }

    @Test
    fun findByOwner_inserted_list_check() {
        var list = gitRepositoryLocalDataSource.findByOwner("hoge")
        Assertions.assertThat(list).isEmpty()

        val owner = "hoge"
        gitRepositoryLocalDataSource.insertAll(
            GitRepository(1, "hello","hello", owner),
            GitRepository(2, "world", "world", owner)
        )
        list = gitRepositoryLocalDataSource.findByOwner("hoge")
        Assertions.assertThat(list).hasSize(2)
    }

    @Test
    fun getDb() {
    }
}