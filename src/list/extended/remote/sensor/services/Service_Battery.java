package list.extended.remote.sensor.services;

import list.extended.remote.sensor.prober.R;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class Service_Battery  extends Service {
	
	private final String LOG_TAG=this.getClass().getName();
	
	private BroadcastReceiver mBatInfoReceiver;
	
	public static final int SENSOR_ID = 2;
	
	ServiceType servicetype;
	
    /**
     * This is a list of callbacks that have been registered with the
     * service.  Note that this is package scoped (instead of private) so
     * that it can be accessed more efficiently from inner classes.
     */

	final RemoteCallbackList<IRemoteServiceCallback> mCallbacks
            = new RemoteCallbackList<IRemoteServiceCallback>();
    
    NotificationManager mNM;
    
    @Override
    public void onCreate() {
    	Log.d(LOG_TAG,"onCreate");
        
    	mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
          
        sensorScanner();
 

    }

    
    @Override
    public void onDestroy() {
    	Log.d(LOG_TAG,"onDestroy");
        // Cancel the persistent notification.
        mNM.cancel(R.string.remote_service_started);

        // Tell the user we stopped.
        Toast.makeText(this, R.string.remote_service_stopped, Toast.LENGTH_SHORT).show();
        
       try{
        	if( mBatInfoReceiver !=null){
        		getApplicationContext().unregisterReceiver(mBatInfoReceiver);
        		Log.d(LOG_TAG,"We have unregister sensor manger");
        	}
        }catch(Exception ex){
        	Log.d(LOG_TAG,"We failed while unregistering listener");
        	ex.printStackTrace();
        	
        }
        // Unregister all callbacks.
        mCallbacks.kill();
    }
    

    @Override
    public IBinder onBind(Intent intent) {
    	Log.d(LOG_TAG,"onBind: "+intent.getAction());
        // Select the interface to return.
    	
    	new ServiceIntentType(mServiceBinder, mControlBinder);
    	servicetype = ServiceIntentType.getServiceType(intent.getAction());
    	return ServiceIntentType.getServiceBinder(intent.getAction());
 
    }

    
    /**
     * The IRemoteInterface is defined through IDL
     */
    private final IRemoteService.Stub mServiceBinder = new IRemoteService.Stub() {
        public void registerCallback(IRemoteServiceCallback cb) {
        	Log.d(LOG_TAG,"registerCallback");
            if (cb != null) mCallbacks.register(cb);
        }
        public void unregisterCallback(IRemoteServiceCallback cb) {
        	Log.d(LOG_TAG,"unregisterCallback");
        	if (cb != null) mCallbacks.unregister(cb);
        }
    };

    /**
     * A secondary interface to the service.
     */
    private final IRemoteServiceControl.Stub mControlBinder = new IRemoteServiceControl.Stub() {
        public int getPid() {
            return Process.myPid();
        }
        
    };

    private void sensorScanner(){
    	Log.d(LOG_TAG,"sensorScanner");
    	
    	mBatInfoReceiver = new BroadcastReceiver(){
    		@Override
    		public void onReceive(Context arg0, Intent intent) {
    			try {
    				int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
	    			String sensorvalue = Integer.toString(level);
					//Log.d(LOG_TAG, sensorEvent.values[0]+" "+sensorEvent.values[1]+" "+sensorEvent.values[2]);
    				final int N = mCallbacks.beginBroadcast();
    				//Log.d(LOG_TAG,N+" +On Sensor Change 1 "+ Thread.currentThread().getName()+">"+sensorEvent.values[0]);
    				for (int i=0; i<N; i++) {
    					try {
    						Log.d(LOG_TAG, N+" Battery Charge Changed ..."+ Thread.currentThread().getName()+">"+sensorvalue);
    		    			
    						mCallbacks.getBroadcastItem(i).valueChanged(sensorvalue);
    						
    					} catch (RemoteException e) {
    						// The RemoteCallbackList will take care of removing
    						// the dead object for us.
    						e.printStackTrace();
    					}
    				}
    				mCallbacks.finishBroadcast();
				 
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		}
    	};
		
		getApplicationContext().registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));   	
    }
    
    
}