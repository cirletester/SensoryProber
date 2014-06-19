package list.extended.remote.sensor.services;

public enum ServiceControl {

ACCELEROMETER_CONTROL            (Service_Accelerometer.SENSOR_ID){    public String toString() { return "ACCELEROMETER_CONTROL";    }},
AMBIENT_TEMPERATURE_CONTROL      (Service_Accelerometer.SENSOR_ID){    public String toString() { return "AMBIENT_TEMPERATURE_CONTROL";    }},
GRAVITY_CONTROL                        (Service_Gravity.SENSOR_ID){    public String toString() { return "GRAVITY_CONTROL";    }},
GYROSCOPE_CONTROL                    (Service_Gyroscope.SENSOR_ID){    public String toString() { return "GYROSCOPE_CONTROL";    }},
LIGHT_CONTROL                            (Service_Light.SENSOR_ID){    public String toString() { return "LIGHT_CONTROL";    }},
LINEAR_ACCELERATION_CONTROL(Service_Linear_Acceleration.SENSOR_ID){    public String toString() { return "LINEAR_ACCELERATION_CONTROL";    }},
MAGNETIC_FIELD_CONTROL          (Service_Magnetic_Field.SENSOR_ID){    public String toString() { return "MAGNETIC_FIELD_CONTROL";    }},
ORIENTATION_CONTROL                (Service_Orientation.SENSOR_ID){    public String toString() { return "ORIENTATION_CONTROL";    }},
PRESSURE_CONTROL                      (Service_Pressure.SENSOR_ID){    public String toString() { return "PRESSURE_CONTROL";    }},
PROXIMITY_CONTROL                    (Service_Proximity.SENSOR_ID){    public String toString() { return "PROXIMITY_CONTROL";    }},
RELATIVE_HUMIDITY_CONTROL    (Service_Relative_Humidity.SENSOR_ID){    public String toString() { return "RELATIVE_HUMIDITY_CONTROL";    }},
ROTATION_VECTOR_CONTROL        (Service_Rotation_Vector.SENSOR_ID){    public String toString() { return "ROTATION_VECTOR_CONTROL";    }},
TEMPERATURE_CONTROL                (Service_Temperature.SENSOR_ID){    public String toString() { return "TEMPERATURE_CONTROL";    }},
GPS_CONTROL                                (Service_GPS.SENSOR_ID){    public String toString() { return "GPS_CONTROL";    }},
BATTERY_CONTROL                        (Service_Battery.SENSOR_ID){    public String toString() { return "BATTERY_CONTROL";    }};

private final int SERVICE_CONTROL_OFFSET = 100;
private final int ServiceControl; 
private ServiceControl(int v) { ServiceControl = v * SERVICE_CONTROL_OFFSET; } 
public int getInteger() { return ServiceControl; }
}