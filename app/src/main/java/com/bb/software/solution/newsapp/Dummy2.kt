package com.bb.software.solution.newsapp

import android.app.DatePickerDialog
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bb.software.solution.newsapp.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun AvailabilityScreen() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // States for managing availability and leave request
    var selectedDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }
    var isDayShift by remember { mutableStateOf(true) }
    var leaveStartDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }
    var leaveEndDate by remember { mutableStateOf(dateFormat.format(calendar.time)) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header section
        Text("Availability", style = MaterialTheme.typography.labelMedium)

        // Availability input section
        Spacer(modifier = Modifier.height(16.dp))
        Text("Set Your Availability", style = MaterialTheme.typography.labelSmall)

        // Date picker for availability
        Button(onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year, month, dayOfMonth)
                    selectedDate = dateFormat.format(selectedCalendar.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text("Select Date: $selectedDate")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Shift type toggle (Day/Night)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Shift Type: ")
            Switch(
                checked = isDayShift,
                onCheckedChange = { isDayShift = it }
            )
            Text(if (isDayShift) "Day Shift" else "Night Shift")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Annual leave request section
        Text("Request Annual Leave", style = MaterialTheme.typography.labelLarge)

        // Date range picker for leave request
        Row {
            Button(onClick = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val startCalendar = Calendar.getInstance()
                        startCalendar.set(year, month, dayOfMonth)
                        leaveStartDate = dateFormat.format(startCalendar.time)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text("Start Date: $leaveStartDate")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val endCalendar = Calendar.getInstance()
                        endCalendar.set(year, month, dayOfMonth)
                        leaveEndDate = dateFormat.format(endCalendar.time)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text("End Date: $leaveEndDate")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Submit button for leave request
        Button(onClick = {
            // Handle leave request submission logic
        }) {
            Text("Request Leave")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Summary of current availability and leave requests
        Text("Current Availability", style = MaterialTheme.typography.labelMedium)
        // Display current availability and leave requests here
        // Example:
        Text("Date: $selectedDate, Shift: ${if (isDayShift) "Day" else "Night"}")

        // Add logic to fetch and display actual data
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AvailabilityScreenPreview() {
    AppTheme {
        AvailabilityScreen()

    }

}