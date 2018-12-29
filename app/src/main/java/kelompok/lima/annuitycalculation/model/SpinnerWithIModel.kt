/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/8/18 2:10 PM
 */

package kelompok.lima.annuitycalculation.model

data class SpinnerWithIModel(val data: String,
                             val value: Double) {
    override fun toString(): String {
        return data
    }
}