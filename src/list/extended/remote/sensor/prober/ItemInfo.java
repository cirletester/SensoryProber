package list.extended.remote.sensor.prober;

import list.extended.remote.sensor.management.BindingService;
import list.extended.remote.sensor.services.*;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ItemInfo {
	
		private final String LOG_TAG="ItemInfo";
		private Context context=null;
		
		public boolean killbutton;
		public boolean unbindbutton;
		public boolean bindbutton=true;
		public String callbacktext="";
		
		public OnClickListener mBindListener1, mUnbindListener1 , mKillListener1;
		
		ServiceType servicetype;
		BindingService mybind;
		//mybindingservice1 = new BindingService(this, myContext, Sensor.TYPE_ACCELEROMETER);
		public ItemInfo(Context cnt, ISensorService mySrvc, ServiceType sentype, int pos){
			context = cnt;
			servicetype = sentype;
			mybind = new BindingService(mySrvc, cnt, sentype, pos);
		
		
		
		
		mBindListener1 = new OnClickListener() {
			public void onClick(View v) {
				Log.d(LOG_TAG,"Here");
				mybind.bindServices();
				callbacktext="Binding.";
				bindbutton=false;
				killbutton = true;
				unbindbutton = true;
			}
		};

		 mUnbindListener1 = new OnClickListener() {
			public void onClick(View v) {
				mybind.unbindServices();  			
				callbacktext= "Unbinding.";
				bindbutton = true;
				killbutton = true;
				unbindbutton = false;
			}
		};

		 mKillListener1 = new OnClickListener() {
			public void onClick(View v) {
				// To kill the process hosting our service, we need to know its
				// PID.  Conveniently our service has a call that will return
				// to us that information.
				if (mybind.isSecondServiceDefined() ) {
					try {
						mybind.killprocess();
						callbacktext = "Killed service process.";
					} catch (Exception ex) {
						// Recover gracefully from the process hosting the
						// server dying.
						// Just for purposes of the sample, put up a notification.
						ex.printStackTrace();
						Toast.makeText(context,
								R.string.remote_call_failed,
								Toast.LENGTH_SHORT).show();
					}
				}else
					Toast.makeText(context,
							"It was null",
							Toast.LENGTH_SHORT).show();

			}
		};
		}
		public String getSensor(){
		return this.servicetype.toString();
		}

}