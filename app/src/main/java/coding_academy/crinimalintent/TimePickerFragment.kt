package coding_academy.crinimalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.text.DateFormat
import java.util.*
private const val DIALOG_TIME  = "Dialogtime"
class TimePickerFragment: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val time1 = arguments?.getCharSequence(DIALOG_TIME)
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val time = TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> },
            hour,
            minute,
            false
        )
        return time
    }
    companion object {
        fun newInstance(time:CharSequence): TimePickerFragment {
            val args = Bundle().apply {
                putCharSequence(DIALOG_TIME, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }


}



