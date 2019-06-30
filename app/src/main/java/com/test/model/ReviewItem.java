/*
数据库表项的设计
traditional nchar primary key not null
repeatTimes int
EFactor float
NextDate Date

存储下一次要学习的日期，每次检索，是找今天之前的所有条目。
根据公式计算下一次学习的日期，存储到数据库中。

回忆起来的质量分级，公式: q = 5 - (wrongTimes - 1) / 3
5 - <=3
4 - <=6
3 - <=9
2 - <=12
1 - <=15
0 - <=18
*/

package com.test.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.test.util.SQLdm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewItem {

    private String traditional;
    private int repeatTimes;
    private float eFactor;

    public ReviewItem(String traditional, int repeatTimes, float eFactor) {
        this.traditional = traditional;
        this.repeatTimes = repeatTimes;
        this.eFactor = eFactor;
    }

    public String getTraditional() {
        return traditional;
    }

    /**
     * 更新这个条目数据库的内容，如果q为2,1,0，那么回炉重造，重新学过。
     * q = 5 - (wrongTimes - 1) / 3
     * EF':=EF+(0.1-(5-q)*(0.08+(5-q)*0.02))
     * @param context 传入getApplicationContext()的数据
     * @param wrongTimes
     */
    public void updateDb(Context context, int wrongTimes) {
        int quality = 5 - (wrongTimes - 1) / 3;
        eFactor = eFactor + (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f));
        if (eFactor < 1.3) eFactor = 1.3f;

        SQLiteDatabase database = new SQLdm().openDataBase(context);
        // 更新下一次学习日期或者回炉重做
        if (quality > 2) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String today = sdf.format(new Date());
            String sql = "update review set nextDate = \"" + today + "\" where traditonal = \"" + traditional + "\"";
            database.execSQL(sql);
        }
        else {
            String sql = "delete from review where traditonal = \"" + traditional + "\"";
            database.execSQL(sql);
            sql = "update words set learnTimes = 0 where traditonal = \"" + traditional + "\"";
            database.execSQL(sql);
        }
        database.close();
    }
}