package com.app.gamesuitver2.ui.activity.game

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.app.gamesuitver2.databinding.ActivityGameBinding
import com.app.gamesuitver2.databinding.LayoutCustomDialogBinding
import com.app.gamesuitver2.ui.activity.DashboardActivity
import com.app.gamesuitver2.ui.base.BaseActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class GameActivity : BaseActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var dialogBinding: LayoutCustomDialogBinding
    private lateinit var playerName:String
    private lateinit var typeEnemy:String
    private lateinit var playerChoice:String
    private lateinit var enemyChoice:String
    private lateinit var pemenang:String
    private val choice = arrayListOf("GUNTING","BATU","KERTAS")

    companion object{
        const val NAME="playerName"
        const val ENEMY="typeEnemy"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPictureTitle()
        getIntenData()

        dismissIconClicked()
        refreshIconClicked()

        //openDialogPemenang()

        clearChoice()
        setPlayerChoice()

        // TODO: 19/11/2021 CEK SESI

    }

    private fun clearChoice() {
        binding.ivPlayerBatu.rotation=360F
        binding.ivPlayerBatu.setBackgroundColor(Color.TRANSPARENT)

        binding.ivPlayerKertas.rotation=360F
        binding.ivPlayerKertas.setBackgroundColor(Color.TRANSPARENT)

        binding.ivPlayerGunting.rotation=360F
        binding.ivPlayerGunting.setBackgroundColor(Color.TRANSPARENT)

        binding.ivEnemyBatu.rotation=360F
        binding.ivEnemyBatu.setBackgroundColor(Color.TRANSPARENT)

        binding.ivEnemyKertas.rotation=360F
        binding.ivEnemyKertas.setBackgroundColor(Color.TRANSPARENT)

        binding.ivEnemyGunting.rotation=360F
        binding.ivEnemyGunting.setBackgroundColor(Color.TRANSPARENT)

    }

    private fun setPlayerChoice() {
        binding.ivPlayerBatu.setOnClickListener {
            playerChoice="BATU"
            if (typeEnemy=="Computer"){
                setPlayerChoiced()
            }
            setEnemyChoice()

        }
        binding.ivPlayerGunting.setOnClickListener {
            playerChoice="GUNTING"
            if (typeEnemy=="Computer") {
                setPlayerChoiced()
            }
            setEnemyChoice()

        }
        binding.ivPlayerKertas.setOnClickListener {
            playerChoice="KERTAS"
            if (typeEnemy=="Computer") {
                setPlayerChoiced()
            }
            setEnemyChoice()
        }
    }

    private fun setEnemyChoice() {
        if (typeEnemy=="Computer"){
            enemyChoice=choice.random()
            when (enemyChoice){
                "GUNTING"->
                {
                    setEnemyGuntingChoiced()
                }
                "BATU"->
                {
                    setEnemyBatuChoiced()
                }
                "KERTAS"->
                {
                    setEnemyKertasChoiced()
                }
            }
            cekPemenang()
            openDialogPemenang()

        }else{
            val snackbar2= Snackbar.make(binding.root, "$playerName telah memilih. " +
                    "\n Silahkan $typeEnemy untuk memilih",
                Snackbar.LENGTH_INDEFINITE)
            snackbar2.setAction("Close"){
                snackbar2.dismiss()
            }
            snackbar2.show()
            binding.ivEnemyBatu.setOnClickListener {
                enemyChoice="BATU"
                setEnemyBatuChoiced()
                setPlayerChoiced()
                cekPemenang()
                openDialogPemenang()
            }
            binding.ivEnemyGunting.setOnClickListener {
                enemyChoice="GUNTING"
                setEnemyGuntingChoiced()
                setPlayerChoiced()
                cekPemenang()
                openDialogPemenang()
            }
            binding.ivEnemyKertas.setOnClickListener {
                enemyChoice="KERTAS"
                setEnemyKertasChoiced()
                setPlayerChoiced()
                cekPemenang()
                openDialogPemenang()
            }
        }
    }

    private fun cekPemenang() {
        when(playerChoice){
            "GUNTING"-> {
                when (enemyChoice) {
                    "GUNTING" -> pemenang = "SERI"
                    "KERTAS" -> pemenang = playerName
                    "BATU" -> pemenang = typeEnemy
                }
            }
            "KERTAS" ->{
                when(enemyChoice){
                    "GUNTING"-> pemenang=typeEnemy
                    "KERTAS" -> pemenang="SERI"
                    "BATU" -> pemenang = playerName
                }
            }
            "BATU"->{
                when(enemyChoice){
                    "GUNTING"-> pemenang = playerName
                    "KERTAS" -> pemenang = typeEnemy
                    "BATU" -> pemenang = "SERI"
                }
            }
        }
    }

    //method untuk merotasi gambar yang dipilih (player)
    private fun setPlayerChoiced() {
        when(playerChoice){
            "GUNTING"->setPlayerGuntingChoiced()
            "BATU"->setPlayerBatuChoiced()
            "KERTAS"->setPlayerKertasChoiced()
        }
    }

    private fun setPlayerKertasChoiced() {
        binding.ivPlayerKertas.rotation=30F
        binding.ivPlayerKertas.setBackgroundColor(Color.BLUE)
    }

    private fun setPlayerGuntingChoiced() {
        binding.ivPlayerGunting.rotation=30F
        binding.ivPlayerGunting.setBackgroundColor(Color.BLUE)
    }

    private fun setPlayerBatuChoiced() {
        binding.ivPlayerBatu.rotation=30F
        binding.ivPlayerBatu.setBackgroundColor(Color.BLUE)
    }

    //method untuk merotasi gambar yang dipilih (enemy)
    private fun setEnemyKertasChoiced() {
        binding.ivEnemyKertas.rotation=30F
        binding.ivEnemyKertas.setBackgroundColor(Color.RED)
        showPlayerChoice()
    }

    private fun setEnemyGuntingChoiced() {
        binding.ivEnemyGunting.rotation=30F
        binding.ivEnemyGunting.setBackgroundColor(Color.RED)
        showPlayerChoice()
    }

    private fun setEnemyBatuChoiced() {
        binding.ivEnemyBatu.rotation=30F
        binding.ivEnemyBatu.setBackgroundColor(Color.RED)
        showPlayerChoice()
    }

    private fun showPlayerChoice() {
        Toast.makeText(this, "$playerName memilih $playerChoice\n " +
                "$typeEnemy memilih $enemyChoice",Toast.LENGTH_SHORT).show()
    }

    private fun setPictureTitle() {
        this.let {
            Glide.with(it)
                .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
                .into(binding.ivTitle)
        }
        Log.d("setPictureTitle","setPictureTitle berhasil")
    }

    private fun getIntenData() {
        playerName = intent.getStringExtra(NAME).toString()
        typeEnemy = intent.getStringExtra(ENEMY).toString()
        binding.tvPlayerName.text=playerName
        binding.tvEnemyName.text=typeEnemy
        Log.d("getIntenData","getIntentData berhasil")
    }

    private fun dismissIconClicked() {
        binding.ivDismiss.setOnClickListener {
            finish()
            exitProcess(-1)
        }
    }

    private fun refreshIconClicked() {
        binding.ivRefresh.setOnClickListener {
            clearChoice()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun openDialogPemenang() {
        dialogBinding = LayoutCustomDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .apply {
                setView(dialogBinding.root)
            }
            .create()

        if (pemenang=="SERI"){
            dialogBinding.tvMenang.isVisible=false
            dialogBinding.tvPemenang.text="SERI"
        }else{
            dialogBinding.tvMenang.isVisible=true
            dialogBinding.tvPemenang.text=pemenang
        }
        dialogBinding.btnMainLagi.setOnClickListener{
            dialog.dismiss()
            clearChoice()
        }
        dialogBinding.btnMenu.setOnClickListener{
            val intent= Intent(this, DashboardActivity::class.java)
            intent.putExtra(DashboardActivity.NAME,playerName)
            startActivity(intent)
        }
        dialog.show()
    }
}