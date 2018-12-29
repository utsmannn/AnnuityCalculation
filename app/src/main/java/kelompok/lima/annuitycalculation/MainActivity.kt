/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/23/18 4:07 PM
 */

package kelompok.lima.annuitycalculation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val statusbarParam = fake_statusbar.layoutParams as RelativeLayout.LayoutParams
        statusbarParam.height = getStatusBarHeight()
        statusbarParam.width = RelativeLayout.LayoutParams.MATCH_PARENT
        fake_statusbar.layoutParams = statusbarParam

        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        nilai_sekarang.setOnClickListener {
            startIntent(BiasaNilaiSekarang())
        }

        nilai_yang_akan_datang.setOnClickListener {
            startIntent(BiasaNilaiYangAkanDatang())
        }

        dimuka_nilai_sekarang.setOnClickListener {
            startIntent(DimukaNilaiSekarang())
        }

        dimuka_nilai_yang_akan_datang.setOnClickListener {
            startIntent(DimukaNilaiYangAkanDatang())
        }

        info_app.setOnClickListener {
            startIntent(InfoApp())
        }
    }

    private fun startIntent(activity: AppCompatActivity) {
        startActivity(Intent(this, activity::class.java))
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}