package com.example.administrator.myapplication_weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.example.administrator.entity.CityInfo;
import com.example.administrator.entity.FutureInfo;
import com.example.administrator.entity.Mess;
import com.example.administrator.services.LocationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private WeatherChartView chartView;
    String mycity,str;
    //OKHttp客户端对象
    private OkHttpClient client;
    private TextView todayinfo1_cityName,todayinfo1_updateTime,todayinfo1_pm25status,
                      todayinfo1_pm25,todayinfo2_temperature,todayinfo2_wind,todayinfo1_humidity,today_week
                        ,todayinfo2_weatherState;
    private ImageView todayinfo2_weatherStatusImg,todayinfo1_pm25img;
    //其他天气控件
    private ImageView weather_1,weather_2,weather_3,weather_4;
    private TextView weather_week1,weather_week2,weather_week3,weather_week4;
    private TextView weather_limit1,weather_limit2,weather_limit3,weather_limit4;
    private TextView tianqi_1,tianqi_2,tianqi_3,tianqi_4;
    private List weeklist;
    //定位
    private LocationService locationService;
    private TextView LocationResult;
    private ImageView startLocation;
    String dingwei_city;
    //初始化定位
    String default_city;
    //曲线日期
    private TextView riqi1,riqi2,riqi3,riqi4,riqi5,riqi6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        client=new OkHttpClient();
        startLocation = findViewById(R.id.title_city_locate);//定位按钮
        todayinfo1_cityName = findViewById(R.id.todayinfo1_cityName);
        todayinfo1_updateTime = findViewById(R.id.todayinfo1_updateTime);
        todayinfo1_pm25status = findViewById(R.id.todayinfo1_pm25status);
        todayinfo1_pm25 = findViewById(R.id.todayinfo1_pm25);
        todayinfo2_temperature = findViewById(R.id.todayinfo2_temperature);
        todayinfo2_wind = findViewById(R.id.todayinfo2_wind);
        todayinfo2_weatherStatusImg = findViewById(R.id.todayinfo2_weatherStatusImg);
        todayinfo1_humidity = findViewById(R.id.todayinfo1_humidity);
        today_week = findViewById(R.id.today_week);
        todayinfo2_weatherState = findViewById(R.id.todayinfo2_weatherState);
        todayinfo1_pm25img = findViewById(R.id.todayinfo1_pm25img);

        weather_1 = findViewById(R.id.weather_1);
        weather_2 = findViewById(R.id.weather_2);
        weather_3 = findViewById(R.id.weather_3);
        weather_4 = findViewById(R.id.weather_4);
        weather_week1 = findViewById(R.id.weather_week1);
        weather_week2 = findViewById(R.id.weather_week2);
        weather_week3 = findViewById(R.id.weather_week3);
        weather_week4 = findViewById(R.id.weather_week4);

        weather_limit1 = findViewById(R.id.weather_limit1);
        weather_limit2 = findViewById(R.id.weather_limit2);
        weather_limit3 = findViewById(R.id.weather_limit3);
        weather_limit4 = findViewById(R.id.weather_limit4);

        tianqi_1 = findViewById(R.id.tianqi_1);
        tianqi_2 = findViewById(R.id.tianqi_2);
        tianqi_3 = findViewById(R.id.tianqi_3);
        tianqi_4 = findViewById(R.id.tianqi_4);
        chartView = (WeatherChartView) findViewById(R.id.line_char);

        riqi1 = findViewById(R.id.riqi1);
        riqi2 = findViewById(R.id.riqi2);
        riqi3 = findViewById(R.id.riqi3);
        riqi4 = findViewById(R.id.riqi4);
        riqi5 = findViewById(R.id.riqi5);
        riqi6 = findViewById(R.id.riqi6);



        SharedPreferences sharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE);
        mycity = sharedPreferences.getString("name","");//获取城市
        Log.i("city",mycity);
        test_1(mycity);





    }
    //发送GET请求实现登录操作
    public void test_1(final String city) {
        //构建一个请求对象
        String url = "http://apicloud.mob.com/v1/weather/query?key=appkey&city="+city+"";
        Request request = new Request.Builder().url(url).build();
        //构建一个Call对象
        Call call = client.newCall(request);
        //异步执行请求

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("call error", "登录出错," + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        /*default_city = dingwei_city.substring(0,dingwei_city.length()-1);
                        Log.i("city",default_city);
                        Toast.makeText(MainActivity.this,"当前城市："+default_city,Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        editor.putString("name", default_city);
                        editor.commit();*/
                        //test_1(city);
                        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("data", MODE_PRIVATE);
                        String default_city = sharedPreferences.getString("default","");//获取城市
                        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                        editor.putString("name", default_city);
                        Toast.makeText(MainActivity.this, "请连接网络", Toast.LENGTH_SHORT).show();
                        //未连接网络之前初始化定位
                    }
                });


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final List other_weather_max = new ArrayList();
                final List other_weather_mix = new ArrayList();
                //通过response得到服务器响应内容
                str = response.body().string();
                Log.i("网络查询天气weather", str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        todayinfo1_cityName.setText(mycity);
                    }
                });
                if("{\"msg\":\"城市参数为空\",\"retCode\":\"20401\"}".equals(str)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"请定位刷新",Toast.LENGTH_SHORT).show();
                        }
                    });
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putString("name", "北京");
                    editor.commit();
                    return;
                }
                //将字符串转换为json数据
                final Mess mess = new Gson().fromJson(str, new TypeToken<Mess>() {}.getType());//所有数据
                if("查询不到该城市的天气".equals(mess.getMsg())){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"查询不到该城市的天气",Toast.LENGTH_SHORT).show();
                            //设置所有值为空
                            todayinfo1_pm25img.setImageDrawable(null);
                            todayinfo1_updateTime.setText("未同步");


                        }
                    });
                    //SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    //editor.putString("name","珠海");//设置定位数据
                    //editor.commit();
                    return;
                }
                    final CityInfo cityInfo = mess.getResult().get(0);//result --  城市天气所有信息
                    Log.i("CityInfo", cityInfo.getCity());//获取的城市
                    //获取一周天气
                    List<FutureInfo> FutureList = cityInfo.getFuture();
                    //遍历一周天气
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    //今天日期，wind
                    String today_limit = "";
                    String today_wind ="";
                    weeklist = new ArrayList();
                    final List all_limit_list = new ArrayList();
                    final List all_dayTime_weather = new ArrayList();
                    final List all_night_weather = new ArrayList();
                    final List all_quxian_day = new ArrayList();
                    for (final FutureInfo futureinfo : FutureList) {
                        weeklist.add(futureinfo.getWeek());
                        all_limit_list.add(futureinfo.getTemperature());
                        all_dayTime_weather.add(futureinfo.getDayTime());
                        all_night_weather.add(futureinfo.getNight());
                        all_quxian_day.add(futureinfo.getDate());
                        Log.i("futureinfo", sdf.format(futureinfo.getDate()) + ":" + futureinfo.getTemperature());

                        String wendu = futureinfo.getTemperature();
                        if(wendu.length()<6){
                            wendu = wendu+" / "+wendu;
                            Log.i("wendutest",wendu);
                        }

                        String wendu2 = wendu.replace("°C / ","~");
                        String wendu3 = wendu2.replace("°C","");
                        int whendu4 = wendu3.indexOf("~");
                        //Log.i("wendu3",wendu3);
                        //Log.i("下标为",whendu4+"");
                        String whendu5 = wendu3.substring(0,whendu4);//最大天气温度
                        String whendu6 = wendu3.substring(whendu4+1,wendu3.length());//最小天气温度
                        //Log.i("this_max",whendu5);
                        //Log.i("this_mix",whendu6);

                        other_weather_max.add(whendu5);
                        other_weather_mix.add(whendu6);

                        if ("今天".equals(futureinfo.getWeek())) {
                            today_limit += futureinfo.getTemperature();
                            today_wind += futureinfo.getWind();
                            Log.i("today_limit", today_limit);
                        }
                    }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("other_weather_max",other_weather_max+"");
                        try {
                            int max1 = Integer.parseInt(other_weather_max.get(0).toString());
                            int max2 = Integer.parseInt(other_weather_max.get(1).toString());
                            int max3 = Integer.parseInt(other_weather_max.get(2).toString());
                            int max4 = Integer.parseInt(other_weather_max.get(3).toString());
                            int max5 = Integer.parseInt(other_weather_max.get(4).toString());
                            int max6 = Integer.parseInt(other_weather_max.get(5).toString());
                            // 设置白天温度曲线
                            chartView.setTempDay(new int[]{max1,max2,max3,max4,max5,max6});
                            // 设置夜间温度曲线
                            int mix1 = Integer.parseInt(other_weather_mix.get(0).toString());
                            int mix2 = Integer.parseInt(other_weather_mix.get(1).toString());
                            int mix3 = Integer.parseInt(other_weather_mix.get(2).toString());
                            int mix4 = Integer.parseInt(other_weather_mix.get(3).toString());
                            int mix5 = Integer.parseInt(other_weather_mix.get(4).toString());
                            int mix6 = Integer.parseInt(other_weather_mix.get(5).toString());
                            chartView .setTempNight(new int[]{mix1,mix2,mix3,mix4,mix5,mix6});
                            chartView .invalidate();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });






                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            weather_week1.setText(weeklist.get(1) + "");
                            weather_week2.setText(weeklist.get(2) + "");
                            weather_week3.setText(weeklist.get(3) + "");
                            weather_week4.setText(weeklist.get(4) + "");

                            weather_limit1.setText(all_limit_list.get(1)+"");
                            weather_limit2.setText(all_limit_list.get(2)+"");
                            weather_limit3.setText(all_limit_list.get(3)+"");
                            weather_limit4.setText(all_limit_list.get(4)+"");

                            tianqi_1.setText(all_dayTime_weather.get(1)+"");
                            tianqi_2.setText(all_dayTime_weather.get(2)+"");
                            tianqi_3.setText(all_dayTime_weather.get(3)+"");
                            tianqi_4.setText(all_dayTime_weather.get(4)+"");

                            riqi1.setText(sdf.format(all_quxian_day.get(0)).substring(5));
                            Log.i("day",sdf.format(all_quxian_day.get(0)));
                            riqi2.setText(sdf.format(all_quxian_day.get(1)).substring(5));
                            riqi3.setText(sdf.format(all_quxian_day.get(2)).substring(5));
                            riqi4.setText(sdf.format(all_quxian_day.get(3)).substring(5));
                            riqi5.setText(sdf.format(all_quxian_day.get(4)).substring(5));
                            riqi6.setText(sdf.format(all_quxian_day.get(5)).substring(5));

                            switch (all_dayTime_weather.get(1)+""){
                                case "晴":
                                    weather_1.setImageDrawable(getResources().getDrawable(R.drawable.daytime_sun));
                                    break;
                                case "多云":
                                    weather_1.setImageDrawable(getResources().getDrawable(R.drawable.duoyun));
                                    break;
                                default:weather_1.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                    break;
                            }
                            switch (all_dayTime_weather.get(2)+""){
                                case "晴":
                                    weather_2.setImageDrawable(getResources().getDrawable(R.drawable.daytime_sun));
                                    break;
                                case "多云":
                                    weather_2.setImageDrawable(getResources().getDrawable(R.drawable.duoyun));
                                    break;
                                default:weather_2.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                    break;
                            }
                            switch (all_dayTime_weather.get(3)+""){
                                case "晴":
                                    weather_3.setImageDrawable(getResources().getDrawable(R.drawable.daytime_sun));
                                    break;
                                case "多云":
                                    weather_3.setImageDrawable(getResources().getDrawable(R.drawable.duoyun));
                                    break;
                                default:weather_3.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                    break;
                            }
                            switch (all_dayTime_weather.get(4)+""){
                                case "晴":
                                    weather_4.setImageDrawable(getResources().getDrawable(R.drawable.daytime_sun));
                                    break;
                                case "多云":
                                    weather_4.setImageDrawable(getResources().getDrawable(R.drawable.duoyun));
                                    break;
                                default:weather_4.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                    break;
                            }
                        }
                    });


                   /* runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, mess.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });*/


                //------------------给所有控件绑定值
                final String finalToday_limit = today_limit;
                final String finalToday_wind = today_wind;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        todayinfo1_cityName.setText(cityInfo.getCity());//设置城市
                        String hours = cityInfo.getUpdateTime().substring(8,10);
                        String minute = cityInfo.getUpdateTime().substring(10,12);
                        todayinfo1_updateTime.setText(hours+":"+minute+"发布");//设置更新时间
                        todayinfo1_pm25status.setText(cityInfo.getAirCondition());
                        todayinfo1_pm25.setText(cityInfo.getPollutionIndex());
                        todayinfo2_temperature.setText(finalToday_limit);
                        todayinfo2_wind.setText(finalToday_wind);
                        todayinfo1_humidity.setText(cityInfo.getHumidity());
                        todayinfo2_weatherState.setText(cityInfo.getWeather());
                        today_week.setText("今天 "+cityInfo.getWeek());
                        todayinfo1_pm25img.setImageDrawable(getResources().getDrawable(R.drawable.people));
                        switch (cityInfo.getWeather()) {
                            case "晴":
                                todayinfo2_weatherStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.w0));
                                break;
                            case "多云":
                                todayinfo2_weatherStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.duoyun));
                                break;
                            case "阴":
                                todayinfo2_weatherStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.yin_today));
                                break;
                            default:todayinfo2_weatherStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                                break;
                        }
                    }
                });
                //Log.i("other_weather_max:",other_weather_max+"");


            }
        });

    }


    //test_2
    public void test_2(View view){
        Intent intent = new Intent(this,CityListActivity.class);
        startActivity(intent);
    }
    //刷新
    public void data_update(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE);
        String my_city = sharedPreferences.getString("name","");
        test_1(my_city);
        Toast.makeText(MainActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();

    }

    //分享
    public void test_page(View view){
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, str);
        startActivity(Intent.createChooser(textIntent, "分享"));
    }


    //连点两次退出程序
    private static boolean isExit = false;
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    //定位----------------------------------------------------------
    public void dingwei(View view){
        String city = "珠海";
        Toast.makeText(this,"当前城市："+city,Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("name", city);
        editor.commit();
        test_1(city);
    }
    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str) {
        final String s = str;
        try {
            if (LocationResult != null){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocationResult.post(new Runnable() {
                            @Override
                            public void run() {
                                LocationResult.setText(s);
                            }
                        });

                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        Log.i("线程暂停", "--------------");
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        Log.i("线程开始","--------------");
        // -----------location config ------------
        locationService = ((LocationApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        startLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationService.start();// 定位SDK
                // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                //startLocation.setText(getString(R.string.stoplocation));
                locationService.stop();

            }
        });

        //locationService.start();// 定位SDK
        //start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
        //startLocation.setText(getString(R.string.stoplocation));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = this.getSharedPreferences("data", MODE_PRIVATE);
        mycity = sharedPreferences.getString("name","");//获取城市
        test_1(mycity);
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }

                dingwei_city = location.getCity();
                //logMsg(sb.toString());
                Log.i("city",dingwei_city);
                if(dingwei_city!=null){
                    locationService.stop();
                }
                String city = dingwei_city.substring(0,dingwei_city.length()-1);
                Log.i("city",city);
                Toast.makeText(MainActivity.this,"当前城市："+city,Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", city);
                editor.putString("default",city);
                editor.commit();
                test_1(city);
            }
        }

    };

    public void qushi(View view){
        Toast.makeText(this,"quhsi",Toast.LENGTH_SHORT).show();
        chartView = (WeatherChartView) findViewById(R.id.line_char);
        // 设置白天温度曲线
        chartView.setTempDay(new int[]{10,20,10,30,10,15});
        // 设置夜间温度曲线
        chartView .setTempNight(new int[]{5,10,10,20,10,15});
        chartView .invalidate();
    }
}
