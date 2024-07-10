package ru.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HabitFragment : Fragment() {

    private lateinit var habitAdapter: HabitAdapter
    private lateinit var habitRecyclerView: RecyclerView
    private lateinit var habitDatabase: HabitDatabase
    private lateinit var habitDao: HabitDao

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tracker, container, false)
        habitRecyclerView = view.findViewById(R.id.habit_recycler_view)
        habitRecyclerView.layoutManager = LinearLayoutManager(context)
        habitDatabase = HabitDatabase.getDatabase(requireContext())
        habitDao = habitDatabase.habitDao()

        habitDao.getAllHabits().observe(viewLifecycleOwner) { habits ->
            habitAdapter = HabitAdapter(habits)
            habitRecyclerView.adapter = habitAdapter
        }
        return view
    }
}