package com.example.lesson

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {
    private val lessonPresenter = LessonPresenter(this)
    private lateinit var refreshLayout: SwipeRefreshLayout
    private val lessonAdapter = LessonAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        findViewById<Toolbar>(R.id.toolbar).also {
            it.inflateMenu(R.menu.menu_lesson)
            it.setOnMenuItemClickListener(this)
        }

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = this@LessonActivity.lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayoutManager.VERTICAL))
        }
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).also {
            it.setOnRefreshListener {
                lessonPresenter.fetchData()
            }
            it.isRefreshing = true
        }

        lessonPresenter.fetchData()
    }

    fun showResult(lessons: MutableList<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        refreshLayout.isRefreshing = false
    }

    override val presenter: LessonPresenter
        get() = LessonPresenter(this)

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        lessonPresenter.showPlayback()
        return false
    }


}