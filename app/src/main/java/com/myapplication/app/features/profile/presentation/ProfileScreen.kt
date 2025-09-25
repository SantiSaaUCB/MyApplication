package com.myapplication.app.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    vm: ProfileViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val s = state) {
            is ProfileViewModel.UIState.Loading -> CircularProgressIndicator()
            is ProfileViewModel.UIState.Error -> Text(s.message)
            is ProfileViewModel.UIState.Success -> {
                val p = s.data
                InfoCard("Full name", p.fullName)
                Spacer(Modifier.height(8.dp))
                InfoCard("Age", p.age.toString())
                Spacer(Modifier.height(8.dp))
                InfoCard("ID card", p.idCard)
                Spacer(Modifier.height(8.dp))
                InfoCard("Gender", p.gender)
                Spacer(Modifier.height(8.dp))
                InfoCard("Phone", p.phoneNumber)
                Spacer(Modifier.height(8.dp))
                InfoCard("University", p.university)
                Spacer(Modifier.height(8.dp))
                InfoCard("Marital status", p.maritalStatus)
            }
        }
    }
}

@Composable
private fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(4.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge)
        }
    }
}
