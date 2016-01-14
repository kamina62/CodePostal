/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCodePostal;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bastien
 */
public class CodePostal {

    private String json;
    private List<String> lesVilles;

    public CodePostal(int code) {
        start(String.valueOf(code));
    }

    public CodePostal(String code) {
        start(code);
    }

    private void start(String code) {
        if (code.length() != 5) {
            System.out.println("erreur code invalide");
        }

        lesVilles = new LinkedList<>();
        String url = "http://www.cp-ville.com/cpcom.php?cpcommune=" + code;

        String html = getHTML(url);
// replacement de caratére 
        html = html.substring(13, html.length() - 2);

        decode(html);

    }

    private String getHTML(String urlToRead) {
        String strReturn = null;
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //1ére ligne html commentée "<--  --!>"
            rd.readLine();
            strReturn = rd.readLine();
        } catch (MalformedURLException ex) {
            new Exception("[getHTML] MalformedURLException " + ex.getMessage());

        } catch (IOException ex) {
            new Exception("[getHTML] IOException " + ex.getMessage());
        }
        return strReturn;
    }

    private void decode(String json) {
        Map<String, Object> fromJson = new Gson().fromJson(json, Map.class);

        // obtention du nombre de ville 
        Object Scount = fromJson.get("count");
        int count = (int) Double.parseDouble(Scount.toString());
//        System.out.println("nombre de ville : " + count);

        for (int i = 1; i <= count; i++) {
            LinkedTreeMap get = (LinkedTreeMap) fromJson.get(String.valueOf(i));
            String ville = (String) get.get("ville");
            ville = ville.toLowerCase();
            ville = ville.substring(0, 1).toUpperCase() + ville.substring(1);
            lesVilles.add(ville);

        }
    }

    public List<String> getLesVillesList() {
        return lesVilles;
    }

    public String[] getLesVillesArray() {
        return lesVilles.stream().toArray(String[]::new);
    }

}
