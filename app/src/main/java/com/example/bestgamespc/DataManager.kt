package com.example.bestgamespc

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataManager(context: Context) {

    //This is the actual database
    private val db: SQLiteDatabase

    init {
        //Create an instance of our internal
        //CustomSQLiteOpenHelper class
        val helper = CustomSQLiteOpenHelper(context)
        //Get a writable database
        db = helper.writableDatabase
    }

    companion object {

        //const strings for games
        const val GAME_ID = "_id"
        const val GAME_TITLE = "title"
        const val GAME_CATEGORY = "category"
        const val GAME_HARDWARE_REQUIREMENTS = "requirements"
        const val GAME_PRICE = "price"
        const val GAME_DOWNLOAD_LINK = "link"
        const val GAME_IMAGE = "image"

        //const strings for physical stores
        const val PHYSICAL_STORE_ID = "_id"
        const val PHYSICAL_STORE_NAME = "name"
        const val PHYSICAL_STORE_ADDRESS = "address"
        const val PHYSICAL_STORE_PHONE = "phone"

        //const strings for online stores
        const val ONLINE_STORE_ID = "_id"
        const val ONLINE_STORE_NAME = "name"
        const val ONLINE_STORE_LINK = "link"

        /*
        Next, we have a private const strings for
        each table that we need to refer to just
        inside this class
         */

        private const val DB_NAME = "best_pc_games_db"
        private const val DB_VERSION = 1
        private const val TABLE_G = "games"
        private const val TABLE_P = "stores_physical"
        private const val TABLE_O = "stores_online"

    }

    //Insert a game
    private fun insertGame(title: String, category: String, hardwareRequirements: String,
                   price: String, link: String, image: String) {

        val query = "INSERT INTO $TABLE_G ($GAME_TITLE, $GAME_CATEGORY, " +
                "$GAME_HARDWARE_REQUIREMENTS, $GAME_PRICE, $GAME_DOWNLOAD_LINK, $GAME_IMAGE) " +
                "VALUES ('$title', '$category', '$hardwareRequirements', '$price', '$link', " +
                "'$image');"

        Log.i("insert() = ", query)

        db.execSQL(query)
    }

    //Insert a physical store
    private fun insertPhysicalStore(name: String, address: String, phone: String) {

        val query = "INSERT INTO $TABLE_P ($PHYSICAL_STORE_NAME, $PHYSICAL_STORE_ADDRESS, " +
                "$PHYSICAL_STORE_PHONE) VALUES ('$name', '$address', '$phone');"

        Log.i("insert() = ", query)

        db.execSQL(query)
    }

    //Insert an online store
    private fun insertOnlineStore(name: String, link: String) {

        val query = "INSERT INTO $TABLE_O ($ONLINE_STORE_NAME, $ONLINE_STORE_LINK) " +
                "VALUES ('$name', '$link');"

        Log.i("insert() = ", query)

        db.execSQL(query)
    }

    //Returns total number of game records
    private fun gameCount(): Int {

        val query = "SELECT * FROM $TABLE_G"
        val cursor = db.rawQuery(query, null)
        return cursor.count

    }

    //Returns total number of physical store records
    private fun physicalStoreCount(): Int {

        val query = "SELECT * FROM $TABLE_P"
        val cursor = db.rawQuery(query, null)
        return cursor.count

    }

    //Returns total number of online store records
    private fun onlineStoreCount(): Int {

        val query = "SELECT * FROM $TABLE_O"
        val cursor = db.rawQuery(query, null)
        return cursor.count

    }

    //If game table empty, populate
    fun initializeGames() {
        if (gameCount() == 0) {
            insertGame("Apex Legends", "FPS Battle Royale", "OS: " +
                    "64-bit Windows 7\nCPU: Intel Core i3-6300 3.8GHz\nRAM: 6GB\nHDD Space: 22GB",
                "FREE", "https://www.origin.com/usa/en-us/store/apex/apex",
                "apex_legends")
            insertGame("Grand Theft Auto 5", "Action-Adventure", "OS: " +
                    "64-bit Windows 7\nCPU: Intel Core 2 Quad CPU Q6600 2.40GHz\nRAM: 4GB\nHDD Space: 65GB",
                "29.99", "https://store.steampowered.com/app/271590/Grand_Theft_Auto_V/",
                "gta_5")
            insertGame("Detroit: Become Human", "Adventure", "OS: " +
                    "64-bit Windows 7\nCPU: Intel Core i5-2300 2.8 GHz\nRAM: 8GB\nHDD Space: 70GB",
                "39.99", "https://store.steampowered.com/app/1222140/Detroit_Become_Human/",
                "dbh")
            insertGame("Minecraft", "Sandbox, Survival", "OS: " +
                    "64-bit Windows 7\nCPU: Core i3 3210\nRAM: 4GB\nHDD Space: 4GB",
                "26.95", "https://www.minecraft.net/en-us/store/minecraft-java-edition",
                "minecraft")
            insertGame("Need for Speed Payback", "Racing", "OS: " +
                    "64-bit Windows 7\nCPU: Intel i3 6300 @ 3.8GHz or AMD FX 8150 3.6GHz\nRAM: 6GB\nHDD Space: 30GB",
                "19.99", "https://www.origin.com/usa/en-us/store/need-for-speed/need-for-speed-payback",
                "nfs_payback")
        }
    }

    //If physical stores table empty, populate
    fun initializePhysicalStores() {

        if (physicalStoreCount() == 0) {
            insertPhysicalStore("Video Games Plus", "3766 Tittabawassee Rd",
                "(989)-791-1091")
            insertPhysicalStore("GameStop", "5206 Bay Rd", "(989)-799-6600")
            insertPhysicalStore("Game On Saginaw", "3029 Bay Plaza Dr",
                "(989)-401-6407")
            insertPhysicalStore("Media Reload-Saginaw", "3277 Tittabawassee Rd",
                "(989)-249-0685")
        }

    }

    //If online stores table empty, populate
    fun initializeOnlineStores() {

        if (onlineStoreCount() == 0) {
            insertOnlineStore("Steam", "https://store.steampowered.com/")
            insertOnlineStore("Epic Games", "https://www.epicgames.com/store/en-US/")
            insertOnlineStore("Origin", "https://www.origin.com/usa/en-us/store")
            insertOnlineStore("Ubisoft Store", "https://www.ubisoft.com/en-us/games")
            insertOnlineStore("Big Fish Games", "https://www.bigfishgames.com/pc-games/")
        }
    }

    //Get all game records
    fun selectAllGames(): Cursor {
        return db.rawQuery("SELECT * FROM $TABLE_G", null)
    }

    //Get all physical store records
    fun selectAllPhysicalStores(): Cursor {
        return db.rawQuery("SELECT * FROM $TABLE_P", null)
    }

    //Get all online store records
    fun selectAllOnlineStores(): Cursor {
        return db.rawQuery("SELECT * FROM $TABLE_O", null)
    }

    //Find a specific game
    fun searchGame(title: String): Cursor {
        return db.rawQuery("SELECT * FROM $TABLE_G WHERE $GAME_TITLE = '$title'", null)
    }

    //This class is created when our DataManager is initialized
    private inner class CustomSQLiteOpenHelper(
        context: Context)
        : SQLiteOpenHelper(
        context, DB_NAME,
        null, DB_VERSION) {

        //This function only runs the first
        //time the database is created
        override fun onCreate(db: SQLiteDatabase) {

            var newTableQueryString = ("create table $TABLE_G ($GAME_ID integer primary key " +
                    "autoincrement not null, $GAME_TITLE text not null, " +
                    "$GAME_CATEGORY text not null, " +
                    "$GAME_HARDWARE_REQUIREMENTS text not null, " +
                    "$GAME_PRICE text not null, " +
                    "$GAME_DOWNLOAD_LINK text not null, " +
                    "$GAME_IMAGE text not null);")
            Log.i("Table Created", TABLE_G)
            db.execSQL(newTableQueryString)

            newTableQueryString = ("create table $TABLE_P ($PHYSICAL_STORE_ID integer primary key " +
                    "autoincrement not null, $PHYSICAL_STORE_NAME text not null, " +
                    "$PHYSICAL_STORE_ADDRESS text not null, " +
                    "$PHYSICAL_STORE_PHONE text not null);")
            Log.i("Table Created", TABLE_P)
            db.execSQL(newTableQueryString)

            newTableQueryString = ("create table $TABLE_O ($ONLINE_STORE_ID integer primary key " +
                    "autoincrement not null, $ONLINE_STORE_NAME text not null, " +
                    "$ONLINE_STORE_LINK text not null);")
            Log.i("Table Created", TABLE_O)
            db.execSQL(newTableQueryString)

        }


        //This method only runs when we increment DB_VERSION
        //We will look at this in chapter 26
        override fun onUpgrade(db: SQLiteDatabase,
                               oldVersion: Int,
                               newVersion: Int) {


        }

    }
}