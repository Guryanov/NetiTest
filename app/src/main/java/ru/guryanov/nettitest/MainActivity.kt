package ru.guryanov.nettitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.guryanov.nettitest.ui.main.PostsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PostsFragment.newInstance())
                .commitNow()
        }
    }

}
