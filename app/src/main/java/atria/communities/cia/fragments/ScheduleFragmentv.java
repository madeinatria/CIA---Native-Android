package atria.communities.cia.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import atria.communities.cia.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class ScheduleFragmentv extends Fragment implements AdapterView.OnItemSelectedListener {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressBar progressbar = view.findViewById(R.id.progressbar);

        WebView webView = view.findViewById(R.id.web);
        /*webView.loadUrl("https://docs.google.com/spreadsheets/d/e/2PACX-1vS-MYxWYxUOLjnxotSJ2ptcxPL_UDDdrOFF8us4LzbbX5_FI_KeyjiJSt7A1-mogMi11ABAzDKakS8w/pubhtml");*/

        webView.loadUrl("https://docs.google.com/spreadsheets/d/e/2PACX-1vQcRT0vyW4C9kztpN97zy4l0IT_yH9_tECmDL3OnoEGw2odqaOKv0wSwQMOEFRYw4ykMuEKa7Suaa2n/pubhtml?gid=0&single=true");
        //webView.loadUrl("https://docs.google.com/spreadsheets/d/1PH1ab8wOuDOle1ivEgL-v1ozhnQy_Sm3SHQKTUasLBc/pubhtml");

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        webView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView view,String url){

                progressbar.setVisibility(View.GONE);
            }
        });







    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        onStartNewActivity();

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
        onStartNewActivity();
    }


    protected void onLeaveThisActivity() {
       // getActivity().overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    protected void onStartNewActivity() {
       // getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
        //    Toast.makeText(getContext(), ""+data.getExtras().getString("selectedCity"), Toast.LENGTH_SHORT).show();
           // city = data.getExtras().getString("selectedCity");
        }


    }
}
