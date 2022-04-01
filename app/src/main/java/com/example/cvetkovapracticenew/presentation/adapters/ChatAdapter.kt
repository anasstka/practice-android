package com.example.cvetkovapracticenew.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cvetkovapracticenew.R
import com.example.cvetkovapracticenew.network.models.MessageResponse

// адаптер для заполнение списка сообщениями
class ChatAdapter(
    private val messages: List<MessageResponse>,
    private val userName: String
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    // viewholder для инициализации полей элемента списка и заполнение их данными
    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
            // загрузка аватарки пользователя
            ivAvatar.load("http://cinema.areas.su/up/images/" + message.userAvatar)
        }
    }

    // переменные, определяющие чье сообщение выводится - текущего пользователя
    // или другого
    private val VIEW_TYPE_MESSAGE_ME = 1 // мы
    private val VIEW_TYPE_MESSAGE_OTHER = 2 // другой пользователь

    // получение типа пользователя
    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        // сравнение сообщения с текущим пользователем
        return if ("${message.firstName} ${message.lastName}" == userName)
            VIEW_TYPE_MESSAGE_ME
        else
            VIEW_TYPE_MESSAGE_OTHER
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        // выбор layout ресурса в зависимости от типа сообщения
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

    // заполнение viewholder данными
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = messages[position]

        holder.bindView(message)
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}