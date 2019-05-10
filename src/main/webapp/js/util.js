/*根据出生日期算出年龄*/
function jsMyGetAge(strBirthday){
    if(isEmpty(strBirthday)){
        return "";
    }
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];

    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();

    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }

    return returnAge;//返回周岁年龄

}


//判断字符是否为空的方法
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == "")
        return true
    var strings = ' ';
    if (strings.replace(/(^s*)|(s*$)/g, "").length ==0)
        return true;
    return false;
}
// 2019-08-02
function fmtmatDate(birthday) {
    var date = new Date(birthday);
    var y = 1900 + date.getYear();
    var m = "0" + (date.getMonth() + 1);
    var d = "0" + date.getDate();
    return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
}


function getMonthBetween(startDate,endDate){
    var date1 = startDate;
    var date2 = endDate;
// 拆分年月日
    date1 = date1.split('-');
// 得到月数
    date1 = parseInt(date1[0]) * 12 + parseInt(date1[1]);
// 拆分年月日
    date2 = date2.split('-');
// 得到月数
    date2 = parseInt(date2[0]) * 12 + parseInt(date2[1]);
    var m = Math.abs(date1 - date2);
    return m;
}

function setIssuStandard(age,roleType) {
    //2 农村  1城镇
    var standard = 0;
    if(roleType == 1){
        if(age < 79){
            standard = 0;
        } else if(age >= 80 && age <= 89){
            standard = 50;
        }else if(age >= 90 && age <= 99){
            standard = 100;
        } else if(age >= 100 ){
            standard = 300;
        }
    }else{
        if(age < 70){
            standard = 0;
        } else if(age >= 70 && age <= 79){
            standard = 50;
        }else if(age >= 80 && age <= 89){
            standard = 200;
        }else if(age >= 90 && age <= 99){
            standard = 500;
        } else if(age >= 100 ){
            standard = 1000;
        }
    }
    return standard;
}

function getBetweenMonthStr(grantTime) {
    var current = fmtmatDate(new Date());
    var between = 0;
    if(grantTime != null && grantTime != ""){
        between = getMonthBetween(fmtmatDate(grantTime),current);
    }
    var year = 0;
    var month = 0;
    if(between > 0){
        year =  Math.floor(between / 12);
        month = between % 12;
    }
    var desc = year == 0 ? (month +"月") :(year +"年"+ month +"月");
    return between == 0 ? "-" : desc;
}
