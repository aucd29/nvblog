package com.example.nvblog.model.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2018. 12. 12. <p/>
 *
 * https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1
 * https://codinginfinite.com/android-room-persistent-rxjava/
 */

@Dao
interface DefaultConfigDao {
    @Query("SELECT * FROM defaultConfig ORDER BY _id DESC LIMIT 10")
    fun search(): Flowable<List<DefaultConfig>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: DefaultConfig): Completable

    @Update
    fun update(data: DefaultConfig): Completable

    @Delete
    fun delete(data: DefaultConfig): Completable

    @Query("DELETE FROM defaultConfig")
    fun deleteAll()
}
