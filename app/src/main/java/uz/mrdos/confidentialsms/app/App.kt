package uz.mrdos.confidentialsms.app

import android.app.Application
import uz.mrdos.confidentialsms.cache.AppCache

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCache.init(this)
    }
}