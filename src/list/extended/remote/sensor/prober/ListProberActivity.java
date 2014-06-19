package list.extended.remote.sensor.prober;

import java.util.ArrayList;

import list.extended.remote.sensor.services.ServiceType;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListProberActivity extends Activity implements ISensorService{

  

    private final String LOG_TAG="ExtendedRemoteSensorProberActivity";
    private ListView lv = null;
    BaseItemListAdapter adapter=null;
    static long curr=0, last=0;
    
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        
        lv = (ListView)findViewById(R.id.listview);
        
        ArrayList<ItemInfo> l = new ArrayList<ItemInfo>();
        l.add(new ItemInfo(this,this, ServiceType.BATTERY, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.GPS, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.ACCELEROMETER, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.GRAVITY, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.MAGNETIC_FIELD, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.ORIENTATION, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.GYROSCOPE, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.LIGHT, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.PRESSURE, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.TEMPERATURE, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.PROXIMITY, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.ORIENTATION, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.LINEAR_ACCELERATION, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.ROTATION_VECTOR, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.RELATIVE_HUMIDITY, l.size()));
        l.add(new ItemInfo(this,this, ServiceType.AMBIENT_TEMPERATURE, l.size()));
        
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.remote_service_binding_item, l);
        adapter = new BaseItemListAdapter(this, R.layout.remote_service_binding_item, l);        
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {



			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
        });

        
        

    }
    
//////////////////////////

	@Override
	public void onServiceConnected(ServiceType type, int position) {
		// TODO Auto-generated method stub
		
		ItemInfo o = adapter.getItem(position);

			o.killbutton=true;
			o.unbindbutton=true;
			o.callbacktext="Attached..";
			adapter.notifyDataSetChanged();
			Toast.makeText(this, R.string.remote_service_connected,
                Toast.LENGTH_SHORT).show();
			
	}

	@Override
	public void onServiceDissconnected(ServiceType type, int position) {
		// TODO Auto-generated method stub
		ItemInfo o = adapter.getItem(position);
			o.killbutton=false;
			o.unbindbutton=false;
			o.callbacktext=o.callbacktext+"\nDisconnected.";
			adapter.notifyDataSetChanged();
			Log.d(LOG_TAG,"onServiceDissconnected: "+type);
			// As part of the sample, tell the user what happened.
			Toast.makeText(this, R.string.remote_service_disconnected,
                Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onServiceUnbound(ServiceType type, int position) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG,"onServiceUnbound: "+type);
			adapter.notifyDataSetChanged();

	}
	
	
	@Override
	public void onValueChanged(String newValue, ServiceType type, int position) {
		// TODO Auto-generated method stub
		ItemInfo o = adapter.getItem(position);
		curr=System.currentTimeMillis();

		//	Log.d(LOG_TAG, "msg "+newValue+ " "+type);
			o.callbacktext="Received from service:\n" + newValue+ " "+curr+" "+last;
			//mCallbackText1.setText("Received from service:\n" + newValue);
			if(Math.abs(curr-last)>500){
				adapter.notifyDataSetChanged();
				last=curr;

			}
		
		
	}
	
}



