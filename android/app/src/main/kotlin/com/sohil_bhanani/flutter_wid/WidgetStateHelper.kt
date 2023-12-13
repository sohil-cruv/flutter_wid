import androidx.datastore.preferences.core.*
import com.sohil_bhanani.flutter_wid.data.PlayerItem

object WidgetStateHelper {
    fun save(prefs: MutablePreferences, state: PlayerItem) {
        prefs[stringPreferencesKey(widgetTitleKey)] = state.title
        prefs[stringPreferencesKey(widgetArtistKey)] = state.artist
        prefs[stringPreferencesKey(widgetAlbumUrlKey)] = state.albumUrl
        prefs[booleanPreferencesKey(widgetLoadingKey)] = false

    }
    fun saveTitleArtist(prefs: MutablePreferences, title: String, artist: String) {
        prefs[stringPreferencesKey(widgetTitleKey)] = title
        prefs[stringPreferencesKey(widgetArtistKey)] = artist
    }

    fun isStored(prefs: Preferences, title: String, artist: String): Boolean =
        prefs[stringPreferencesKey(widgetTitleKey)] == title && prefs[stringPreferencesKey(
            artist
        )] == artist

    fun setLoading(prefs: MutablePreferences, loading: Boolean) {
        prefs[booleanPreferencesKey(widgetLoadingKey)] = loading
    }

    fun getState(prefs: Preferences): WidgetState {
        val title = prefs[stringPreferencesKey(widgetTitleKey)] ?: ""
        val artist = prefs[stringPreferencesKey(widgetArtistKey)] ?: ""
        val albumUrl = prefs[stringPreferencesKey(widgetAlbumUrlKey)] ?: ""
        val loading = prefs[booleanPreferencesKey(widgetLoadingKey)] ?: false
        return WidgetState(
            data = PlayerItem(
                title = title,
                artist = artist,
                albumUrl = albumUrl,
            ), loading = loading
        )
    }
    val data = "";
    private const val widgetTitleKey = "widgetTitleKey"
    private const val widgetArtistKey = "widgetArtistKey"
    private const val widgetAlbumUrlKey = "widgetAlbumUrlKey"
    private const val widgetLoadingKey = "widgetLoadingKey"

}

data class WidgetState(
    val data: PlayerItem,
    val loading: Boolean,
)