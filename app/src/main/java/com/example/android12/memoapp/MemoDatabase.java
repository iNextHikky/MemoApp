package com.example.android12.memoapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemoDatabase {
    private Context mContext;
    private List<String> dates = new ArrayList<>();
    private List<String> texts = new ArrayList<>();
    private String fileName;
    private DateFormat formatter = DateFormat.getDateTimeInstance();

//  final private Pattern ptnDateForSave = Pattern.compile(".*(\\d{4})(\\d{2})(\\d{2}) (\\d{2}):(\\d{2}).*");
    final private Pattern ptnDateForLoad = Pattern.compile(".*(\\d{4})/(\\d{1,2})/(\\d{1,2}) (\\d{2}):(\\d{2}).*");

    public MemoDatabase(Context context){
        mContext = context;
        fileName = context.getFilesDir().getPath() + "/myMemo.txt";
        Log.d("TEST", "filename:"+fileName);
    }

    public void start(){
        //dates = new ArrayList<>();
        //texts = new ArrayList<>();
        loadDateTexts();
    }

    public void submit(String date, String text) {
        dates.add(date);
        texts.add(text);
        formatter.setTimeZone(TimeZone.getDefault());
        appendDateTexts(date, text);
        Log.d("Success", "submit Ok");
    }

    public void appendDateTexts(String date, String text) {
        String ans = formatter.format(new Date());
        Boolean append = true;
        try{
            FileOutputStream fos = new FileOutputStream(new File(fileName), append);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(ans);
            bw.newLine();
            bw.write(text);
            bw.newLine();
            bw.write("<EOT>");
            bw.newLine();
            bw.close();
            osw.close();
            fos.close();
        }catch (Exception e){
            Log.d("ERROR", "appendDateTexts FAILED");
        }
    }

    public void loadDateTexts(){
        String line;
        int state = 0;
        String msg = "";
        try{
            FileInputStream fis = new FileInputStream(new File(fileName));
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null){
                if(line.equals("<EOT>")){
                    texts.add(msg);
                    state = 0;
                }else if(state == 0){
                    dates.add(line);
                    state = 1;
                    msg = "";
                }else if(state == 1){
                    msg += line;
                }else {
                    Log.d("BAD Format", "Format FAILED");
                    state = 0;
                }
            }
            br.close();
            isr.close();
            fis.close();
        }catch (Exception e){
            Log.d("ERROR", "loadDateTexts FAILED");
        }
        Log.d("SUCCESS", "loadDateTexts SUCCESS");
    }

    public void search(String date, String text, List<String> dates, List<String> texts) {
        if (date == null || date.length() == 0) {
            if (text == null || text.length() == 0)
                return;
            else
                searchByText(text, dates, texts);
        } else {
            if (text == null || text.length() == 0)
                searchByDate(date, dates, texts);
            else
                searchByDateText(date, text, dates, texts);
        }
    }

    public void searchByText(String text, List<String> dates, List<String> texts){
        dates.clear();
        texts.clear();
        Pattern p = Pattern.compile(".*" + text + ".*");
        int sz = this.texts.size();
        for(int idx = 0; idx < sz; idx++){
            Matcher m = p.matcher(this.texts.get(idx));
            if(m.matches()){
                dates.add(this.dates.get(idx));
                texts.add(this.texts.get(idx));
            }
        }
    }

    public void searchByDate(String date, List<String> dates, List<String> texts){
        dates.clear();
        texts.clear();
        Pattern p = Pattern.compile(date);
        int sz = this.dates.size();
        for(int idx = 0; idx < sz; idx++){
            Matcher m = p.matcher(this.dates.get(idx));
            if(m.matches()){
                dates.add(this.dates.get(idx));
                texts.add(this.texts.get(idx));
            }
        }
    }
    public void searchByDateText(String date, String text, List<String> dates, List<String> texts) {
        dates.clear();
        texts.clear();
        Pattern p1, p2;
        Matcher m1, m2;
        p1 = Pattern.compile(date);
        p2 = Pattern.compile(".*" + text + ".*");
        int sz = this.texts.size();
        for (int idx = 0; idx < sz; idx++) {
            m1 = p1.matcher(this.dates.get(idx));
            m2 = p2.matcher(this.texts.get(idx));
            if(m1.matches() && m2.matches()){
                dates.add(this.dates.get(idx));
                texts.add(this.texts.get(idx));
            }
        }
    }

    public void copyAllEntries(List<String> dates, List<String> texts){
        dates.clear();
        texts.clear();
        int sz = this.dates.size();
        for(int idx = 0; idx < sz; idx++){
            dates.add(this.dates.get(idx));
            texts.add(this.texts.get(idx));
        }
    }
}
