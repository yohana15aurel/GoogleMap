package com.example.tutorialgooglemaps;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class callback_map extends AsyncTask<String, String, ArrayList<HashMap<String, String>>> {

    Context context;
    int success;
    String message;
    private JSONParser jsonParser = new JSONParser();
    JSONArray jsonArray = null;
    ArrayList<HashMap<String, String>> arrayListRet;

    public callback_map(Context m_context) {
        this.context= m_context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... params) {

        // Building Parameters
        List<NameValuePair> paramsed = new ArrayList<NameValuePair>();


        Log.d("CEKIDBOOK",params[0]);


        paramsed.add(new BasicNameValuePair("get", params[0])); //email



        System.out.println("params[0] = "+params[0]);

        JSONObject json = jsonParser.makeHttpRequest("http://dev.projectlab.co.id/mit/1317012/mst_map.php",
                "POST", paramsed);

        System.out.println("json 2 = "+json.toString());
        //Log.d("CEKIDBOOK_MSG",json.toString());

        arrayListRet = new ArrayList<>();

        try {
            success = json.getInt("success");

            message = json.getString("message");


            if (success == 1) {
                jsonArray = json.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);

                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("lat", c.getString("lat"));
                    map.put("lang", c.getString("lang"));


                    arrayListRet.add(map);
                }
            } else {
                //Unsuccessfully picking book. Another driver has taken the order.
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("success", String.valueOf(success));
                map.put("message", message);

                arrayListRet.add(map);

            }
        } catch (JSONException e) {
            return null;
        }

        System.out.println("arrayListRet = "+arrayListRet);
        return arrayListRet;
    }


    /**
     * After completing sys_navdrawer_background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(ArrayList<HashMap<String, String>> file_url) {

    }
}
