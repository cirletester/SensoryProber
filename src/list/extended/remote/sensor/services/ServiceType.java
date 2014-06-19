package list.extended.remote.sensor.services;


public enum ServiceType {

ACCELEROMETER            (Service_Accelerometer.SENSOR_ID){    public String toString() {           		return "ACCELEROMETER";    }},
AMBIENT_TEMPERATURE      (Service_Accelerometer.SENSOR_ID){    public String toString() {       	return "AMBIENT_TEMPERATURE";    }},
GRAVITY                        (Service_Gravity.SENSOR_ID){    public String toString() {        				return "GRAVITY";    }},
GYROSCOPE                    (Service_Gyroscope.SENSOR_ID){    public String toString() {        			return "GYROSCOPE";    }},
LIGHT                            (Service_Light.SENSOR_ID){    public String toString() {        				return "LIGHT";    }},
LINEAR_ACCELERATION(Service_Linear_Acceleration.SENSOR_ID){    public String toString() {        	return "LINEAR_ACCELERATION";    }},
MAGNETIC_FIELD          (Service_Magnetic_Field.SENSOR_ID){    public String toString() {        		return "MAGNETIC_FIELD";    }},
ORIENTATION                (Service_Orientation.SENSOR_ID){    public String toString() {        			return "ORIENTATION";    }},
PRESSURE                      (Service_Pressure.SENSOR_ID){    public String toString() {        				return "PRESSURE";    }},
PROXIMITY                    (Service_Proximity.SENSOR_ID){    public String toString() {        			return "PROXIMITY";    }},
RELATIVE_HUMIDITY    (Service_Relative_Humidity.SENSOR_ID){    public String toString() {        	return "RELATIVE_HUMIDITY";    }},
ROTATION_VECTOR        (Service_Rotation_Vector.SENSOR_ID){    public String toString() {        		return "ROTATION_VECTOR";    }},
TEMPERATURE                (Service_Temperature.SENSOR_ID){    public String toString() {        			return "TEMPERATURE";    }},
GPS                                (Service_GPS.SENSOR_ID){    public String toString() {        					return "GPS";    }},
BATTERY                        (Service_Battery.SENSOR_ID){    public String toString() {        				return "BATTERY";    }};

private final int ServiceType; 
private ServiceType(int v) { ServiceType = v; } 
public int getInteger() { return ServiceType; }
}

