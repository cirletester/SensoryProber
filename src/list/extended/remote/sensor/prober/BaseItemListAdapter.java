package list.extended.remote.sensor.prober;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class BaseItemListAdapter extends ArrayAdapter<ItemInfo>{
	private ArrayList<ItemInfo> items = null;
	private final String LOG_TAG = "BaseItemListAdapter ";

    ///////
    Button mKillButton1;
    Button mbindbutton1;
    Button munbindbutton1;
    TextView mCallbackText1;
    Context myContext;
    TextView title;
    ///////
    
	public BaseItemListAdapter(Context context, int textViewResourceId, ArrayList<ItemInfo> items){
		super(context,textViewResourceId,items);
		this.items=items;
		this.myContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.remote_service_binding_item, null);
		}
		ItemInfo o = items.get(position);
		if (o != null) {

			title = (TextView) v.findViewById(R.id.myTextViewId);
			title.setText(o.getSensor());
			mbindbutton1 = (Button) v.findViewById(R.id.bind);
			mbindbutton1.setOnClickListener(o.mBindListener1);
			mbindbutton1.setEnabled(o.bindbutton);
			munbindbutton1 = (Button)v.findViewById(R.id.unbind);
			munbindbutton1.setOnClickListener(o.mUnbindListener1);
			munbindbutton1.setEnabled(o.unbindbutton);
			mKillButton1 = (Button)v.findViewById(R.id.kill);
			mKillButton1.setOnClickListener(o.mKillListener1);
			mKillButton1.setEnabled(o.killbutton);     
			mCallbackText1 = (TextView)v.findViewById(R.id.callback);
			mCallbackText1.setText(o.callbacktext);        

			Log.d(LOG_TAG,"Got the view");
		}

			return v;
		
	}
}
	