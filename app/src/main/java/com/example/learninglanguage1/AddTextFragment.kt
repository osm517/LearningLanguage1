package com.example.learninglanguage1

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.learninglanguage1.Model.Language
import com.google.android.material.button.MaterialButton
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.util.Locale


class AddTextFragment : Fragment(R.layout.fragment_add_text) {
    private var Et_Language_Origin: EditText? = null
    private var Tv_Language_Destiny: TextView? = null
    private var Btn_Select_Language: MaterialButton? = null
    private var Btn_Language_Selected: MaterialButton? = null
    private var Btn_Translate: MaterialButton? = null
    private var Btn_Save: MaterialButton? = null
    private var progressDialog: ProgressBar? = null
    private var LanguagesArrayList: ArrayList<Language>? = null
    private var language_code_origin = "es"
    private var language_title_origin = "Spanish"
    private var language_code_destiny = "en"
    private var language_title_destiny = "English"
    private var translatorOptions: TranslatorOptions? = null
    private var translator: Translator? = null
    private var Text_language_origin = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBackAddText = requireView().findViewById<Button>(R.id.btnBackAddText)
       btnBackAddText.setOnClickListener {
           findNavController().navigate(R.id.action_addTextFragment_to_addFragment)
        }

        initializeviews(view)
        AvailableLanguages()
        Btn_Select_Language?.setOnClickListener { //Toast.makeText(MainActivity.this, "Select Language", Toast.LENGTH_SHORT).show();
            SelectLanguageOrigin()
        }
        Btn_Language_Selected?.setOnClickListener { //Toast.makeText(MainActivity.this, "Language Selected", Toast.LENGTH_SHORT).show();


            SelectLanguageDestiny()
        }
        Btn_Translate?.setOnClickListener { //Toast.makeText(MainActivity.this, "Translate", Toast.LENGTH_SHORT).show();
            ValidateData()
        }
        Btn_Save?.setOnClickListener {
            val card = Card(0, 0, "Translate", Et_Language_Origin?.text.toString(), Tv_Language_Destiny?.text.toString())
            AppDatabase.getDatabase(this.requireContext()).cardDao().save(card = card)

        }



    }

    private fun initializeviews(v: View) {
        Btn_Select_Language = v.findViewById(R.id.Btn_Select_Language)
        progressDialog?.let { p ->
           // p.setTitle("Wait, please.")
            //p.setCanceledOnTouchOutside(false)
        } ?: also {
            Log.d("App", "nuull")
        }
        Tv_Language_Destiny = v.findViewById(R.id.Tv_Language_Destiny)
        Et_Language_Origin = v.findViewById(R.id.Et_Language_Origin)
        Btn_Select_Language = v.findViewById(R.id.Btn_Select_Language)
        Btn_Language_Selected = v.findViewById(R.id.Btn_Language_Selected)
        Btn_Translate = v.findViewById(R.id.Btn_Translate)
        Btn_Save = v.findViewById(R.id.btnSave)
        //progressDialog = v.findViewById(R.id.)


    }


    private fun AvailableLanguages() {
        LanguagesArrayList = ArrayList()
        val LanguageCodeList = TranslateLanguage.getAllLanguages()
        for (language_code in LanguageCodeList) {
            val language_title = Locale(language_code).displayLanguage

            //Log.d(RECORDS, "AvailableLanguages: language_code: "+ language_code);
            //Log.d(RECORDS, "AvailableLanguages: language_title: "+ language_title);
            val languageModel = Language(language_code, language_title)
            LanguagesArrayList!!.add(languageModel)
        }
    }

    private fun SelectLanguageOrigin() {
        val popupMenu = PopupMenu(context, Btn_Select_Language)
        for (i in LanguagesArrayList!!.indices) {
            popupMenu.menu.add(Menu.NONE, i, i, LanguagesArrayList!![i].language_title)
        }
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            val position = item.itemId
            language_code_origin = LanguagesArrayList!![position].language_code
            language_title_origin = LanguagesArrayList!![position].language_title
            Btn_Select_Language!!.text = language_title_origin
            Et_Language_Origin!!.hint = "Enter text in: $language_title_origin"
            Log.d(RECORDS, "onMenuItemClick: language_code_origin: $language_code_origin")
            Log.d(RECORDS, "onMenuItemClick: language_title_origin: $language_title_origin")
            false
        }
    }
    

    private fun SelectLanguageDestiny() {
        val popupMenu = PopupMenu(context, Btn_Language_Selected)
        for (i in LanguagesArrayList!!.indices) {
            popupMenu.menu.add(Menu.NONE, i, i, LanguagesArrayList!![i].language_title)
        }
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            val position = item.itemId
            language_code_destiny = LanguagesArrayList!![position].language_code
            language_title_destiny = LanguagesArrayList!![position].language_title
            Btn_Language_Selected!!.text = language_title_destiny
            Log.d(RECORDS, "onMenuItemClick: language_code_destiny: $language_code_destiny")
            Log.d(RECORDS, "onMenuItemClick: language_title_destiny: $language_title_destiny")
            false
        }
    }


    private fun ValidateData() {
        Text_language_origin = Et_Language_Origin?.text.toString().trim { it <= ' ' }
        Log.d(RECORDS, "ValidateData: Text_language_origin$Text_language_origin")
        if (Text_language_origin.isEmpty()) {

        } else {
            TranslateText()
        }
    }

    private fun TranslateText() {
        //progressDialog!!.setMessage("Processing")
        //progressDialog!!.show()
        translatorOptions = TranslatorOptions.Builder()
            .setSourceLanguage(language_code_origin)
            .setTargetLanguage(language_code_destiny)
            .build()
        translator = Translation.getClient(translatorOptions!!)
        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        translator?.downloadModelIfNeeded(downloadConditions)
            ?.addOnSuccessListener {
                Log.d(RECORDS, "onSuccess: the package has been downloaded successfully")
               // progressDialog!!.setMessage("translating text")
                translator?.translate(Text_language_origin)
                    ?.addOnSuccessListener { translated_text ->
                       // progressDialog!!.dismiss()
                        Log.d(RECORDS, "onSuccess: translated text$translated_text")
                        Tv_Language_Destiny!!.text = translated_text
                    }?.addOnFailureListener { e ->
                        //progressDialog!!.dismiss()
                        Log.d(RECORDS, "onFailure$e")
                        Toast.makeText(requireActivity(), "" + e, Toast.LENGTH_SHORT).show()
                    }
            }?.addOnFailureListener { e ->
                //progressDialog!!.dismiss()
                Log.d(RECORDS, "onFailure$e")
                Toast.makeText(requireActivity(), "" + e, Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val RECORDS = "My_records"
    }
}

//val btnBackAddText = requireView().findViewById<Button>(R.id.btnBackAddText)
//
//        btnBackAddText.setOnClickListener {
//            findNavController().navigate(R.id.action_addTextFragment_to_addFragment)
//        }  pegar esto dentro del corchete inmediato del onviewcreated