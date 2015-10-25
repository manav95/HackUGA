package com.example.manavdutta1.hackuga;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.File;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.AssetManager;

public class ReadCVS{
	public static HashMap exec(Resources resources){
	
		ReadCVS obj = new ReadCVS();
		//obj.run();
		
		//HashMap<String, int[]> map = new HashMap<String, int[]>();
		
		HashMap<String, Champion> map = new HashMap<String, Champion>();
		
		map = obj.run(resources);
		return map;
	}
	public HashMap run(Resources resources){
        //HashMap<String, int[]> map = new HashMap<String, int[]>();
        HashMap<String, Champion> map = new HashMap<String, Champion>();
        BufferedReader br = null;
        try {
            //String[] files = manager.list("");
            //File csvFile = new File(files[0]);
            String line = "";
            String csvSplitBy = ",";
            //InputStream stream =
               // br = new BufferedReader(new FileReader(files[0]));
               InputStream stream = resources.openRawResource(R.raw.dkck);
               //AssetFileDescriptor descriptor = manager.openFd("raw/dkck.csv");
               // br = new BufferedReader(new FileReader("res/raw/dkck"));
                br = new BufferedReader(new InputStreamReader(stream));
                while ((line = br.readLine()) != null) {
                    String[] champion = line.split(csvSplitBy);
                    double[] percentages = new double[2];
                    //Champion champ = new Champion(champion[0], champion[1], champion[2]);
                    percentages[0] = Double.parseDouble(champion[1]);
                    percentages[1] = Double.parseDouble(champion[2]);

                    Champion champ = new Champion(champion[0], percentages[0], percentages[1]);

                    map.put(champion[0], champ);
                }


            }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
    return map;
	
	}
}