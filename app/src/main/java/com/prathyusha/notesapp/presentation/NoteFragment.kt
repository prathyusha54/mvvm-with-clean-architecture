package com.prathyusha.notesapp.presentation

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prathyusha.core.data.Note
import com.prathyusha.notesapp.R
import com.prathyusha.notesapp.framework.NoteVieModel


/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment() {
    private var noteId = 0L
    private lateinit var viewModel: NoteVieModel
    private var currentNote = Note("", "", 0L, 0L)

    private var checkButton: FloatingActionButton? = null
    private var title: EditText? = null
    private var description: EditText? = null
    private var imgDelete: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkButton = view.findViewById(R.id.checkButton)
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.description)
        imgDelete = view.findViewById(R.id.imgDelete)

        viewModel = ViewModelProviders.of(this).get(NoteVieModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if(noteId != 0L) {
            viewModel.getNote(noteId)
        }
        checkButton?.setOnClickListener {
            if (title.toString() != "" || description.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = title?.text.toString()
                currentNote.content = description?.text.toString()
                currentNote.updatedTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        imgDelete?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(context?.getString(R.string.delete_title))
                .setMessage(context?.getString(R.string.delete_message))
                .setPositiveButton(context?.getString(R.string.yes)) { _, i -> viewModel.deleteNote(currentNote) }
                .setNegativeButton(context?.getString(R.string.cancel)) {_, i -> }
                .create()
                .show()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                hideKeyBoard()
                title?.let { it -> Navigation.findNavController(it).popBackStack() }
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong. Please try again later",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                title?.setText(it.title, TextView.BufferType.EDITABLE)
                description?.setText(it.content, TextView.BufferType.EDITABLE)
            }
        }
    }

    private fun hideKeyBoard() {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
