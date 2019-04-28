package com.nangokuman.sqlitemoduleunittest.repository.git_repo

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(Enclosed::class)
class GitRepositoryLocalDataSourceTest {
    abstract class DBTest {
        //    lateinit var db: AppDatabase
        lateinit var gitRepositoryLocalDataSource: GitRepositoryLocalDataSource

        @Before
        fun setUp() {
            val context = InstrumentationRegistry.getInstrumentation().context
            val db = Room
                .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
            gitRepositoryLocalDataSource = GitRepositoryLocalDataSource(db)
        }

        @After
        @Throws(IOException::class)
        fun tearDown() {
//        db.clearAllTables()
//        db.close()
        }
    }

    @RunWith(RobolectricTestRunner::class)
    class BlankRecord : DBTest() {
        @Test
        fun insertAll_successfully_parent_record() {
            var hoge = "hoge"
            gitRepositoryLocalDataSource.insertAll(
                GitRepository(0, "hello","hello", hoge)
            )
            var hogeOwners = gitRepositoryLocalDataSource.findByOwner(hoge)
            assertThat(hogeOwners).hasSize(1)
        }
    }

    @RunWith(RobolectricTestRunner::class)
    class RecordPrepared : DBTest() {
        @Before
        fun setUpChild() {
            val owner = "hoge"
            gitRepositoryLocalDataSource.insertAll(
                GitRepository(1, "hello","hello", owner),
                GitRepository(2, "world", "world", owner),
                GitRepository(3, "yay!", "yay!", "suzuki")
            )
        }

        @Test
        fun findByOwner_givenHoge_returnssizeCount2() {
            var list = gitRepositoryLocalDataSource.findByOwner("hoge")
            Assertions.assertThat(list).hasSize(2)
        }

        @Test
        fun findByOwner_givenSuzuki_returnssizeCount1() {
            var list = gitRepositoryLocalDataSource.findByOwner("suzuki")
            Assertions.assertThat(list).hasSize(1)
        }
    }
}
