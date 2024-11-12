package skosana.avianaapp1.Features.Notifications

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class NotificationViewModel(
    application: Application
): ViewModel() {

    private val workManager = WorkManager.getInstance(application)

    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        SciName: String,
        Name: String

    ){

        val myWorkRequestBuilder = OneTimeWorkRequestBuilder<NotificationsWorker>()
        myWorkRequestBuilder.setInputData(
            workDataOf(
                "NAME" to Name,
                "SCI_NAME" to SciName
            )
        )

        myWorkRequestBuilder.setInitialDelay(duration, unit)
        workManager.enqueue(myWorkRequestBuilder.build())

    }

}