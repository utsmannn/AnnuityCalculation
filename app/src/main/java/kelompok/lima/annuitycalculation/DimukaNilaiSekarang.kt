/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/23/18 7:03 PM
 */

package kelompok.lima.annuitycalculation

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_input.*

class DimukaNilaiSekarang : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title_anuitas.text = "Anuitas Di Muka Nilai Sekarang"
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

        if (!watcherReady(input_i) && !watcherReady(input_p) && !watcherReady(input_pv)) {
            val iValue = valueEdit(input_i)
            val pValue = valueEdit(input_p)
            val pvValue = valueEdit(input_pv)

            val i = (iValue / iBaseValue) / 100

            val step1 = pvValue * i
            val step2 = step1/pValue
            val step3 = 1 - step2
            val step4 = Math.log(step3)
            val step5 = Math.log(1+i)
            val step6 = -(step4/step5)
            val step7 = step6 / nBaseValue

            val limitFinal = getLimitValue(step7)
            editFinal(input_n, layout_input_n, limitFinal)
        } else {
            showSnackbar()
        }
    }

    private fun hitungPV() {

        if (!watcherReady(input_i) && !watcherReady(input_n) && !watcherReady(input_p)) {
            val iValue = valueEdit(input_i)
            val nValue = valueEdit(input_n)
            val pValue = valueEdit(input_p)

            val i = (iValue / iBaseValue) / 100
            val n = nValue * nBaseValue

            val step1 = Math.pow(1+i, -n+1)
            val step2 = 1-step1
            val step3 = step2/i
            val step4 = step3 + 1
            val step5 = step4 * pValue

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

            val step1 = Math.pow(1+i, -nValue +1)
            val step2 = 1-step1
            val step3 = step2/i
            val step4 = step3+1
            val step5 = pvValue/step4

            val limitFinal = getLimitValue(step5)
            editFinal(input_p, layout_input_p, limitFinal)
        } else {
            showSnackbar()
        }
    }
}