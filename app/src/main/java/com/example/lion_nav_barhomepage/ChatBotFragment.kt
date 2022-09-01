package com.example.lion_nav_barhomepage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepalace.chatbot.utils.BotResponse
import com.codepalace.chatbot.utils.Constants
import com.codepalace.chatbot.utils.Time
import com.example.lion_nav_barhomepage.databinding.FragmentChatBotBinding
import com.example.lion_nav_barhomepage.databinding.FragmentHelpBinding
import kotlinx.coroutines.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatBotFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatBotFragment : Fragment() {
    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!
    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Lion", "Happiness", "Vision", "Cheery")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBotBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Lions Assistant"
        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! ${patient_main_data.name.toString()} Today you're speaking with ${botList[random]}, how may I help?")
        super.onViewCreated(view, savedInstanceState)
    }
    private fun clickEvents() {

        //Send a message
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(this.context)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, Constants.SEND_ID, timeStamp))
            binding.etMessage.setText("")

            adapter.insertMessage(Message(message, Constants.SEND_ID, timeStamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, Constants.RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, Constants.RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response) {
                    Constants.OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    Constants.OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, Constants.RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, Constants.RECEIVE_ID, timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}