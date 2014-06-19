package list.extended.remote.sensor.services;

import android.os.IBinder;
import android.util.Log;

public class ServiceIntentType {

	private static IRemoteServiceControl.Stub mycontrol = null; 
	private static IRemoteService.Stub myservice = null;
	private static final String LOG_TAG =  "ServiceIntentType";

	
	public ServiceIntentType( IRemoteService.Stub serv, IRemoteServiceControl.Stub contr){
		mycontrol = contr;
		myservice = serv;
	}
	
	
	public static IBinder getServiceBinder(String action){
		IBinder temp = null;

		if( action.equals(ServiceType.ACCELEROMETER.toString())||
			action.equals(ServiceType.AMBIENT_TEMPERATURE.toString())||
		   	action.equals(ServiceType.GRAVITY.toString())||
		   	action.equals(ServiceType.GYROSCOPE.toString())||
		   	action.equals(ServiceType.LIGHT.toString())||
		   	action.equals(ServiceType.LINEAR_ACCELERATION.toString())||
		   	action.equals(ServiceType.MAGNETIC_FIELD.toString())||
		   	action.equals(ServiceType.ORIENTATION.toString())||
		   	action.equals(ServiceType.PRESSURE.toString())||
		   	action.equals(ServiceType.PROXIMITY.toString())||
		   	action.equals(ServiceType.RELATIVE_HUMIDITY.toString())||
		   	action.equals(ServiceType.ROTATION_VECTOR.toString())||
		   	action.equals(ServiceType.TEMPERATURE.toString())||
		   	action.equals(ServiceType.GPS.toString())||
		   	action.equals(ServiceType.BATTERY.toString())){
			Log.d(LOG_TAG,"We got a Service");
				temp = myservice;
		}

		if( action.equals(ServiceControl.ACCELEROMETER_CONTROL.toString())||
			action.equals(ServiceControl.AMBIENT_TEMPERATURE_CONTROL.toString())||
			action.equals(ServiceControl.GRAVITY_CONTROL.toString())||
			action.equals(ServiceControl.GYROSCOPE_CONTROL.toString())||
			action.equals(ServiceControl.LIGHT_CONTROL.toString())||
			action.equals(ServiceControl.LINEAR_ACCELERATION_CONTROL.toString())||
			action.equals(ServiceControl.MAGNETIC_FIELD_CONTROL.toString())||
			action.equals(ServiceControl.ORIENTATION_CONTROL.toString())||
			action.equals(ServiceControl.PRESSURE_CONTROL.toString())||
			action.equals(ServiceControl.PROXIMITY_CONTROL.toString())||
			action.equals(ServiceControl.RELATIVE_HUMIDITY_CONTROL.toString())||
			action.equals(ServiceControl.ROTATION_VECTOR_CONTROL.toString())||
			action.equals(ServiceControl.TEMPERATURE_CONTROL.toString())||
			action.equals(ServiceControl.GPS_CONTROL.toString())||
			action.equals(ServiceControl.BATTERY_CONTROL.toString())){
			Log.d(LOG_TAG,"We got a ServiceControl");
					temp = mycontrol;
			}	
		
		if(temp==null)
			Log.d(LOG_TAG,"Oh shit!");
		
		return temp;
	}


	public static String getServiceControl(ServiceType servicetype){
		
		String temp = null;
		
		switch(servicetype){
		
		case ACCELEROMETER:
			temp = ServiceControl.ACCELEROMETER_CONTROL.toString();
			break;
		case LINEAR_ACCELERATION:
			temp = ServiceControl.LINEAR_ACCELERATION_CONTROL.toString();
			break;
		case AMBIENT_TEMPERATURE:
			temp = ServiceControl.AMBIENT_TEMPERATURE_CONTROL.toString();
			break;
		case GRAVITY:
			temp = ServiceControl.GRAVITY_CONTROL.toString();
			break;
		case GYROSCOPE:
			temp = ServiceControl.GYROSCOPE_CONTROL.toString();
			break;
		case LIGHT:
			temp = ServiceControl.LIGHT_CONTROL.toString();
			break;
		case MAGNETIC_FIELD:
			temp = ServiceControl.MAGNETIC_FIELD_CONTROL.toString();
			break;
		case ORIENTATION:
			temp = ServiceControl.ORIENTATION_CONTROL.toString();
			break;
		case PRESSURE:
			temp = ServiceControl.PRESSURE_CONTROL.toString();
			break;
		case PROXIMITY:
			temp = ServiceControl.PROXIMITY_CONTROL.toString();
			break;
		case RELATIVE_HUMIDITY:
			temp = ServiceControl.RELATIVE_HUMIDITY_CONTROL.toString();	
			break;
		case ROTATION_VECTOR:
			temp = ServiceControl.ROTATION_VECTOR_CONTROL.toString();
			break;
		case TEMPERATURE:
			temp = ServiceControl.TEMPERATURE_CONTROL.toString();
			break;
		case GPS:
			temp = ServiceControl.GPS_CONTROL.toString();
			break;
		case BATTERY:
			temp = ServiceControl.BATTERY_CONTROL.toString();
			break;
		}
		return temp;
	}
	
