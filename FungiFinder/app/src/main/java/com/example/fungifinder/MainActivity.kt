package com.example.fungifinder

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import org.json.JSONObject
import org.jetbrains.anko.doAsync
import java.util.*
import kotlin.math.truncate

class MainActivity : AppCompatActivity() {

    val RequestPermissionCode = 1
    var mLocation: Location? = null
    val API = "b16619ac2ec6b9560e1cc815967abdfd"
    var TIME = System.currentTimeMillis() / 1000
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()
        getLastLocation()
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){ return }
        fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermission()
        } else{
            fusedLocationClient.lastLocation.addOnSuccessListener{
                location: Location? -> mLocation = location
                    if(location != null) {
                        var LAT = location.latitude.toInt()
                        var LON = location.longitude.toInt()

                        doAsync {

                            //current weather
                            var response = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj = JSONObject(response)
                            var temp = jsonObj.getJSONObject("current").getDouble("temp"); /*convert to F */ temp = ((((temp-273.15)*9)/5)+32); temp = truncate(temp)
                            var timezone = jsonObj.getString("timezone")
                            var humidity = jsonObj.getJSONObject("current").getInt("humidity")
                            var weather = jsonObj.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //weather 1 day ago
                            TIME = TIME - 86400
                            var response1 = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj1 = JSONObject(response1)
                            var temp1 = jsonObj1.getJSONObject("current").getDouble("temp"); /*convert to F */ temp1 = ((((temp1-273.15)*9)/5)+32); temp1 = truncate(temp1)
                            var timezone1 = jsonObj1.getString("timezone")
                            var humidity1 = jsonObj1.getJSONObject("current").getInt("humidity")
                            var weather1 = jsonObj1.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //weather 2 days ago
                            TIME = TIME - 86400
                            var response2 = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj2 = JSONObject(response2)
                            var temp2 = jsonObj2.getJSONObject("current").getDouble("temp"); /*convert to F */ temp2 = ((((temp2-273.15)*9)/5)+32); temp2 = truncate(temp2)
                            var timezone2 = jsonObj2.getString("timezone")
                            var humidity2 = jsonObj2.getJSONObject("current").getInt("humidity")
                            var weather2 = jsonObj2.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //weather 3 days ago
                            TIME = TIME - 86400
                            var response3 = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj3 = JSONObject(response3)
                            var temp3 = jsonObj3.getJSONObject("current").getDouble("temp"); /*convert to F */ temp3 = ((((temp3-273.15)*9)/5)+32); temp3 = truncate(temp3)
                            var timezone3 = jsonObj3.getString("timezone")
                            var humidity3 = jsonObj3.getJSONObject("current").getInt("humidity")
                            var weather3 = jsonObj3.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //weather 4 days ago
                            TIME = TIME - 86400
                            var response4 = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj4 = JSONObject(response4)
                            var temp4 = jsonObj4.getJSONObject("current").getDouble("temp"); /*convert to F */ temp4 = ((((temp4-273.15)*9)/5)+32); temp4 = truncate(temp4)
                            var timezone4 = jsonObj4.getString("timezone")
                            var humidity4 = jsonObj4.getJSONObject("current").getInt("humidity")
                            var weather4 = jsonObj4.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //weather 5 days ago
                            TIME = TIME - 86400
                            var response5 = URL("https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=$LAT&lon=$LON&dt=$TIME&appid=$API").readText(Charsets.UTF_8)
                            var jsonObj5 = JSONObject(response5)
                            var temp5 = jsonObj5.getJSONObject("current").getDouble("temp"); /*convert to F */ temp5 = ((((temp5-273.15)*9)/5)+32); temp5 = truncate(temp5)
                            var timezone5 = jsonObj5.getString("timezone")
                            var humidity5 = jsonObj5.getJSONObject("current").getInt("humidity")
                            var weather5 = jsonObj5.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getInt("id")

                            //check what mushrooms are in season and convert to var
                            var inSeason = checkFungi(LON, LAT)

                            println(Calendar.MONTH)
                            println(checkRegion(LON, LAT))
                            println(LON)
                            println(LAT)
                            println(temp2)
                            println(temp3)
                            println(temp4)
                            println(weather5)
                            println(inSeason.honeys)

                            //create a single score based on current conditions
                            var score = assignScore(humidity, humidity1, humidity2, humidity3, humidity4, humidity5, weather, weather1, weather2, weather3, weather4, weather5, temp, temp1, temp2, temp3, temp4, temp5)
                        }
                    } else{
                        latitude.text = "NO_LOC_DATA"
                        longitude.text = "NO_LOC_DATA"
                    }
            }
        }
    }

    data class Species(var lions: Boolean, var oysters: Boolean, var chickens: Boolean, var hedgehogs: Boolean, var puffballs: Boolean, var boletes: Boolean, var chanterelle: Boolean, var milks: Boolean, var honeys: Boolean, var morels: Boolean)
    private fun checkFungi(LON: Int, LAT: Int): Species{
        var lions = false; var oysters = false; var chickens = false; var hedgehogs = false; var puffballs = false; var boletes = false; var chanterelle = false; var milks = false; var honeys = false; var morels = false
        //var tempAvg = ((temp1 + temp2 + temp3 + temp4 + temp5) / 5)

        //check region and see what mushrooms are in season
        if(checkRegion(LON, LAT) == "NA_G"){
            if(month() == "Winter"){
                lions = true; chickens = true; oysters = true; milks = true; honeys = true
            }
            if(month() == "Spring"){
                hedgehogs = true; morels = true; chanterelle = true
            }
            if(month() == "Summer"){
                lions = true; chickens = true; hedgehogs = true; milks = true; boletes = true; chanterelle = true
            }
            if(month() == "Fall"){
                chickens = true; lions = true; milks = true; oysters = true; hedgehogs = true; boletes = true; chanterelle = true; honeys = true
            }
            }

        return Species(lions, oysters, chickens, hedgehogs, puffballs, boletes, chanterelle, milks, honeys, morels)
    }

    private fun month(LON: Int, LAT: Int): String{
        var month = "Winter"
        //if america
        if(checkRegion(LON, LAT) == "NA_E" || checkRegion(LON, LAT) == "NA_G" || checkRegion(LON, LAT) == "NA_W") {
            when (Calendar.MONTH) {
                3 -> month = "Spring"
                4 -> month = "Spring"
                5 -> month = "Spring"
                6 -> month = "Summer"
                7 -> month = "Summer"
                8 -> month = "Summer"
                9 -> month = "Fall"
                10 -> month = "Fall"
                11 -> month = "Fall"
            }
        }

        return month
    }

    private fun checkRegion(LON: Int, LAT: Int): String{
        var region = "NA_E"//north america east default region

        if(LON >= -104 && LON <= -50 && LAT >= 33 && LAT <= 59){region = "NA_E"}//North America East
        if(LON <= -105 && LON >= -125 && LAT >= 29 && LAT <= 59){region = "NA_W"}//North America West
        if(LON >= -104 && LON <= -80 && LAT <= 33 && LAT >= 25){region = "NA_G"}//North America Gulf Coast

        return region
    }

    private fun assignScore(humidity: Int, humidity1: Int, humidity2: Int, humidity3: Int, humidity4: Int, humidity5: Int, weather: Int, weather1: Int, weather2: Int, weather3: Int, weather4: Int, weather5: Int, temp: Double, temp1: Double, temp2: Double, temp3: Double, temp4: Double, temp5: Double): Int{

        //generic score on how abundant mushrooms are. 0 = little/none. 1-12 = little. 13 - 18 = medium. 19 - 27 = abundant
        var score = 0
        if(checkHumid(humidity) == "medium"){score++}
        if(checkHumid(humidity1) == "medium"){score++}
        if(checkHumid(humidity2) == "medium"){score++}
        if(checkHumid(humidity3) == "medium"){score++}
        if(checkHumid(humidity4) == "medium"){score++}
        if(checkHumid(humidity5) == "medium"){score++}
        if(checkHumid(humidity) == "high"){score += 2}
        if(checkHumid(humidity1) == "high"){score += 2}
        if(checkHumid(humidity2) == "high"){score += 2}
        if(checkHumid(humidity3) == "high"){score += 2}
        if(checkHumid(humidity4) == "high"){score += 2}
        if(checkHumid(humidity5) == "high"){score += 2}
        if(checkRain(weather) == true){score++}
        if(checkRain(weather1) == true){score++}
        if(checkRain(weather2) == true){score += 2}
        if(checkRain(weather3) == true){score += 6}
        if(checkRain(weather4) == true){score += 4}
        if(checkRain(weather5) == true){score++}

        //set score to 0 if too many days have had freezing weather
        if(checkTemp(temp5) == "freezing" && checkTemp(temp4) == "freezing" && checkTemp(temp3) == "freezing" && checkTemp(temp2) == "freezing"){score = 0}

        return score
    }

    private fun checkHumid(humidity: Int): String{
        var humidGeneric = "default"

        if(humidity >= 70){humidGeneric = "high"}
        if(humidity in 50..69){humidGeneric = "medium"}
        if(humidity < 50){humidGeneric = "low"}

        return humidGeneric
    }

    private fun checkTemp(temp: Double): String{
        var tempGeneric = "default"

        if(temp < 50){tempGeneric = "freezing"}
        if(temp > 49 && temp < 65){tempGeneric = "cold"}
        if(temp > 64){tempGeneric = "warm"}

        return tempGeneric
    }

    private fun checkRain(weatherID: Int): Boolean{

        if(weatherID in 200..531){
            return true
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), RequestPermissionCode)

        this.recreate()
    }
}