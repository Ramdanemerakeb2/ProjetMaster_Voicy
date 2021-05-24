package com.example.voicy_v2.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voicy_v2.R;
import com.example.voicy_v2.model.Exercice;
import com.example.voicy_v2.model.ExerciceLogatome;
import com.example.voicy_v2.model.Patient;
import com.example.voicy_v2.model.VoicyDbHelper;

import org.w3c.dom.Text;

import cn.pedant.SweetAlert.SweetAlertDialog;



public class CreationExoSpecifiqueLogatomActivity  extends FonctionnaliteActivity {
    Exercice exercice ;
    public static VoicyDbHelper dbPatientExo;
    public static String suiteFiltre = "" ;
    public static String maListePhonem = "" ;
    public static String serieFiltre = "" ;
    public static  String[] CVCV = new String[4]; ;
    public static  String[] CCVCCV = new String[6]; ;
    public static  String[] CVCCV = new String[5]; ;
    public static  String[] CCVCV = new String[5]; ;
    public static String[] tabformat = null ;
    public static int monFromat = 0 ;
    public static boolean visible = false ;
//    public static RelativeLayout rl = null ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_creation_exo_specifique_logatom, null, false);
        drawerLayout.addView(contentView, 0);

        dbPatientExo = new VoicyDbHelper(this);
        final TextView format = findViewById(R.id.format);
        final RelativeLayout rlCVCV = findViewById(R.id.filtrageCVCV);
        final TextView firstC1 = findViewById(R.id.C1);
        final TextView firstV1 = findViewById(R.id.V1);
        final TextView firstC2 = findViewById(R.id.C2);
        final TextView firstV2 = findViewById(R.id.V2);

        final RelativeLayout rlCCVCCV = findViewById(R.id.filtrageCCVCCV);
        final TextView secondC1 = findViewById(R.id.secondC1);
        final TextView secondC2 = findViewById(R.id.secondC2);
        final TextView secondV1 = findViewById(R.id.secondV1);
        final TextView secondC3 = findViewById(R.id.secondC3);
        final TextView secondC4 = findViewById(R.id.secondC4);
        final TextView secondV2 = findViewById(R.id.secondV2);

        final RelativeLayout rlCVCCV = findViewById(R.id.filtrageCVCCV);
        final TextView thirdC1 = findViewById(R.id.thirdC1);
        final TextView thirdV1 = findViewById(R.id.thirdV1);
        final TextView thirdC2 = findViewById(R.id.thirdC2);
        final TextView thirdC3 = findViewById(R.id.thirdC3);
        final TextView thirdV2 = findViewById(R.id.thirdV2);

        final RelativeLayout rlCCVCV = findViewById(R.id.filtrageCCVCV);
        final TextView fourthC1 = findViewById(R.id.fourthC1);
        final TextView fourthC2 = findViewById(R.id.fourthC2);
        final TextView fourthV1 = findViewById(R.id.fourthV1);
        final TextView fourthC3 = findViewById(R.id.fourthC3);
        final TextView fourthV2 = findViewById(R.id.fourthV2);




        final TextView TextIdentifiantExo = findViewById(R.id.idExo);
        final TextView filtrePhonem = findViewById(R.id.phonems);
        final TextView filtrePhonemSerie = findViewById(R.id.phonems2);
        final TextView TextIteration = findViewById(R.id.textIteration);
        final TextView lesPhonems = findViewById(R.id.phonemeFilter);
        final TextView showFiltrageAvance = findViewById(R.id.showFiltrageAvance);
        TextView btnAjout = (TextView) findViewById(R.id.btnAjout);
        final Spinner genre = findViewById(R.id.spinner_genre);
        Intent i = getIntent();
        final String idPatient = (String) i.getStringExtra("idPatient");
        final TextView tv = findViewById(R.id.tv);

        for(int a = 0 ; a<CVCV.length;a++){
            CVCV[a] = "!" ;
        }

        for(int a = 0 ; a<CCVCCV.length;a++){
            CCVCCV[a] = "!" ;
        }
        for(int a = 0 ; a<CVCCV.length;a++){
            CVCCV[a] = "!" ;
        }
        for(int a = 0 ; a<CCVCV.length;a++){
            CCVCV[a] = "!" ;
        }


