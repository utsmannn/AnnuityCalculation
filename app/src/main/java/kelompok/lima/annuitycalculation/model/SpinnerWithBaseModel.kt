/*
 * Created by Muhammad Utsman on 23/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/8/18 2:18 PM
 */

package kelompok.lima.annuitycalculation.model

data class SpinnerWithBaseModel(var data: String,
                                var value: String) {
    override fun toString(): String {
        return data
    }
}