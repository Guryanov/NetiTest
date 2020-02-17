package ru.guryanov.nettitest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.list.*
import ru.guryanov.nettitest.NettiApplication
import ru.guryanov.nettitest.R
import ru.guryanov.nettitest.data.ViewModelFactory
import ru.guryanov.nettitest.data.entity.Post

class PostsFragment : Fragment() {

    companion object {
        fun newInstance() = PostsFragment()
    }


    private val viewModel by viewModels<PostsViewModel> {
        val repository = (requireContext().applicationContext as NettiApplication).taskRepository
        ViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        viewModel.items.observe(viewLifecycleOwner, Observer<MutableList<Post>> { data ->
            data?.let {
                initAdapter(it)
            }
        })
        viewModel.loadError.observe(viewLifecycleOwner, Observer<Boolean> {
            Toast.makeText(requireContext(), R.string.error_load, Toast.LENGTH_LONG).show()
        })
    }


    private fun initAdapter(data: MutableList<Post>) {
        list.adapter = PostsAdapter(data)
        list.setHasFixedSize(true)
    }


}
