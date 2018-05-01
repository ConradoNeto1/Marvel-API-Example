package br.eti.wagnermessias.marvelexample.helpers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Wagner on 01/05/2018.
 */

public class RequestHelper {

    public static Map<String, String> getAuthorizationQueryMap() {

        Map<String, String> queryMap = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String ts = sdf.format(System.currentTimeMillis());

        String hashString = ts + Constants.MARVEL_PRIVATE_KEY + Constants.MARVEL_PUBLIC_KEY;
        String hash = Util.md5(hashString);

        queryMap.put("ts", ts);
        queryMap.put("hash", hash);
        queryMap.put("apikey", Constants.MARVEL_PUBLIC_KEY);

        return queryMap;
    }
}
