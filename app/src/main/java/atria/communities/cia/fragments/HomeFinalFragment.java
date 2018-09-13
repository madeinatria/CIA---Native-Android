package atria.communities.cia.fragments;

import android.content.Intent;
import android.os.Build;
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

public class HomeFinalFragment extends Fragment implements AdapterView.OnItemSelectedListener {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_final_fragmentv3,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressBar progressbar = view.findViewById(R.id.progressbar);

        WebView webView = view.findViewById(R.id.web);
       // webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSekiWtQGHSq_js6w7aTB9eEC-9RXqJVigbWLpDDvWOOrs8sng/viewform?usp=sf_link");

        webView.loadUrl("http://ciatria.com/web_view.html");
      // webView.getSettings().setLoadWithOverviewMode(true);
        /*webView.getSettings().setUseWideViewPort(true);*/
        WebSettings webSettings = webView.getSettings();
     //   webSettings.setJavaScriptEnabled(true);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
       /* settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);*/
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 /*       webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

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
