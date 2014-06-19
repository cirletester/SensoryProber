package list.extended.remote.sensor.management;

import list.extended.remote.sensor.services.IRemoteService;
import list.extended.remote.sensor.services.IRemoteServiceCallback;
import list.extended.remote.sensor.services.IRemoteServiceControl;
import list.extended.remote.sensor.services.ServiceIntentType;
import list.extended.remote.sensor.services.ServiceType;
import list.extended.remote.sensor.prober.ISensorService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Process;
import android.os.RemoteException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * Transactionality added for binding and unbinding to the remote service.
 */

public class BindingService {
    /** The primary interface we will be calling on the service. */
    IRemoteService mService = null;
    /** Another interface we use on the service. */
    IRemoteServiceControl mServiceControl = null;
    
    private boolean mIsBound;
    private final String LOG_TAG="Binding";

    ISensorService myActivity;
    
    Context myContext;
    
    ServiceConnection mSecondaryConnection = null;
    
    ServiceConnection mConnection = null;
    
    IRemoteServiceCallback mCallback = null;
    
    ServiceType servicetype;
    
    int position;
    ///////////
    
     public BindingService(ISensorService parent, Context context, ServiceType type, int pos){
	
    	 myActivity = parent;
    	 myContext = context;
    	 servicetype = type;
    	 position = pos;
         /**
          * Class for interacting with the secondary interface of the service.
          */
    	 if (mSecondaryConnection==null)
    		 mSecondaryConnection = new ServiceConnection() {
             	public void onServiceConnected(ComponentName className, IBinder service) {
                 // Connecting to a secondary interface is the same as any
                 // other interface.
             		Log.d(LOG_TAG,"mSecondaryConnection onServiceConnected");
             		mServiceControl = IRemoteServiceControl.Stub.asInterface(service);
             }

             public void onServiceDisconnected(ComponentName className) {
             		Log.d(LOG_TAG,"mSecondaryConnection onServiceDissconnected");
             		mServiceControl = null;
             }
         };

         if(mConnection == null)
        	 mConnection = new ServiceConnection() {
             	public void onServiceConnected(ComponentName className,IBinder service) {
                 // This is called when the connection with the service has been
                 // established, giving us the service object we can use to
                 // interact with the service.  We are communicating with our
                 // service through an IDL interface, so get a client-side
                 // representation of that from the raw service object.
               	 Log.d(LOG_TAG, "mConnection serviceConnection: "+" "+servicetype);
           		 Log.d(LOG_TAG,"++onServiceConnected: "+className.getClassName().getClass().getName()+" "+service.getClass().getName());
             		mService = IRemoteService.Stub.asInterface(service);

                 // We want to monitor the service for as long as we are
                 // connected to it.
             		try {
             			Log.d(LOG_TAG, "+About to register callback: "+ servicetype);
             			mService.registerCallback(mCallback);
             			Log.d(LOG_TAG, "-About to register callback: "+ servicetype);

             			myActivity.onServiceConnected(servicetype, position);
                 	
                 		
             		} catch (Exception e) {
             			Log.d(LOG_TAG,"->"+e.getMessage());
             			e.printStackTrace();
                     // In this case the service has crashed before we could even
                     // do anything with it; we can count on soon being
                     // disconnected (and then reconnected if it can be restarted)
                     // so there is no need to do anything here.
             		}
             	}

     		@Override
            public void onServiceDisconnected(ComponentName className) {
     			Log.d(LOG_TAG, "mConnection onServiceDisconnected.: "+" "+servicetype);
                // This is called when the connection with the service has been
                // unexpectedly disconnected -- that is, its process crashed.
                mService = null;
                myActivity.onServiceDissconnected(servicetype, position);
            }

         }; 
         
         /**
          * This implementation is used to receive callbacks from the remote
          * service.
          */
         if(mCallback == null)
        	 mCallback = new IRemoteServiceCallback.Stub() {
     			@Override
     			public void valueChanged(String value) throws RemoteException {
     				// TODO Auto-generated method stub
     				mHandler.sendMessage(mHandler.obtainMessage(BUMP_MSG, value));
             		//Log.d(LOG_TAG,"RemoteService--->"+value);
     			}
         };
     }
     

    
     public void bindServices(){
    	// Establish a couple connections with the service.
    	 	Log.d(LOG_TAG, "bindServices");
    	 	Log.d(LOG_TAG, "Start->ThenBind---->mBindListener."+IRemoteService.class.getName());
  	 
    	 	myContext.bindService(new Intent(ServiceIntentType.getService(servicetype)), mConnection, Context.BIND_AUTO_CREATE);
    	 								   
			Log.d(LOG_TAG, "---->mBindListener."+IRemoteServiceControl.class.getName());
			myContext.bindService(new Intent(ServiceIntentType.getServiceControl(servicetype)),mSecondaryConnection, Context.BIND_AUTO_CREATE);

			mIsBound = true;    	 
     }

     public void unbindServices(){
    	 Log.d(LOG_TAG, "unbindServices");
 		if (mIsBound) {
			// If we have received the service, and hence registered with
			// it, then now is the time to unregister.
 			Log.d(LOG_TAG, "mIsBound =true");
			if (mService != null) {
				try {
					mService.unregisterCallback(mCallback);
					Log.d(LOG_TAG, "We have unregister callback");
					// Detach our existing connection.
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			myContext.unbindService(mConnection);
			myContext.unbindService(mSecondaryConnection);
			myActivity.onServiceUnbound(servicetype, position);
			mIsBound = false;

		}

     }
     
         // ----------------------------------------------------------------------
         // Code showing how to deal with callbacks.
         // ----------------------------------------------------------------------
         private static final int BUMP_MSG = 1;
         
         
         private Handler mHandler = new Handler() {
             @Override public  void handleMessage(Message msg) {
                 switch (msg.what) {
                     case BUMP_MSG:
                     	Log.d(LOG_TAG, msg.obj+" + "+System.currentTimeMillis()+" "+servicetype);
                         myActivity.onValueChanged((String) msg.obj, servicetype, position);
                         break;
                     default:
                         super.handleMessage(msg);
                 }
             }
             
         };
         

     
     
   /*  public ServiceConnection getMainService(){
    	 return mConnection;
     }
     
     public ServiceConnection getSecondService(){
    	 return mSecondaryConnection;
    	 
     }*/

     public boolean isSecondServiceDefined()
     {
    	 if(mSecondaryConnection == null)
    		 return false;
    	 return true;
    				 
     }
     
     public void killprocess(){
    	 
    	 try{
			int pid = getPid();
			// Note that, though this API allows us to request to
			// kill any process based on its PID, the kernel will
			// still impose standard restrictions on which PIDs you
			// are actually able to kill.  Typically this means only
			// the process running your application and any additional
			// processes created by that app as shown here; packages
			// sharing a common UID will also be able to kill each
			// other's processes.
			Process.killProcess(pid);
    	 }catch(Exception ex){
    		 
    		 ex.printStackTrace();
    	 }
     }
     
     private int getPid(){
    	 int retValue = -1;
		 Log.d(LOG_TAG,"getPid: "+ servicetype);    	 
    	 try {

    		 retValue = mServiceControl.getPid();
    		 Log.d(LOG_TAG,"getPid: "+ retValue); 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return retValue;
     }
     
    /**
     * Class for interacting with the main interface of the service.
     */

}

/**/
