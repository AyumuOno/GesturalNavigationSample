package net.furusin.www.gesturalnavigationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.Gravity
import android.view.View
import android.widget.ListView
import androidx.core.view.ViewCompat
import android.widget.ArrayAdapter
import androidx.core.view.updatePadding
import android.view.MotionEvent
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    companion object {
        // X軸最低スワイプ距離
        private const val SWIPE_MIN_DISTANCE = 50
        // X軸最低スワイプスピード
        private const val SWIPE_THRESHOLD_VELOCITY = 200
    }

    lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView()

        // フルスクリーンに対応する
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN


        // 左から右へのスワイプでDrawerLayoutを開く
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(
                event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float
            ): Boolean {
                if (event2.x - event1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(Gravity.LEFT)
                }
                return false
            }
        })
    }

    // ListViewの場合はonTouchEventではなく
    // dispatchTouchEventを使う
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // GestureDetectorにイベントを流す
        gestureDetector.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    private fun initListView() {
        val members = mutableListOf<String>()
        (0..100).forEach { members.add("furusin $it") }

        findViewById<ListView>(R.id.listView).also { listView ->
            // ListViewにデータをセットする
            listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, members)

            // リストの最後にNavigation Barの分のスペースを追加する
            listView.setOnApplyWindowInsetsListener { view, windowInsets ->
                view.updatePadding(bottom = windowInsets.systemWindowInsetBottom)
                windowInsets
            }
        }
    }
}
