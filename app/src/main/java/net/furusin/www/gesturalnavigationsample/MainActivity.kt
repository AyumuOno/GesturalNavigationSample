package net.furusin.www.gesturalnavigationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // フルスクリーンに対応する
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val members = mutableListOf<String>()
        (0..100).forEach { members.add("furusin $it") }

        findViewById<ListView>(R.id.listView).also { listView ->
            listView.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1, members
            )
        }
    }
}
