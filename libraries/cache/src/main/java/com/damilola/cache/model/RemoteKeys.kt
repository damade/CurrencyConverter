package com.damilola.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(@PrimaryKey val repoId: String, val remoteKeyType: RemoteKeyType,
                      val prevKey: Int?, val nextKey: Int?)