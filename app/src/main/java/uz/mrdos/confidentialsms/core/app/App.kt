package uz.mrdos.confidentialsms.core.app

import android.app.Application
import uz.mrdos.confidentialsms.core.cache.AppCache

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCache.init(this)
    }
}