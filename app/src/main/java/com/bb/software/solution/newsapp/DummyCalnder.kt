import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bb.software.solution.newsapp.ui.theme.AppTheme
@Composable
fun ShiftsScreen() {
    // Simulated data for shifts grouped by month
    val monthlyShifts = listOf(
        MonthlyShifts("July 2024", listOf(
            Shift("1", "2024-07-05", "Day Shift"),
            Shift("2", "2024-07-08", "Night Shift")
            // Add more shifts for July as needed
        )),
        MonthlyShifts("June 2024", listOf(
            Shift("3", "2024-06-28", "Day Shift", completed = true),
            Shift("4", "2024-06-30", "Night Shift", completed = true)
            // Add more shifts for June as needed
        ))
        // Add more months and shifts as needed
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Calendar or month selection UI (dropdown or tabs)
        // For simplicity, let's use a Text as an example
        Text("Select Month", style = MaterialTheme.typography.labelMedium)

        // Monthly shifts list
        monthlyShifts.forEach { monthlyShift ->
            MonthlyShiftsSection(monthlyShift = monthlyShift)
        }
    }
}

@Composable
fun MonthlyShiftsSection(monthlyShift: MonthlyShifts) {
    Column {
        Text(monthlyShift.month, style = MaterialTheme.typography.labelMedium)

        // Upcoming shifts for the month
        ShiftsList(shifts = monthlyShift.upcomingShifts, title = "Upcoming Shifts")

        // Expandable section for previous shifts
        val expanded = remember { mutableStateOf(false) }
        if (monthlyShift.previousShifts.isNotEmpty()) {
            Column {
                Row(
                    modifier = Modifier.clickable { expanded.value = !expanded.value },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Previous Shifts", style = MaterialTheme.typography.labelMedium)
                    Icon(
                        imageVector = if (expanded.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                if (expanded.value) {
                    ShiftsList(shifts = monthlyShift.previousShifts, title = "Previous Shifts")
                }
            }
        }
    }
}

@Composable
fun ShiftsList(shifts: List<Shift>, title: String) {
    Column {
        Text(title, style = MaterialTheme.typography.labelMedium)
        shifts.forEach { shift ->
            ShiftItem(shift = shift)
        }
    }
}

@Composable
fun ShiftItem(shift: Shift) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Date: ${shift.date}")
            Text("Shift Type: ${shift.shiftType}")
            if (shift.completed) {
                Text("Status: Completed")
            } else {
                // Optionally, show action buttons for upcoming shifts
            }
        }
    }
}

data class Shift(
    val id: String,
    val date: String,
    val shiftType: String,
    val completed: Boolean = false
    // Add more fields as needed
)

data class MonthlyShifts(
    val month: String,
    val upcomingShifts: List<Shift>,
    val previousShifts: List<Shift> = emptyList()
    // Add more fields as needed
)

@Preview(showBackground = true)
@Composable
fun ShiftsScreenPreview() {
    AppTheme {
        ShiftsScreen()
    }

}