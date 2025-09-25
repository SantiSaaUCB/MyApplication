package com.myapplication.app.features.dollar.data.datasource

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.myapplication.app.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DollarRealTimeDataSource {

    suspend fun getDollarUpdates(): Flow<DollarModel> = callbackFlow {
        val callback = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(DollarModel::class.java)
                if (value != null) {
                    trySend(value)
                }
            }
        }
        val ref = Firebase.database.getReference("dollar")
        ref.addValueEventListener(callback)
        awaitClose { ref.removeEventListener(callback) }
    }
}
