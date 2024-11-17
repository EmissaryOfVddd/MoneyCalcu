import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RecTransactionWorker(ctx: Context, workerParams: WorkerParameters): Worker(ctx, workerParams) {
    override fun doWork(): Result {
        // TODO: ИМПЛЕМЕНТАЦИЯ

        return Result.success()
    }
}