//import android.content.Context
//import androidx.datastore.preferences.core.MutablePreferences
//import androidx.datastore.preferences.core.Preferences
//import androidx.glance.GlanceId
//import androidx.glance.appwidget.GlanceAppWidgetManager
//import androidx.glance.appwidget.state.getAppWidgetState
//import androidx.glance.appwidget.state.updateAppWidgetState
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import androidx.work.workDataOf
//import com.sohil_bhanani.flutter_wid.PlayerWidget
//import com.sohil_bhanani.flutter_wid.PlayerWidgetReciever
//import com.sohil_bhanani.flutter_wid.data.PlayerRepository
//
//class PlayerWidgetWorker(
//    private val repository: PlayerRepository,
//    private val appContext: Context,
//    private val workerParameters: WorkerParameters
//) : CoroutineWorker(appContext, workerParameters) {
//
//    companion object {
//        private const val WIDGET_TITLE_KEY = "widget_title_key"
//        private const val WIDGET_ARTIST_KEY = "widget_artist_key"
//
//        fun buildData(title: String, artist: String) = workDataOf(
//            WIDGET_TITLE_KEY to title,
//            WIDGET_ARTIST_KEY to artist
//        )
//    }
//
//    override suspend fun doWork(): Result{
//        getTitleArtistPair().let { dataPair ->
//            val glanceId = GlanceAppWidgetManager(appContext)
//                .getGlanceIds(PlayerWidget::class.java).firstOrNull { id ->
//                    PlayerWidget().getAppWidgetState<Preferences>(
//                        appContext,
//                        id
//                    ).let { prefs ->
//                        WidgetStateHelper.isStored(
//                            prefs,
//                            title = dataPair.first,
//                            artist = dataPair.second
//                        )
//                    }
//                } ?: return Result.failure()
//            updateWidgetState(glanceId) {
//                WidgetStateHelper.setLoading(it, true)
//            }
//
//            updateWidgetState(glanceId) {
//                WidgetStateHelper.save(
//                    it, repository.getData()
//                )
//            }
//            return Result.success()
//
//        }
//    }
//
//    private suspend fun updateWidgetState(
//        glanceId: GlanceId,
//        update: (MutablePreferences) -> Unit
//    ) {
//        PlayerWidget().apply {
//            updateAppWidgetState(appContext, glanceId) {
//                update(it)
//            }
//            update(appContext, glanceId)
//        }
//    }
//
//    private fun getTitleArtistPair(): Pair<String, String> {
//        return inputData.let {
//            it.getString(WIDGET_TITLE_KEY).orEmpty() to it.getString(WIDGET_ARTIST_KEY).orEmpty()
//        }
//    }
//
//}