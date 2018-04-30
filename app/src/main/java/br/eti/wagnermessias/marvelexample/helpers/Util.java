package br.eti.wagnermessias.marvelexample.helpers;

import android.provider.ContactsContract;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Wagner on 29/04/2018.
 */

public class Util {

    public static List<String> getAuthorizationParameterValues(){
        List<String> parametersValues = new ArrayList<String>();

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date timestamp = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
//        String ts = sdf.format(timestamp);

        long timeStamp = System.currentTimeMillis();

//        String ts = String.valueOf(System.currentTimeMillis());

        String hashString = timeStamp + Constants.MARVEL_PRIVATE_KEY + Constants.MARVEL_PUBLIC_KEY;
        String hashMD5 = null;


        String hash = DigestUtils.md5(hashString);

//            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
//            byte[] md5Bytes = md5Encoder.digest(hashString.getBytes("UTF-8"));
//
//            StringBuilder md5 = new StringBuilder();
//            for (int i = 0; i < md5Bytes.length; ++i) {
//                md5.append(Integer.toHexString((md5Bytes[i] & 0xFF) | 0x100).substring(1, 3));
//            }
//            MessageDigest algorithm = MessageDigest.getInstance("MD5");
//            byte messageDigest[] = algorithm.digest(hashString.getBytes("UTF-8"));
//            hashMD5 = new String(messageDigest);
        hashMD5 = hash.toString();

        return null;
    }
}
