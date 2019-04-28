package com.nangokuman.sqlitemoduleunittest.repository.git_repo

class GitRepositoryLocalDataSource(private val db: AppDatabase) {
    fun insertAll(vararg repositories: GitRepository) {
        db.gitRepositoryDao().insertAll(*repositories)
    }

    fun findByOwner(owner: String) : List<GitRepository> {
        return db.gitRepositoryDao().findByOwner(owner)
    }
}