/*
 * Created by Muhammad Utsman on 24/12/2018
 * Copyright (c) 2018 . All rights reserved.
 * Last modified 12/24/18 9:34 PM
 */

package kelompok.lima.annuitycalculation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.info_app.*
import kotlinx.android.synthetic.main.pengertian_view.*
import ru.noties.markwon.Markwon

class InfoApp : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_app)


        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.pengertian_view)

        val pengertian = "## Anuitas\n" +
                "Anuitas dalam teori keuangan adalah suatu rangkaian penerimaan atau pembayaran tetap yang dilakukan secara berkala pada jangka waktu tertentu.  \n" +
                "  \n" +
                "Anuitas biasa (ordinary annuity) adalah anuitas yang pembayaran atau penerimaannya terjadi pada akhir periode.  \n" +
                "  \n" +
                "Penggunaan Nilai Sekarang  \n" +
                "1. Menghitung besarnya cicilan perbulan Kredit Kepemilikan Rumah (KPR)  \n" +
                "2. Cicilan utang sewa guna usaha (Leasing)  \n" +
                "3. Tingkat bunga efektif suatu pinjaman  \n" +
                "4. Lama periode waktu yang diperlukan  \n" +
                "5. Saldo pinjaman pada suatu saat tertentu  \n" +
                "  \n" +
                "Anuitas dimuka (due annuity) adalah anuitas yang pembayaran atau penerimaannya dilakukan di awal periode."

        Markwon.setMarkdown(dialog.pengertian_text, pengertian)

        annuity_pengertian.setOnClickListener {
            dialog.show()
        }

        try {
            val version = applicationContext
                    .packageManager
                    .getPackageInfo(applicationContext.packageName, 0)
                    .versionName
            app_version.text = "App versi $version"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            app_version.text = "App versi 1.0.1"
        }

    }
}