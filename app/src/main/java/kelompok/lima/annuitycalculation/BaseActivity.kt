/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/23/18 4:40 PM
 */

package kelompok.lima.annuitycalculation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import kelompok.lima.annuitycalculation.model.SpinnerWithBaseModel
import kelompok.lima.annuitycalculation.model.SpinnerWithIModel
import kotlinx.android.synthetic.main.activity_input.*
import java.text.DecimalFormat

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    var iBaseValue: Double = 1.0
    var nBaseValue: Double = 1.0

    lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun watcherReady(editText: EditText?) : Boolean {
        return editText?.text.toString().trim().isEmpty()
    }

    fun initSetup() {
        val listSpinner: MutableList<SpinnerWithBaseModel> = mutableListOf()
        setupSpinnerAnuitas(listSpinner)

        val spinnerBaseAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listSpinner)
        spinner_calculation.adapter = spinnerBaseAdapter

        spinner_calculation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = parent?.getItemAtPosition(position) as SpinnerWithBaseModel
                whenType(value.value)
                type = value.value
            }
        }

        val listSpinnerI: MutableList<SpinnerWithIModel> = mutableListOf()

        listSpinnerI.add(SpinnerWithIModel("Periode dalam bulan", 1.0))
        listSpinnerI.add(SpinnerWithIModel("Periode dalam tahun", 12.0))

        val iSpinnerAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listSpinnerI)
        spinner_range.adapter = iSpinnerAdapter

        spinner_range.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinnerWithModel = parent?.getItemAtPosition(position) as SpinnerWithIModel
                iBaseValue = spinnerWithModel.value
                nBaseValue = spinnerWithModel.value
            }
        }
    }

    private fun setupSpinnerAnuitas(listSpinner: MutableList<SpinnerWithBaseModel>) {
        if (!title_anuitas.text.toString().toLowerCase().contains("datang")) {
            listSpinner.add(SpinnerWithBaseModel("Hitung Jumlah Yang Dibayarkan (p)", "p"))
            listSpinner.add(SpinnerWithBaseModel("Hitung Nilai Sekarang (PV)", "pv"))
            listSpinner.add(SpinnerWithBaseModel("Hitung Jumlah Periode (n)", "n"))
        } else {
            listSpinner.add(SpinnerWithBaseModel("Hitung Jumlah Yang Dibayarkan (p)", "p"))
            listSpinner.add(SpinnerWithBaseModel("Hitung Nilai Yang Akan Datang (FV)", "pv"))
            listSpinner.add(SpinnerWithBaseModel("Hitung Jumlah Periode (n)", "n"))
        }
    }

    private fun whenType(type: String) {
        when (type) {
            "p" -> {
                setHint()
                ifEditDisable(input_p, layout_input_p)
            }
            "pv" -> {
                setHint()
                ifEditDisable(input_pv, layout_input_pv)
            }
            "n" -> {
                setHint()
                ifEditDisable(input_n, layout_input_n)
            }
        }
        input_i.text.clear()
    }

    private fun setHint() {
        layout_input_p.apply {
            boxBackgroundColor = resources.getColor(android.R.color.transparent)
            hint = "Jumlah yang dibayarkan secara periodik (p) / Rp"
        }
        layout_input_pv.apply {
            boxBackgroundColor = resources.getColor(android.R.color.transparent)
            hint = if (!title_anuitas.text.toString().toLowerCase().contains("datang")) {
                "Nilai sekarang (PV) / Rp"
            } else {
                "Nilai yang akan datang (FV) / Rp"
            }
        }
        layout_input_n.apply {
            boxBackgroundColor = resources.getColor(android.R.color.transparent)
            hint = "Jumlah Periode (n)"
        }
        layout_input_i.apply {
            boxBackgroundColor = resources.getColor(android.R.color.transparent)
            hint = "Tingkat Bunga (i) / %"
        }
    }

    private fun ifEditDisable(editText: EditText, layoutInput: TextInputLayout) {
        allEditEnable(input_p, layoutInput)
        allEditEnable(input_pv, layoutInput)
        allEditEnable(input_n, layoutInput)

        editText.apply {
            isEnabled = false
            setBackgroundColor(Color.RED)
        }

        layoutInput.boxBackgroundColor = resources.getColor(R.color.colorPrimary)
    }

    private fun allEditEnable(editText: EditText, layoutInput: TextInputLayout) {
        editText.apply {
            setTextColor(Color.BLACK)
            text.clear()
            isEnabled = true
        }

        layoutInput.visibility = View.VISIBLE
    }

    fun valueEdit(editText: EditText): Double {
        return editText.text.toString().toDouble()
    }

    fun getLimitValue(step: Double) : String {
        return DecimalFormat("###.00").format(step)
    }

    fun editFinal(editText: EditText, layoutInput: TextInputLayout, finalValue: String) {
        editText.apply {
            setTextColor(Color.RED)

            if (finalValue == "NaN") {
                setText("Error")
            } else {
                setText(finalValue)
            }
        }
        layoutInput.boxBackgroundColor = resources.getColor(android.R.color.transparent)
    }

    fun showSnackbar() {
        Snackbar.make(parent_layout, "Salah satu tidak boleh kosong", Snackbar.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}