package com.example.voicy_v2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.DirectoryManager;
import com.example.voicy_v2.model.Logatome;
import com.example.voicy_v2.model.Mot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ListeExoPredefiniActivity extends FonctionnaliteActivity {

    private ListView listView;
    private ArrayAdapter<String > adapter;
    private String[] fileList;
    private View customView;
    private PopupWindow popUp;
    private TextView titrePopUp, textClose, listLogatomes;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_liste_exo_predefini);

        //Ajout du menu sur l'activité
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_liste_exo_predefini, null, false);
        drawerLayout.addView(contentView, 0);

        linearLayout = findViewById(R.id.exo_layout);

        //Inistialiser la vue du popup
        customView = inflater.inflate(R.layout.popup_exo_predefini,null);

        listView = (ListView) findViewById(R.id.list_exo_predefini);



        try {
            // Fonction permettant de récupérer tous les noms des listes predefinis
            fileList = getAssets().list("liste_exo_specefiques");
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fileList);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
               System.out.println(" ********** test liste exo specefiques ************");
                showResultat(fileList[position]);
            }
        });


    }


    private void showResultat(String fichier)
    {

        //Initialisation de la popup
        popUp = new PopupWindow(customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        popUp.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popUp.setElevation(5.0f);
        }

        // Configuration du titre
        titrePopUp = popUp.getContentView().findViewById(R.id.titrePopup);
        titrePopUp.setText(fichier);

        // Configuration du contenu du fichier
        listLogatomes = popUp.getContentView().findViewById(R.id.list_logatomes);
        listLogatomes.setText(getTextFromFile(fichier));
        listLogatomes.setMovementMethod(new ScrollingMovementMethod());

        // Faire apparaitre la popup
        popUp.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popUp.showAtLocation(linearLayout, Gravity.CENTER,0,0);
        popUp.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        textClose = customView.findViewById(R.id.txtClose);
        textClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp.dismiss();
            }
        });
    }


    private String getTextFromFile(String file) {
        String[] res = null;
        String result = "";

        // buffer sur la liste présentes dans assets
        try(BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("liste_exo_specefiques/"+file))))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                line = line.substring(line.indexOf("\t")+1);

                // split de la ligne par rapport à une tabulation
                res = line.split("\t");

                result  += res[0]+"\n";
            }

            return  result;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return result;
        }
    }


}