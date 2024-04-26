package com.example.inspectionapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inspectionapplication.model.inspection.Question
import com.example.inspectionapplication.R
import com.example.inspectionapplication.api.RetrofitInstance
import com.example.inspectionapplication.databinding.FragmentQuestionsBinding
import com.example.inspectionapplication.model.inspection.AnswerChoice
import com.example.inspectionapplication.model.inspection.Inspection
import com.example.inspectionapplication.model.repository.InspectionRepository
import com.example.inspectionapplication.viewmodel.InspectionViewModel
import com.example.inspectionapplication.viewmodel.ViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: InspectionViewModel
    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var questionList : List<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)

        setupListener()
        setupSpinner()
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        binding.recyclerQuestions.layoutManager = LinearLayoutManager(requireContext())
//        questionAdapter = QuestionAdapter(requireContext(),questionList)
//        binding.recyclerQuestions.adapter = questionAdapter
//
//        val repository = InspectionRepository(RetrofitInstance.api)
//        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(InspectionViewModel::class.java)
//
//        // Observe the LiveData from ViewModel
//        viewModel.fetchQuestions { posts ->
//            questionAdapter = QuestionAdapter(requireContext(),questionList)
//        }
//

        // Handle back button press
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun setupRecyclerView(){
          questionList = listOf(
            Question(name = "Is the drugs trolley locked?", id = 1,selectedAnswerChoiceId = 1,
            answerChoices = listOf(AnswerChoice(
                id= 1,
                name = "Yes",
                score = 1.0
            ), AnswerChoice(
                    id= 2,
                    name = "No",
                    score = 0.0)
            )),

            Question(name = "Are the drawers locked?", id = 2,selectedAnswerChoiceId = 2,
                answerChoices = listOf(AnswerChoice(
                    id= 1,
                    name = "Yes",
                    score = 1.0
                ), AnswerChoice(
                    id= 2,
                    name = "No",
                    score = 0.0)
                )),

            Question(name = "How often is the floor cleaned?", id = 3,selectedAnswerChoiceId = 2,
            answerChoices = listOf(AnswerChoice(
                id= 1,
                name = "Everyday",
                score = 1.0
            ), AnswerChoice(
                id= 2,
                name = "Every two day",
                score = 0.5),
                AnswerChoice(
                    id= 3,
                    name = "Every week",
                    score = 0.0
                ))),


            Question(name = "How often is the floor cleaned?", id = 4,selectedAnswerChoiceId = 1,
                answerChoices = listOf(AnswerChoice(
                    id= 1,
                    name = "Everyday",
                    score = 1.0
                ), AnswerChoice(
                    id= 2,
                    name = "Every two day",
                    score = 0.5),
                    AnswerChoice(
                        id= 3,
                        name = "Every week",
                        score = 0.0
                    )))

        )
        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())

        // pass it to rvLists layoutManager
        binding.recyclerQuestions.setLayoutManager(layoutManager)

        questionAdapter = QuestionAdapter(requireContext(), questionList )
        binding.recyclerQuestions.adapter = questionAdapter
    }

    private fun setupSpinner(){

        // access the items of the InspectionType list
        val inspectionType = resources.getStringArray(R.array.InspectionType)

        // access the spinner
        if (binding.spinnerInspectionType != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, inspectionType)
            binding.spinnerInspectionType.adapter = adapter

            binding.spinnerInspectionType.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(requireContext(),
//                        getString(R.string.selected_item) + " " +
//                                "" + inspectionType[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


        // access the items of the Area list
        val area = resources.getStringArray(R.array.Areas)

        // access the spinner
        if (binding.spinnerArea != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, area)
            binding.spinnerArea.adapter = adapter

            binding.spinnerArea.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(requireContext(),
//                        getString(R.string.selected_item) + " " +
//                                "" + inspectionType[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        // access the items of the Area list
        val category = resources.getStringArray(R.array.CategoryNames)

        // access the spinner
        if (binding.spinnerCategory != null) {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, category)
            binding.spinnerCategory.adapter = adapter

            binding.spinnerCategory.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(requireContext(),
//                        getString(R.string.selected_item) + " " +
//                                "" + inspectionType[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


    }

    private fun setupListener(){
        binding.btnSave.setOnClickListener {
            Toast.makeText(requireContext(),"Data Saved Successfully", Toast.LENGTH_SHORT).show()
        }

        binding.btnSubmit.setOnClickListener {
//            viewModel.submitInspection(
//                Inspection(
//
//                )
//            )
            Toast.makeText(requireContext(),"Data Submitted Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}