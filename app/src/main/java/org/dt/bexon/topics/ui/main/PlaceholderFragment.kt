package org.dt.bexon.topics.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.dt.bexon.topics.databinding.FragmentMainBinding
import org.dt.bexon.topics.model.Data


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setData(arguments?.getSerializable(ARG_SECTION_TOPIC) as Data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        val recyclerView: RecyclerView = binding.list
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        pageViewModel.topic.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: ${it.id}")
            val adapter = TopicAdapter(it.data)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_TOPIC = "section_topic"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(data: Data): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_SECTION_TOPIC, data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}