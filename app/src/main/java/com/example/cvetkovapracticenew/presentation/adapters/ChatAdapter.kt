package com.example.cvetkovapracticenew.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.data.userToken
import com.example.cvetkovapracticenew.network.models.MessageResponse


class ChatAdapter(val messages: List<MessageResponse>, val userName: String) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) { //SentMessageHolder
        var tvTextMessage: TextView
        var tvTextInfoAboutMessage: TextView
        var ivAvatar: ImageView

        init {
            tvTextMessage = view.findViewById(R.id.tv_textMessage)
            tvTextInfoAboutMessage = view.findViewById(R.id.tv_textInfoAboutMessage)
            ivAvatar = view.findViewById(R.id.iv_avatar)
        }

        fun bindView(message: MessageResponse) {
            tvTextMessage.text = message.text
            tvTextInfoAboutMessage.text =
                "${message.firstName} ${message.lastName} • ${message.creationDateTime}"
            ivAvatar.load("http://cinema.areas.su/up/images/" + message.userAvatar)
        }
    }

    private val VIEW_TYPE_MESSAGE_ME = 1 // мы
    private val VIEW_TYPE_MESSAGE_OTHER = 2 // другой пользователь

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        Log.i("!!!", "${message.firstName} ${message.lastName} --- $userName")

        return if ("${message.firstName} ${message.lastName}" == userName)
            VIEW_TYPE_MESSAGE_ME
        else
            VIEW_TYPE_MESSAGE_OTHER
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val itemView: View = when (viewType) {
            VIEW_TYPE_MESSAGE_ME -> {
                LayoutInflater
                    .from(parent.context)
                    .inflate(
                        R.layout.item_message_me,
                        parent,
                        false
                    )
            }
            VIEW_TYPE_MESSAGE_OTHER -> {
                LayoutInflater
                    .from(parent.context)
                    .inflate(
                        R.layout.item_message_other,
                        parent,
                        false
                    )
            }
            else -> {
                LayoutInflater
                    .from(parent.context)
                    .inflate(
                        R.layout.item_message_other,
                        parent,
                        false
                    )
            }
        }

        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]

        holder.bindView(message)
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}