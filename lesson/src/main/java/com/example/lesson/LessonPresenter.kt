package com.example.lesson

import android.util.Log
import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient
import com.example.core.utils.Utils
import com.example.lesson.entity.Lesson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LessonPresenter(var activity: LessonActivity) {
    companion object {
        const val LESSON_PATH = "lessons"
    }

    private var lessons = mutableListOf<Lesson>()
    private val type: Type = object : TypeToken<ArrayList<Lesson>>() {}.type

    fun fetchData() {
        HttpClient.INSTANCE.get(LESSON_PATH, type, object : EntityCallback<MutableList<Lesson>> {
            override fun onSuccess(lessons: MutableList<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread { activity.showResult(lessons) }
            }

            override fun onFailure(message: String?) {
                Log.i("TAG", "onFailure: message = $message")
                activity.runOnUiThread {
                    activity.showResult(lessons)
                    Utils.toast(message ?: "") }
            }

        })
    }

    fun showPlayback() {
        val playbackLessons = mutableListOf<Lesson>()
        lessons.forEach {
            if (it.state == Lesson.State.PLAYBACK) {
                playbackLessons.add(it)
            }
        }
        activity.showResult(playbackLessons)
    }

}