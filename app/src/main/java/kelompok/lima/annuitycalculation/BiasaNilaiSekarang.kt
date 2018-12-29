/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/23/18 4:31 PM
 */

package kelompok.lima.annuitycalculation

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_input.*

class BiasaNilaiSekarang : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title_anuitas.text = "Anuitas Biasa Nilai Sekarang"
        initSetup()

        btn_hitung.setOnClickListener {
            when (type) {
                "p" -> hitungP()
                "pv" -> hitungPV()
                "n" -> hitungN()
            }
        }
    }

    private fun hitungN() {
        if (!watcherReady(input_p) && !watcherReady(input_i) && !watcherReady(input_pv)) {
            val pValue = valueEdit(input_p)
            val iValue = valueEdit(input_i)
            val pvValue = valueEdit(input_pv)

            val i = (iValue / iBaseValue) / 100

            val step1 = (pvValue * i) / pValue
            val step2 = 1 - step1
            val step3 = Math.log(step2)
            val step4 = Math.log(1 + i)
            val step5 = -(step3 / step4)
            val step6 = step5 / nBaseValue

            val limitFinal = getLimitValue(step6)
            editFinal(input_n, layout_input_n, limitFinal)
        } else {
            showSnackbar()
        }
    }

    private fun hitungPV() {
        if (!watcherReady(input_p) && !watcherReady(input_i) && !watcherReady(input_n)) {
            val pValue = valueEdit(input_p)
            val iValue = valueEdit(input_i)
            val nValue = valueEdit(input_n)

            val i = (iValue / iBaseValue) / 100
            val n = nValue * nBaseValue

            val step1 = 1 + i
            val step2 = Math.pow(step1, -n)
            val step3 = 1 - step2
            val step4 = pValue * step3
            val step5 = step4 / i

            val limitFinal = getLimitValue(step5)
            editFinal(input_pv, layout_input_pv, limitFinal)
        } else {
            showSnackbar()
        }
    }

    private fun hitungP() {
        if (!watcherReady(input_pv) && !watcherReady(input_i) && !watcherReady(input_n)) {
            val pvValue = valueEdit(input_pv)
            val iValue = valueEdit(input_i)
            val nValue = valueEdit(input_n)

            val i = (iValue / iBaseValue) / 100
            val n = nValue * nBaseValue

            val step1 = pvValue * i
            val step2 = 1 + i
            val step3 = Math.pow(step2, -n)
            val step4 = 1 - step3
            val step5 = step1 / step4

            val limitFinal = getLimitValue(step5)
            editFinal(input_p, layout_input_p, limitFinal)
        } else {
            showSnackbar()
        }
    }


}