	public static String getService(ServiceType servicetype){
		
		String temp = null;
		switch(servicetype){
		case ACCELEROMETER:
			temp = ServiceType.ACCELEROMETER.toString();
			break;
		case LINEAR_ACCELERATION:
			temp = ServiceType.LINEAR_ACCELERATION.toString();
			break;
		case AMBIENT_TEMPERATURE:
			temp = ServiceType.AMBIENT_TEMPERATURE.toString();
			break;
		case GRAVITY:
			temp = ServiceType.GRAVITY.toString();
			break;
		case GYROSCOPE:
			temp = ServiceType.GYROSCOPE.toString();
			break;
		case LIGHT:
			temp = ServiceType.LIGHT.toString();
			break;
		case MAGNETIC_FIELD:
			temp = ServiceType.MAGNETIC_FIELD.toString();
			break;
		case ORIENTATION:
			temp = ServiceType.ORIENTATION.toString();
			break;
		case PRESSURE:
			temp = ServiceType.PRESSURE.toString();
			break;
		case PROXIMITY:
			temp = ServiceType.PROXIMITY.toString();
			break;
		case RELATIVE_HUMIDITY:
			temp = ServiceType.RELATIVE_HUMIDITY.toString();
			break;
		case ROTATION_VECTOR:
			temp = ServiceType.ROTATION_VECTOR.toString();
			break;
		case TEMPERATURE:
			temp = ServiceType.TEMPERATURE.toString();
			break;
		case GPS:
			temp = ServiceType.GPS.toString();
			break;
		case BATTERY:
			temp = ServiceType.BATTERY.toString();
			break;
			
		}
		
		return temp;
	}
	
	public static ServiceType getServiceType(String action){
		
		ServiceType temp = null;
		Log.d(LOG_TAG,"My Action"+action);
		if(action.equals(ServiceType.ACCELEROMETER.getInteger())){			
			temp = ServiceType.ACCELEROMETER;
		}
		if(action.equals(ServiceType.LINEAR_ACCELERATION.toString())){			
			temp = ServiceType.LINEAR_ACCELERATION;
		}
		if(action.equals(ServiceType.AMBIENT_TEMPERATURE.toString())){			
			temp = ServiceType.AMBIENT_TEMPERATURE;
		}
		if(action.equals(ServiceType.GRAVITY.toString())){			
			temp = ServiceType.GRAVITY;
		}
		if(action.equals(ServiceType.GYROSCOPE.toString())){			
			temp = ServiceType.GYROSCOPE;
		}
		if(action.equals(ServiceType.LIGHT.toString())){			
			temp = ServiceType.LIGHT;
		}
		if(action.equals(ServiceType.MAGNETIC_FIELD.toString())){			
			temp = ServiceType.MAGNETIC_FIELD;
		}
	
		if(action.equals(ServiceType.ORIENTATION.toString())){			
			temp = ServiceType.MAGNETIC_FIELD;
		}
		if(action.equals(ServiceType.PRESSURE.toString())){			
			temp = ServiceType.PRESSURE;
		}
		if(action.equals(ServiceType.PROXIMITY.toString())){			
			temp = ServiceType.PROXIMITY;
		}
		if(action.equals(ServiceType.RELATIVE_HUMIDITY.toString())){			
			temp = ServiceType.RELATIVE_HUMIDITY;
		}
		if(action.equals(ServiceType.ROTATION_VECTOR.toString())){			
			temp = ServiceType.ROTATION_VECTOR;
		}
		if(action.equals(ServiceType.TEMPERATURE.toString())){			
			temp = ServiceType.TEMPERATURE;
		}
		if(action.equals(ServiceType.GPS.toString())){			
			temp = ServiceType.GPS;
		}
		if(action.equals(ServiceType.BATTERY.toString())){			
			temp = ServiceType.BATTERY;
		}
		
		
		return temp;
		
	}
	
}
