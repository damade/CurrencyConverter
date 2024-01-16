package com.damilola.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.damilola.cache.model.RemoteKeyType
import com.damilola.cache.model.RemoteKeys
@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCarerRemoteKeys(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE repoId = :id AND remoteKeyType = :keyType")
    suspend fun remoteKeyById(keyType: RemoteKeyType, id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE remoteKeyType = :keyType")
    suspend fun clearCarerRemoteKeys(keyType: RemoteKeyType)
}