        rlCVCV.setVisibility(View.GONE);
        rlCCVCCV.setVisibility(View.GONE);
        rlCVCCV.setVisibility(View.GONE);
        rlCCVCV.setVisibility(View.GONE);
        showFiltrageAvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!visible) {
                    PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, showFiltrageAvance);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.popup_format_filtre, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            //format.setText(item.getTitle());
                            if (item.getTitle().toString().contains("C_C_V_C_V")){System.out.println("format"+item.getTitle());
                                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"Format qui passe  " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                monFromat = 4 ;
                                System.out.println("format int "+monFromat);
                                rlCCVCV.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.GONE);
                                lesPhonems.setVisibility(View.GONE);
                                filtrePhonem.setVisibility(View.GONE);
                                filtrePhonemSerie.setVisibility(View.GONE);
                                showFiltrageAvance.setText("Filtrage par phonems");
                                visible=true;
                                System.out.println("visible  "+visible);
                            }
                            else if (item.getTitle().toString().contains("C_C_V_C_C_V")){System.out.println("format"+item.getTitle());
                                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"Format qui passe  " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                monFromat = 2 ;
                                rlCCVCCV.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.GONE);
                                lesPhonems.setVisibility(View.GONE);
                                filtrePhonem.setVisibility(View.GONE);
                                filtrePhonemSerie.setVisibility(View.GONE);
                                showFiltrageAvance.setText("Filtrage par phonems");
                                visible=true;
                            }
                            else if (item.getTitle().toString().contains("C_V_C_C_V")){System.out.println("format"+item.getTitle());
                                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"Format qui passe  " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                monFromat = 3 ;
                                System.out.println("format int "+monFromat);
                                rlCVCCV.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.GONE);
                                lesPhonems.setVisibility(View.GONE);
                                filtrePhonem.setVisibility(View.GONE);
                                filtrePhonemSerie.setVisibility(View.GONE);
                                showFiltrageAvance.setText("Filtrage par phonems");
                                visible=true;
                                System.out.println("visible  "+visible);
                            }
                            else if (item.getTitle().toString().contains("C_V_C_V")){System.out.println("format"+item.getTitle());
                                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"Format qui passe  " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                monFromat = 1 ;
                                rlCVCV.setVisibility(View.VISIBLE);
                                tv.setVisibility(View.GONE);
                                lesPhonems.setVisibility(View.GONE);
                                filtrePhonem.setVisibility(View.GONE);
                                filtrePhonemSerie.setVisibility(View.GONE);
                                showFiltrageAvance.setText("Filtrage par phonems");
                                visible=true;
                            }

                            return true;
                        }
                    });

                    popup.show();
                   /* rl.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.GONE);
                    lesPhonems.setVisibility(View.GONE);
                    filtrePhonem.setVisibility(View.GONE);
                    filtrePhonemSerie.setVisibility(View.GONE);
                    showFiltrageAvance.setText("Filtrage par phonems");
                    visible=true;*/
                }else{
                    if (monFromat ==1){
                        rlCVCV.setVisibility(View.GONE);
                        tv.setVisibility(View.VISIBLE);
                        lesPhonems.setVisibility(View.VISIBLE);
                        filtrePhonem.setVisibility(View.VISIBLE);
                        filtrePhonemSerie.setVisibility(View.VISIBLE);
                        showFiltrageAvance.setText("Filtrage par format");
                        visible=false;
                    }else if (monFromat ==2){
                        rlCCVCCV.setVisibility(View.GONE);
                        tv.setVisibility(View.VISIBLE);
                        lesPhonems.setVisibility(View.VISIBLE);
                        filtrePhonem.setVisibility(View.VISIBLE);
                        filtrePhonemSerie.setVisibility(View.VISIBLE);
                        showFiltrageAvance.setText("Filtrage par format");
                        visible=false;
                    }else if (monFromat ==3){
                        rlCVCCV.setVisibility(View.GONE);
                        tv.setVisibility(View.VISIBLE);
                        lesPhonems.setVisibility(View.VISIBLE);
                        filtrePhonem.setVisibility(View.VISIBLE);
                        filtrePhonemSerie.setVisibility(View.VISIBLE);
                        showFiltrageAvance.setText("Filtrage par format");
                        visible=false;
                    }else if (monFromat ==4){
                        rlCCVCV.setVisibility(View.GONE);
                        tv.setVisibility(View.VISIBLE);
                        lesPhonems.setVisibility(View.VISIBLE);
                        filtrePhonem.setVisibility(View.VISIBLE);
                        filtrePhonemSerie.setVisibility(View.VISIBLE);
                        showFiltrageAvance.setText("Filtrage par format");
                        visible=false;
                    }
                    /*rl.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    lesPhonems.setVisibility(View.VISIBLE);
                    filtrePhonem.setVisibility(View.VISIBLE);
                    filtrePhonemSerie.setVisibility(View.VISIBLE);
                    showFiltrageAvance.setText("Filtrage par format");
                    visible=false;*/
                }
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.genre , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);

        format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, format);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_format_filtre, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        format.setText(item.getTitle());
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"Format  " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });


        fourthC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, fourthC1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        fourthC1.setText(fourthC1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCV[0]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        fourthC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, fourthC2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        fourthC2.setText(fourthC2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCV[1]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        fourthV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, fourthV1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        fourthV1.setText(fourthV1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCV[2]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        fourthC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, fourthC3);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        fourthC3.setText(fourthC3.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCV[3]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        fourthV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, fourthV2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        fourthV2.setText(fourthV2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCV[4]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        thirdC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, thirdC1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        thirdC1.setText(thirdC1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCCV[0]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        thirdV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, thirdV1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        thirdV1.setText(thirdV1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCCV[1]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        thirdC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, thirdC2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        thirdC2.setText(thirdC2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCCV[2]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        thirdC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, thirdC3);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        thirdC3.setText(thirdC3.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCCV[3]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        thirdV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, thirdV2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        thirdV2.setText(thirdV2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCCV[4]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        secondC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondC1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondC1.setText(secondC1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[0]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });

        secondC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondC2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondC2.setText(secondC2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[1]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        secondV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondV1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondV1.setText(secondV1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[2]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        secondC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondC3);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondC3.setText(secondC3.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[3]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        secondC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondC4);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondC4.setText(secondC4.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[4]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        secondV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, secondV2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        secondV2.setText(secondV2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CCVCCV[5]=item.getTitle().toString();
                        //if (CCVCCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });



        firstC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstC1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstC1.setText(firstC1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCV[0]=item.getTitle().toString();
                        if (CVCV[0]==null){CVCV[0]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstV1);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstV1.setText(firstV1.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCV[1]=item.getTitle().toString();
                        if (CVCV[1]==null){CVCV[1]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstC2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_consonnes, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstC2.setText(firstC2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCV[2]=item.getTitle().toString();
                        if (CVCV[2]==null){CVCV[2]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
        firstV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, firstV2);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_voyelles, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        firstV2.setText(firstV2.getText().toString()+item.getTitle());
                        serieFiltre="";
                        suiteFiltre="";
                        CVCV[3]=item.getTitle().toString();
                        if (CVCV[3]==null){CVCV[3]="";}
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"element " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });


        lesPhonems.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, lesPhonems);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.pop_up_phonem, popup.getMenu());
                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"testons ca marche" , Toast.LENGTH_SHORT).show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String s = item.getTitle().toString();
                        maListePhonem += s ;
                        maListePhonem += ",";
                        lesPhonems.setText("la suite de phonems est ' "+maListePhonem+"'");
                        Log.i("filtres ",maListePhonem);
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"les Phonems sont "+maListePhonem+"ajout du phonem : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
               return true;
            }
        });

        /*lesPhonems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(CreationExoSpecifiqueLogatomActivity.this, lesPhonems);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.pop_up_phonem, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String s = item.getTitle().toString();
                        maListePhonem += s ;
                        maListePhonem += " ";
                        lesPhonems.setText("la suite de phonems est ' "+maListePhonem+"'");
                        Log.i("filtres ",maListePhonem);
                        Toast.makeText(CreationExoSpecifiqueLogatomActivity.this,"les Phonems sont "+maListePhonem+"ajout du phonem : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });*/

        filtrePhonemSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maListePhonem!=""){maListePhonem=maListePhonem.substring(0, maListePhonem.length() - 1);}
                serieFiltre = maListePhonem;
                Log.i("filtres ", serieFiltre);
                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this, "les Phonems sont " + serieFiltre, Toast.LENGTH_SHORT).show();
                SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sDialog.setContentText("La serie de filtres est :"+serieFiltre);
                sDialog.setCancelable(true);
                sDialog.show();

            }
        });


        filtrePhonem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(maListePhonem!=""){maListePhonem=maListePhonem.substring(0, maListePhonem.length() - 1);}
                suiteFiltre = maListePhonem;
                Log.i("filtres ", suiteFiltre);
                Toast.makeText(CreationExoSpecifiqueLogatomActivity.this, "les Phonems sont " + suiteFiltre,Toast.LENGTH_SHORT).show();
                SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sDialog.setContentText("La suite stricte de filtres est :"+suiteFiltre);
                sDialog.setCancelable(true);
                sDialog.show();
            }

        });

        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(String.valueOf(TextIteration.getText()).length() > 2 || String.valueOf(TextIteration.getText()).length() < 1)
                {
                    mauvaiseConfig("Veuillez remplir le nombre d'exercice à effectuer");
                }
                else
                {
                    String.valueOf(TextIteration.getText());
                    int iteration = Integer.parseInt(String.valueOf(TextIteration.getText()));

                    if(iteration == 0)
                    {
                        mauvaiseConfig("Impossible de lancer 0 exercice.");
                    }
                    else if(iteration > 12)
                    {
                        mauvaiseConfig("Impossible de lancer plus de 12 exercices");
                    }
                    else
                    {
                        String genrePatient = genre.getSelectedItem().toString() ;
                        String phonemesFiltrage;
                      /*  if(suiteFiltre!=""){
                            suiteFiltre=suiteFiltre.substring(0, suiteFiltre.length() - 1);
                            serieFiltre="";
                        }
                        if(serieFiltre!=""){
                            suiteFiltre="";
                            serieFiltre=serieFiltre.substring(0, serieFiltre.length() - 1);
                        }*/
                        phonemesFiltrage = suiteFiltre;
                        if (monFromat ==1){tabformat = CVCV;}
                        else if (monFromat ==2){tabformat = CCVCCV;}
                        else if (monFromat ==3){tabformat = CVCCV;}
                        else if (monFromat ==4){tabformat = CCVCV;}
                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String phonemeFiltrage)
                        if(dbPatientExo.addExercice(new ExerciceLogatome(Integer.parseInt(String.valueOf(TextIteration.getText())),genrePatient,getApplicationContext(),TextIdentifiantExo.getText().toString(),idPatient,suiteFiltre,serieFiltre,tabformat))){
                            maListePhonem="";
                            lesPhonems.setText("");
                            Log.i("filtres ils sont la zebiiiiiiiiii ", suiteFiltre);
                            for (int m=0 ; m <CVCV.length;m++){
                                Log.i("cvcv["+m+"]",CVCV[m]);
                            }
                            SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sDialog.setContentText("Exercice ajouté");
                            sDialog.setCancelable(true);
                            sDialog.show();

                            Intent intent = new Intent(CreationExoSpecifiqueLogatomActivity.this, ExerciceSpecifiqueLogatomLaunchActivity.class);
                            intent.putExtra("CVCV",tabformat);
                            intent.putExtra("phonemes",phonemesFiltrage);
                            intent.putExtra("serieFiltre",serieFiltre);
                            intent.putExtra("suiteFiltre",suiteFiltre);
                            intent.putExtra("type", "logatome");
                            intent.putExtra("genre", genrePatient);
                            intent.putExtra("iteration", iteration);
                            intent.putExtra("IdPatient", idPatient);
                            startActivityForResult(intent, 0);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                        }else{
                            SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.ERROR_TYPE);
                            sDialog.setTitleText("Oups ...");
                            sDialog.setContentText("erreur ajout de l'exo");
                            sDialog.setCancelable(true);
                            sDialog.show();
                        }
                        //public ExerciceLogatome(int nb, String leGenre, Context c,String idExo,String patientId,String listmots,String listPhonem)
                        //exercice = new ExerciceLogatome(iteration)

                    }
                }
            }
        });

    }

    public void mauvaiseConfig(String error)
    {
        SweetAlertDialog sDialog = new SweetAlertDialog(CreationExoSpecifiqueLogatomActivity.this, SweetAlertDialog.ERROR_TYPE);
        sDialog.setTitleText("Oups ...");
        sDialog.setContentText(error);
        sDialog.setConfirmText("Ok");
        sDialog.setCancelable(false);
        sDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
        {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        });
        sDialog.show();
    }
}
