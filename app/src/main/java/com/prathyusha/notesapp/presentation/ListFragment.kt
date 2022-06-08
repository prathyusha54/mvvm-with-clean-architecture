package com.prathyusha.notesapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prathyusha.notesapp.R
import com.prathyusha.notesapp.framework.NoteListViewModel
import com.prathyusha.notesapp.presentation.adapter.NoteListAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(), NoteListInterface {

    private var progressBar: ProgressBar? = null
    private var rvNotesList: RecyclerView? = null
    private var addNoteBtn: FloatingActionButton? = null
    private val noteListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        rvNotesList = view.findViewById(R.id.rvNotes)
        progressBar = view.findViewById(R.id.progressbar)
        addNoteBtn = view.findViewById(R.id.addNote)

        rvNotesList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        addNoteBtn?.setOnClickListener {
                goToNoteDetailsPage()
        }

        observeVieModel()
    }

    fun observeVieModel() {
        viewModel.notes.observe(viewLifecycleOwner) { noteList ->
            progressBar?.visibility = View.GONE
            rvNotesList?.visibility = View.VISIBLE
            noteListAdapter.updateNotes(noteList.sortedByDescending { it.updatedTime })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    /**
     * To navigate to details screen based on Note ID
     */
    private fun goToNoteDetailsPage(noteId:Long = 0L) {
        val action = ListFragmentDirections.actionListFragmentToNoteFragment(noteId)
        rvNotesList?.let { Navigation.findNavController(it).navigate(action) }
    }

    override fun onClick(id: Long) {
        goToNoteDetailsPage(id)
    }
}