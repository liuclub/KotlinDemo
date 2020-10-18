package com.example.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.BaseViewHolder
import com.example.lesson.entity.Lesson

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list = mutableListOf<Lesson>()
    fun updateAndNotify(list: MutableList<Lesson>) {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class LessonViewHolder constructor(itemView: View) : BaseViewHolder(itemView) {
        companion object {
            @JvmStatic
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false))
            }

        }


        fun onBind(lesson: Lesson) {
            val date = lesson.date ?: "日期待定"
            setText(R.id.tv_date, date)
            setText(R.id.tv_content, lesson.content)

            setText(R.id.tv_state, lesson.state.stateName())
            val colorRes = when (lesson.state) {
                Lesson.State.PLAYBACK -> R.color.playback
                Lesson.State.LIVE -> R.color.live
                Lesson.State.WAIT -> R.color.wait
                else -> R.color.playback
            }
            val color = itemView.context.getColor(colorRes)
            getView<TextView>(R.id.tv_state).setBackgroundColor(color)
        }
    }

}