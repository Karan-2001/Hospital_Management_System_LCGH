package com.example.lion_nav_barhomepage

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.codepalace.chatbot.utils.Constants.RECEIVE_ID
import com.codepalace.chatbot.utils.Constants.SEND_ID


class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {

                //Remove message on the item clicked
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
        val message : TextView = itemView.findViewById(R.id.tv_message)
        val botmessage : TextView = itemView.findViewById(R.id.tv_bot_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.botmessage.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.botmessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.message.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}