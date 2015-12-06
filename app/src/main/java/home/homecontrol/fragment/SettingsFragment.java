package home.homecontrol.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import home.homecontrol.R;
import home.homecontrol.network.NetworkData;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public static final String FRAGMENT_TAG = "SettingsFragment";
    private static final String LOG_TAG = SettingsFragment.class.getName();

    @Bind(R.id.settingIP)
    EditText settingIPEdit;
    @Bind(R.id.settingIPButton)
    Button settingIpButton;
    Context context;

    AsyncHttpClient client;

    public SettingsFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        client = new AsyncHttpClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        settingIpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ip = -1;
                try {
                    ip = Integer.valueOf(settingIPEdit.getText().toString());
                } catch (NumberFormatException e){}

                if (ip < 255 && ip > 0) {
                    NetworkData.setIpSet(Integer.toString(ip));
                    String request = NetworkData.getIpServer() + NetworkData.SETTINGS;
                    Log.d(LOG_TAG, request);
                    client.get(request, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String str = null;
                            try {
                                str = new String(responseBody, "UTF-8");
                                Log.d(LOG_TAG, "response: " + str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (str.equals(NetworkData.OK_MSG)) {
                                Toast.makeText(context, "Urządzenie zostało sparsowane!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Parsowanie się nie powiodło!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(context, "Brak połączenia z urządzeniem!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(context, "Zły adress IP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}