package com.example.bestgamespc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        title = "Best PC Games"

        //create an instance of DataManager
        val dm = DataManager(this)

        //populate tables
        dm.initializeGames()
        dm.initializePhysicalStores()
        dm.initializeOnlineStores()

        //Load List of games on startup by
        //simulating a menu item click
        val menu = nav_view.menu
        val menuItemGame = menu.getItem(0)
        this.onNavigationItemSelected(menuItemGame)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle action bar item clicks here. the action bar will
        //automatically handle clicks on the Home/Up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //Create a transaction
        val transaction = supportFragmentManager.beginTransaction()

        //Handle navigation view item clicks here
        when (item.itemId) {

            R.id.nav_games -> {
                val fragment = GameListFragment()
                transaction.replace(R.id.fragmentHolder, fragment)
            }

            R.id.nav_online_stores -> {
                val fragment = OnlineStoresFragment()
                transaction.replace(R.id.fragmentHolder, fragment)
            }

            R.id.nav_physical_stores -> {
                val fragment = PhysicalStoresFragment()
                transaction.replace(R.id.fragmentHolder, fragment)
            }

            R.id.nav_amazon -> {
                val myIntent = Intent(this, AmazonActivity::class.java)
                this.startActivity(myIntent)
            }
        }

        //Ask android to remember which
        //menu options the user has chosen
        transaction.addToBackStack(null)

        //Implement the change
        transaction.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }



}