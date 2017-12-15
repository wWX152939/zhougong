package bean;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class JsonDreamBean {
	/**
     * ��ȡassets�µ�txt�ļ�������utf-8 String
     * @param context
     * @param fileName ��������׺
     * @return
     */
    public static String readAssetsTxt(Context context,String fileName){
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
           return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "��ȡ���������ļ���";
    }
}
