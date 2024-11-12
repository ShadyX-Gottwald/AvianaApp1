package skosana.avianaapp1.Features.Notifications

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.reactivex.rxjava3.core.Scheduler
import skosana.avianaapp1.MainActivity

class NotificationsWorker(
    private val context: Context,
    workerParams: WorkerParameters
): Worker(context ,workerParams) {

    private val notificationId = 18

    override fun doWork(): Result {
        val intent = Intent(context, MainActivity::class.java).apply {
            // flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val name = inputData.getString(nameKey)
        val SciName = inputData.getString(SciName)

        val body = "Hello,Reminder  $name($SciName). Please Save your Birds ."
        val body2 = "New Notification"
        val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg_low)
            .setContentTitle("Reminder.")
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setContentText(body)
            .build()

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return Result.failure()
            }
            notify(NOTIFICATION_ID, builder)
        }

        return Result.success()
    }

    companion object {
        const val nameKey = "NAME"
        const val SciName = "SCI_NAME"
    }


}