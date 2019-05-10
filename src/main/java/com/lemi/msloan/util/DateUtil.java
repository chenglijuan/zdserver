 package com.lemi.msloan.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


 public final class DateUtil
 {
      //private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
/*     */   public static Date getDateToString(String dateStr, String patten)
/*     */   {
/*  85 */     if (org.apache.commons.lang.StringUtils.isBlank(patten)) {
/*  86 */       return null;
/*     */     }
/*  88 */     SimpleDateFormat formatter = new SimpleDateFormat(patten);
/*     */     try {
/*  90 */       return formatter.parse(dateStr);
/*     */     } catch (ParseException e) {
/*  92 */       e.printStackTrace();
/*     */     }
/*  94 */     return null;
/*     */   }
    /*     */
/*     */   public static boolean isDateString(String date, String fmt)
/*     */   {
/* 107 */     SimpleDateFormat df = new SimpleDateFormat(fmt);
/*     */     try {
/* 109 */       df.parse(date);
/*     */     } catch (ParseException e) {
/* 111 */       return false;
/*     */     }
/* 113 */     return true;
/*     */   }
    /*     */
/*     */   public static String getDateString(String pattern, Date date) {
/* 117 */     SimpleDateFormat df = new SimpleDateFormat(pattern);
/* 118 */     String dateString = df.format(date);
/* 119 */     return dateString;
/*     */   }
    /*     */
/*     */   public static String getDateStringBeforeOrAfterToday(Integer day)
/*     */   {
/* 129 */     Calendar calendar = Calendar.getInstance();
/* 130 */     calendar.setTime(new Date());
/* 131 */     calendar.add(5, day.intValue());
/* 132 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 133 */     String dateString = df.format(calendar.getTime());
/* 134 */     return dateString;
/*     */   }
    /*     */
/*     */   public static String getDateStringBeforeOrAfterToday(Integer day, String dataPattern)
/*     */   {
/* 144 */     Calendar calendar = Calendar.getInstance();
/* 145 */     calendar.setTime(new Date());
/* 146 */     calendar.add(5, day.intValue());
/* 147 */     SimpleDateFormat df = new SimpleDateFormat(dataPattern);
/* 148 */     String dateString = df.format(calendar.getTime());
/* 149 */     return dateString;
/*     */   }
    /*     */
/*     */   public static String getDateStringBeforeOrAfterToday2(Integer day, String dataPattern) {
/* 153 */     Calendar calendar = Calendar.getInstance();
/* 154 */     calendar.setTime(new Date());
/* 155 */     calendar.add(5, day.intValue());
/* 156 */     SimpleDateFormat df = new SimpleDateFormat(dataPattern);
/* 157 */     String dateString = df.format(calendar.getTime());
/* 158 */     return dateString;
/*     */   }
    /*     */
/*     */   public static String getCurrentDate(String fmt)
/*     */   {
/* 167 */     if (StringUtils.isEmpty(fmt)) {
/* 168 */       fmt = "yyyy-MM-dd HH:mm:ss";
/*     */     }
/* 170 */     SimpleDateFormat df = new SimpleDateFormat(fmt);
/* 171 */     return df.format(new Date());
/*     */   }
    /*     */
/*     */


    /*     */
/*     */   public static String formatDate(Date date, String fmt) throws Exception {
/* 188 */     if (date == null) {
/* 189 */       return "";
/*     */     }
/* 191 */     SimpleDateFormat myFormat = new SimpleDateFormat(fmt);
/* 192 */     return myFormat.format(date);
/*     */   }
    /*     */
/*     */   public static Integer getLastDay(Integer year, Integer month)
/*     */   {
/* 203 */     Integer lastDay = null;
/* 204 */     Calendar cal = Calendar.getInstance();
/*     */
/* 206 */     cal.set(1, year.intValue());
/* 207 */     cal.set(2, month.intValue());
/* 208 */     cal.set(5, 1);
/* 209 */     cal.add(5, -1);
/* 210 */     Date lastDate = cal.getTime();
/*     */     try {
/* 212 */       lastDay = Integer.valueOf(Integer.parseInt(formatDate(lastDate, "yyyy-MM-dd HH:mm:ss").substring(8, 10)));
/*     */     } catch (Exception e) {
/* 214 */       e.printStackTrace();
/*     */     }
/* 216 */     return lastDay;
/*     */   }
    /*     */
/*     */   public static int daysBetween(Date smdate, Date bdate)
/*     */     throws ParseException
/*     */   {
/* 228 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 229 */     smdate = sdf.parse(sdf.format(smdate));
/* 230 */     bdate = sdf.parse(sdf.format(bdate));
/* 231 */     Calendar cal = Calendar.getInstance();
/* 232 */     cal.setTime(smdate);
/* 233 */     long time1 = cal.getTimeInMillis();
/* 234 */     cal.setTime(bdate);
/* 235 */     long time2 = cal.getTimeInMillis();
/* 236 */     long between_days = (time2 - time1) / 86400000L;
/*     */
/* 238 */     return Integer.parseInt(String.valueOf(between_days));
/*     */   }
    /*     */
/*     */   public static String getFirstDayOfMonth(Date date)
/*     */   {
/* 247 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 248 */     String day = "";
/*     */
/* 250 */     Calendar cale = Calendar.getInstance();
/* 251 */     cale.add(2, 0);
/* 252 */     cale.set(5, 1);
/* 253 */     day = format.format(cale.getTime());
/* 254 */     return day + " 00:00:00";
/*     */   }
    /*     */
/*     */   public static String getLastDayOfMonth(Date date)
/*     */   {
/* 263 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/* 264 */     String day = "";
/*     */
/* 266 */     Calendar cale = Calendar.getInstance();
/* 267 */     cale.add(2, 1);
/* 268 */     cale.set(5, 0);
/* 269 */     day = format.format(cale.getTime());
/* 270 */     return day + " 23:59:59";
/*     */   }
    /*     */
/*     */   public static String getDateStringBeforeOrMin(Integer min, String dataPattern)
/*     */   {
/* 279 */     Calendar calendar = Calendar.getInstance();
/* 280 */     calendar.setTime(new Date());
/* 281 */     calendar.add(12, min.intValue());
/* 282 */     SimpleDateFormat df = new SimpleDateFormat(dataPattern);
/* 283 */     String dateString = df.format(calendar.getTime());
/* 284 */     return dateString;
/*     */   }


     public static String getYesterdayDate() {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Calendar c = Calendar.getInstance();
         c.setTime(new Date());
         c.add(Calendar.DATE, -1);
         return format.format(c.getTime());
     }
     /**
      * 获取这个月的第一天/最后一天
      * @param date
      * @return
      */
     public static Map<String, String> getFirstdayLastdayMonth(Date date) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
//		calendar.add(Calendar.MONTH, -1);
         Date theDate = calendar.getTime();

         //上个月第一天
         GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
         gcLast.setTime(theDate);
         gcLast.set(Calendar.DAY_OF_MONTH, 1);
         String day_first = df.format(gcLast.getTime());
         StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
         day_first = str.toString();

         //上个月最后一天
         calendar.add(Calendar.MONTH, 1);    //加一个月
         calendar.set(Calendar.DATE, 1);        //设置为该月第一天
         calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
         String day_last = df.format(calendar.getTime());
         StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
         day_last = endStr.toString();

         Map<String, String> map = new HashMap<String, String>();
         map.put("first", day_first);
         map.put("last", day_last);
         return map;
     }

     /*
      * @author: clj
      * @data: 2018/7/11  下午2:09
      * @modified:
      * @decription:  上i个月
      * @param [i]
      * @return java.util.Map<java.lang.String,java.lang.String>
      */
     public static Date getNLastMonthInfo(Date date,int i) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         //Date date = new Date();
         //System.out.println("当前时间是：" + dateFormat.format(date));
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date); // 设置为当前时间
         calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + i); // 设置为上一个月
         date = calendar.getTime();
         System.out.println("上一个月的时间： " + dateFormat.format(date));
         return date;
     }

    /*
     * @author: clj
     * @data: 2018/7/13  下午3:53
     * @modified:
     * @decription: 获取指定月的所有月
     * @param []
     * @return java.util.List
     */
     public static List getDayListOfMonth(Date date) {
        // SimpleDateFormat sdf = new SimpleDateFormat();

         List list = new ArrayList();
         Calendar aCalendar = Calendar.getInstance();
         aCalendar.setTime(date);
         int year = aCalendar.get(Calendar.YEAR);//年份
         int month = aCalendar.get(Calendar.MONTH) + 1;//月份
         int day = aCalendar.getActualMaximum(Calendar.DATE);
         for (int i = 1; i <= day; i++) {
             String aDate = String.valueOf(year)+"-"+month+"-"+i;
             //System.out.println("aDate="+aDate);
             list.add(aDate);
         }
         return list;
     }


     /*
      * @author: clj
      * @data: 2018/7/13  下午4:02
      * @modified:
      * @decription:获取所有的月份
      * @param [date]
      * @return java.util.List
      */
     public static List getDayListOfYear(Date date) {

         List list = new ArrayList();
         Calendar aCalendar = Calendar.getInstance();
         aCalendar.setTime(date);
         int year = aCalendar.get(Calendar.YEAR);//年份;
         for (int i = 1; i <= 12; i++) {
             String aDate = String.valueOf(year)+"-"+i+"-01";
             list.add(aDate);
         }
         return list;
     }



     public static List getDayListOfYear1(Date date) throws Exception{
         List list = new ArrayList();
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar aCalendar = Calendar.getInstance();
         aCalendar.setTime(date);
         int year = aCalendar.get(Calendar.YEAR);//年份;

         for (int i = 1; i <= 12; i++) {
             String aDate = String.valueOf(year)+"-"+i+"-01";
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(df.parse(aDate));
             //上个月第一天
             GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
             gcLast.setTime(df.parse(aDate));
             gcLast.set(Calendar.DAY_OF_MONTH, 1);
             String day_first = df.format(gcLast.getTime());
             StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
             day_first = str.toString();

             //上个月最后一天
             calendar.add(Calendar.MONTH, 1);    //加一个月
             calendar.set(Calendar.DATE, 1);        //设置为该月第一天
             calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
             String day_last = df.format(calendar.getTime());
             StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
             day_last = endStr.toString();

             Map<String, String> map = new HashMap<String, String>();
             map.put("first", day_first);
             map.put("last", day_last);
             aDate =day_first +";"+day_last;
             list.add(aDate);
            // System.out.println("adate"+aDate);
         }
         return list;
     }


     public static int monthBetween(Date smdate, Date bdate){
         int result = 0;

         //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

         Calendar c1 = Calendar.getInstance();
         Calendar c2 = Calendar.getInstance();

         c1.setTime(smdate);
         c2.setTime(bdate);
         result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

         return result == 0 ? 1 : Math.abs(result);
     }


     /**
      * 判断是否是对应的格式的日期字符串
      * @param dateStr
      * @param datePattern
      * @return
      */
     public static boolean isRightDateStr(String dateStr,String datePattern){
         SimpleDateFormat dateFormat  = new SimpleDateFormat(datePattern);
         try {
             //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
             dateFormat.setLenient(false);
             dateFormat.parse(dateStr);
             Date date = (Date)dateFormat.parse(dateStr);
             //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
             String newDateStr = dateFormat.format(date);
             if(dateStr.equals(newDateStr)){
                 return true;
             }else {
                 return false;
             }
         } catch (ParseException e) {
             return false;
         }
     }


     /**
      * 根据开始时间和结束时间返回时间段内的时间集合
      *
      * @param beginDate
      * @param endDate
      * @return List
      */
     public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
         List<Date> lDate = new ArrayList<Date>();
         lDate.add(beginDate);// 把开始时间加入集合
         Calendar cal = Calendar.getInstance();
         // 使用给定的 Date 设置此 Calendar 的时间
         cal.setTime(beginDate);
         boolean bContinue = true;
         while (bContinue) {
             // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
             cal.add(Calendar.DAY_OF_MONTH, 1);
             // 测试此日期是否在指定日期之后
             if (endDate.after(cal.getTime())) {
                 lDate.add(cal.getTime());
             } else {
                 break;
             }
         }
         lDate.add(endDate);// 把结束时间加入集合
         return lDate;

     }
     public static int dayForWeek(Date date) throws Exception {
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         int dayForWeek = 0;
         if(c.get(Calendar.DAY_OF_WEEK) == 1){
             dayForWeek = 7;
         }else{
             dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
         }
         return dayForWeek;
     }

     /**
      * 判断时间格式 格式必须为“YYYY-MM-dd”
      * 2004-2-30 是无效的
      * 2003-2-29 是无效的
      * @return
      */
     public static boolean isValidDate(String str) {
         //String str = "2007-01-02";
         if(StringUtils.isBlank(str)){
             return false;
         }
         DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         try{
             Date date = (Date)formatter.parse(str);
             return str.equals(formatter.format(date));
         }catch(Exception e){
             return false;
         }
     }

     public static String getYearsbefore(Date date,Integer before){
         SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-before);
         Date updateDate2 = calendar.getTime();
         //System.out.println("往前推1个月的时间"+sdf.format(updateDate2));
         return sdf.format(updateDate2);
     }

     public static int getAgeByBirth(Date birthDay) throws ParseException {
         int age = 0;
         Calendar cal = Calendar.getInstance();
         if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
             throw new IllegalArgumentException(
                     "The birthDay is before Now.It's unbelievable!");
         }
         int yearNow = cal.get(Calendar.YEAR);  //当前年份
         int monthNow = cal.get(Calendar.MONTH);  //当前月份
         int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
         cal.setTime(birthDay);
         int yearBirth = cal.get(Calendar.YEAR);
         int monthBirth = cal.get(Calendar.MONTH);
         int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
         age = yearNow - yearBirth;   //计算整岁数
         if (monthNow <= monthBirth) {
             if (monthNow == monthBirth) {
                 if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
             } else {
                 age--;//当前月份在生日之前，年龄减一
             }
         }
         return age;
     }


     public static void main(String[] args) {
         getNLastMonthInfo(new Date(),1);
     }


}

