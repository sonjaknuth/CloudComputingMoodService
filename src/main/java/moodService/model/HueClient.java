package moodService.model;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface HueClient {


	// REST Controller
	
	/** Register user **/
	// method: POST
	//http://<Bridge IP Address>/api  (eg: 192.168.2.110)
	//body: {"devicetype":"my_hue_app#Sonjas iPhone"}
	// Response: 
	//[
//    {
//        "success": {
//            "username": "qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M"
//        }
//    }
//]
	@RequestLine("POST /api")
	@Headers("Content-Type: application/json")
	@Body("%7B\"devicetype\": \"{devicetypeName}\"%7D") //like so: my_hue_app#Sonjas iPhone
    void register(@Param("devicetypeName") String devicetypeName);
	
	/** Get Lamps**/
	// Method: GET
	// http://192.168.2.110/api/{username eg:qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M}/lights
	// Response:
//	{
//	    "1": {
//	        "state": {
//	            "on": true,
//	            "bri": 106,
//	            "hue": 8418,
//	            "sat": 140,
//	            "effect": "none",
//	            "xy": [
//	                0.4573,
//	                0.41
//	            ],
//	            "ct": 366,
//	            "alert": "none",
//	            "colormode": "ct",
//	            "reachable": true
//	        },
//	        "type": "Extended color light",
//	        "name": "Hue color lamp 1",
//	        "modelid": "LCT010",
//	        "manufacturername": "Philips",
//	        "uniqueid": "00:17:88:01:02:46:35:bb-0b",
//	        "swversion": "1.15.0_r18729",
//	        "swconfigid": "F921C859",
//	        "productid": "Philips-LCT010-1-A19ECLv4"
//	    },
//	    "2": {
//	        "state": {
//	            "on": false,
//	            "bri": 0,
//	            "hue": 0,
//	            "sat": 0,
//	            "effect": "none",
//	            "xy": [
//	                0,
//	                0
//	            ],
//	            "ct": 0,
//	            "alert": "none",
//	            "colormode": "hs",
//	            "reachable": false
//	        },
//	        "type": "Extended color light",
//	        "name": "Hue color lamp 2",
//	        "modelid": "LCT010",
//	        "manufacturername": "Philips",
//	        "uniqueid": "00:17:88:01:02:3c:28:9b-0b",
//	        "swversion": ""
//	    },
//	    "3": {
//	        "state": {
//	            "on": false,
//	            "bri": 0,
//	            "hue": 0,
//	            "sat": 0,
//	            "effect": "none",
//	            "xy": [
//	                0,
//	                0
//	            ],
//	            "ct": 0,
//	            "alert": "none",
//	            "colormode": "hs",
//	            "reachable": false
//	        },
//	        "type": "Extended color light",
//	        "name": "Hue color lamp 3",
//	        "modelid": "LCT010",
//	        "manufacturername": "Philips",
//	        "uniqueid": "00:17:88:01:02:46:30:d5-0b",
//	        "swversion": ""
//	    }
//	}
	
	/**  Get Specific Lamp **/ 
	// Method: GET
	// http://192.168.2.110/api/{username eg:qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M}/lights
	// Response:
//	{
//	    "state": {
//	        "on": true,
//	        "bri": 254,
//	        "hue": 8418,
//	        "sat": 140,
//	        "effect": "none",
//	        "xy": [
//	            0.4573,
//	            0.41
//	        ],
//	        "ct": 366,
//	        "alert": "none",
//	        "colormode": "ct",
//	        "reachable": true
//	    },
//	    "type": "Extended color light",
//	    "name": "Hue color lamp 1",
//	    "modelid": "LCT010",
//	    "manufacturername": "Philips",
//	    "uniqueid": "00:17:88:01:02:46:35:bb-0b",
//	    "swversion": "1.15.0_r18729",
//	    "swconfigid": "F921C859",
//	    "productid": "Philips-LCT010-1-A19ECLv4"
//	}
	
	/** Turn Lamp ON/OFF**/
	// Method: PUT
	// http://192.168.2.110/api/qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M/lights/1/state
	// Body: {"on":true/false}
	// Response:
//	[
//	    {
//	        "success": {
//	            "/lights/1/state/on": true
//	        }
//	    }
//	]
	@RequestLine("PUT /api/{userID}/lights/{lampID}/state")
	@Headers("Content-Type: application/json")
	 // json curly braces must be escaped!
	@Body("%7B\"on\": \"{onOff}\"%7D") 
    void changeOnOffState(@Param("userID") String userID, @Param("lampID") String lampID, @Param("onOff") boolean lampOn);
	
	
	/** change colors -- see also: http://hyperphysics.phy-astr.gsu.edu/hbase/vision/cie.html**/ 
	// Method: PUT
	// http://192.168.2.110/api/qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M/lights/1/state
	// Body for..:
		//..Red: {"xy":[0.675,0.322]}
		//..Yellow: {"xy":[0.5425, 0.4196]}
		//..Green: {"xy":[0.41, 0.51721]}
		//..Blue: {"xy":[0.1691, 0.0441]}
		//..Purple/Pink: {"xy":[ 0.4149, 0.1776]}
	// Response:
//	[
//	    {
//	        "success": {
//	            "/lights/1/state/xy": [
//	                0.675,
//	                0.322
//	            ]
//	        }
//	    }
//	] 
	@RequestLine("PUT /api/{userID}/lights/{lampID}/state")
	@Headers("Content-Type: application/json")
	 // json curly braces must be escaped!
	@Body("%7B\"xy\": [{x},{y}]%7D") 
    void changeColor(@Param("userID") String userID, @Param("lampID") String lampID, @Param("x") Double x, @Param("y") Double y);
}
