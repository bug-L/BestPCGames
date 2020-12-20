package com.example.bestgamespc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            : View? {

        val view = inflater.inflate(
            R.layout.recycler_view_games,
            container,
            false)

        //create an instance of DataManager
        val dm = DataManager(requireActivity())

        //get all games as a cursor
        val gamesCursor = dm.selectAllGames()
        //create a mutable list of games
        var gamesList = mutableListOf<Game>()

        //populate mutable list of games using cursor data
        while (gamesCursor.moveToNext()) {

            val game = Game(gamesCursor.getString(1), gamesCursor.getString(2),
                gamesCursor.getString(3), gamesCursor.getString(4),
                gamesCursor.getString(5), gamesCursor.getString(6))
            gamesList.add(game)

        }

        val recycler_view = view.findViewById(R.id.recyclerView) as RecyclerView
        val adapter = GameAdapter(requireActivity(), gamesList)
        val layoutManager = LinearLayoutManager(requireActivity())

        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()

        recycler_view.addItemDecoration(
            DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
        )

        //set the adapter
        recycler_view.adapter = adapter

        return view
    }

}