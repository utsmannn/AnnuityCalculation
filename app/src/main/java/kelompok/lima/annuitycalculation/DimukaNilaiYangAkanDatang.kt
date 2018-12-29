/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/23/18 8:42 PM
 */

package kelompok.lima.annuitycalculation

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_input.*

class DimukaNilaiYangAkanDatang : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title_anuitas.text = "Anuitas Di Muka Nilai Yang Akan Datang"
        initSetup()

        btn_hitung.setOnClickListener {
            when (type) {
                "p" -> hitungP()
                "pv" -> hitungFV()
                "n" -> hitungN()
            }
        }
    }

    private fun hitungN() {
        if (!watcherReady(input_pv) && !watcherReady(input_i) && !watcherReady(input_p)) {
            val fvValue = valueEdit(input_pv)
            val iValue = valueEdit(input_i)
            val pValue = valueEdit(input_p)

            val i = (iValue / iBaseValue) / 100

            val step1 = fvValue * i
            val step2 = pValue * (1+i)
            val step3 = step1/step2
            val step4 = - Math.log(1+step3)
            val step5 = - Math.log(1+i)
            val step6 = (step4/step5)

            val limitFinal = getLimitValue(step6)
            editFinal(input_n, layout_input_n, limitFinal)
        } else {
            showSnackbar()
        }
    }

    private fun hitungFV() {
        if (!watcherReady(input_i) && !watcherReady(input_p) && !watcherReady(input_n)) {
            val iValue = valueEdit(input_i)
            val pValue = valueEdit(input_p)
            val nValue = valueEdit(input_n)

            val i = (iValue / iBaseValue) / 100
            val n = nValue * nBaseValue

            val step1 = (Math.pow(1+i, n)-1)
            val step2 = pValue * (step1/i)
            val step3 = step2 * (1+i)

            val limitFinal = getLimitValue(step3)
            editFinal(input_pv, layout_input_pv, limitFinal)
        } else {
            showSnackbar()
        }
    }

    private fun hitungP() {
        if (!watcherReady(input_pv) && !watcherReady(input_i) && !watcherReady(input_n)) {
            val fvValue = valueEdit(input_pv)
            val iValue = valueEdit(input_i)
            val nValue = valueEdit(input_n)

            val i = (iValue / iBaseValue) / 100
            val n = nValue * nBaseValue

            val step1 = Math.pow(1+i, n)
            val step2 = step1 - 1
            val step3 = step2/i
            val step4 = 1+i
            val step5 = step3 * step4
            val step6 = fvValue / step5

            val limitFinal = getLimitValue(step6)
            editFinal(input_p, layout_input_p, limitFinal)
        } else {
            showSnackbar()
        }
    }
}