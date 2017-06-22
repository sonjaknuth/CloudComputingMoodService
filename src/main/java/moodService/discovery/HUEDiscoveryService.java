package moodService.discovery;

import java.util.List;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;

public class HUEDiscoveryService {
	static PHHueSDK phHueSDK = PHHueSDK.getInstance();
	static boolean bridgeFound = false;
	static String ipAddress = null;
	
	public static synchronized boolean isBridgeFound() {
		return bridgeFound;
	}
	public static synchronized void setBridgeFound(boolean bridgeFound1) {
		bridgeFound = bridgeFound1;
	}
	 // Local SDK Listener
    private static PHSDKListener listener = new PHSDKListener() {

        @Override
        public void onAccessPointsFound(List<PHAccessPoint> accessPoint) {
             // Handle your bridge search results here.  Typically if multiple results are returned you will want to display them in a list 
             // and let the user select their bridge.   If one is found you may opt to connect automatically to that bridge.    
        	System.out.println("Bridge found! --- Count: "+accessPoint.size());
        	PHAccessPoint phAccessPoint = accessPoint.get(0);
        	ipAddress = phAccessPoint.getIpAddress();
        	//phHueSDK.connect(phAccessPoint);
        	HUEDiscoveryService.setBridgeFound(true);
//			phHueSDK.connect(phAccessPoint);
        }
        
        @Override
        public void onCacheUpdated(List cacheNotificationsList, PHBridge bridge) {
             // Here you receive notifications that the BridgeResource Cache was updated. Use the PHMessageType to   
             // check which cache was updated, e.g.
            if (cacheNotificationsList.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
               System.out.println("Lights Cache Updated ");
            }
        }

        @Override
        public void onBridgeConnected(PHBridge b, String username) {
//           System.out.println("Bridge Connected!");
//        	phHueSDK.setSelectedBridge(b);
//            phHueSDK.enableHeartbeat(b, PHHueSDK.HB_INTERVAL);
//            bridgeFound = true;
            // Here it is recommended to set your connected bridge in your sdk object (as above) and start the heartbeat.
            // At this point you are connected to a bridge so you should pass control to your main program/activity.
            // The username is generated randomly by the bridge.
            // Also it is recommended you store the connected IP Address/ Username in your app here.  This will allow easy automatic connection on subsequent use. 
        }

        @Override
        public void onAuthenticationRequired(PHAccessPoint accessPoint) {
            phHueSDK.startPushlinkAuthentication(accessPoint);
            // Arriving here indicates that Pushlinking is required (to prove the User has physical access to the bridge).  Typically here
            // you will display a pushlink image (with a timer) indicating to to the user they need to push the button on their bridge within 30 seconds.
        }

        @Override
        public void onConnectionResumed(PHBridge bridge) {
        	System.out.println("Connection resumed");
        }

        @Override
        public void onConnectionLost(PHAccessPoint accessPoint) {
             // Here you would handle the loss of connection to your bridge.
        	System.out.println("Connection Lost");
        }
        
        @Override
        public void onError(int code, final String message) {
             // Here you can handle events such as Bridge Not Responding, Authentication Failed and Bridge Not Found
        	System.out.println("Brigde not responding");
        }

        @Override
        public void onParsingErrors(List parsingErrorsList) {
            // Any JSON parsing errors are returned here.  Typically your program should never return these.      
        }
    };
    
    public static String getIPAdress() {
		// phHueSDK.setAppName("HUEControl");
		 phHueSDK.setAppName("my_hue_app");  
		 phHueSDK.setDeviceName("Sonjas iPhone");
		 phHueSDK.getNotificationManager().registerSDKListener(listener);
		 PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
		 sm.search(true, true); 
		 while (!isBridgeFound()) {
				
		 }
		 phHueSDK.destroySDK();
		 return ipAddress;
//		// Store Information somewhere
//     	PHAccessPoint myAccessPoint = new PHAccessPoint();
//     	myAccessPoint.setIpAddress(phAccessPoint.getIpAddress());
//     	myAccessPoint.setUsername(phAccessPoint.getUsername());
//     	phHueSDK.connect(myAccessPoint);
	
		 
//		 PHBridgeResourcesCache cache = phHueSDK.getSelectedBridge().getResourceCache();
//		 // And now you can get any resource you want, for example:
//		 List myLights = cache.getAllLights();
//		 System.out.println("Lights found: "+myLights.size());
	
	}
    
    public static void main(String[] args) {
    	System.out.println("IP: "+ getIPAdress());
		return;
	}
	
}
