package org.cryptimeleon.incentive.app

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.onNavDestinationSelected
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load mcl
        System.loadLibrary("mcljava")

        // For logging
        Timber.uprootAll() // Ensure there is only one tree to avoid duplicate logs
        Timber.plant(Timber.DebugTree())
    }

}