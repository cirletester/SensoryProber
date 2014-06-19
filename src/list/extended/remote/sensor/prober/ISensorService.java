package list.extended.remote.sensor.prober;
import list.extended.remote.sensor.services.*;

public interface ISensorService {

	public void onServiceConnected(ServiceType type, int position);
	public void onServiceDissconnected(ServiceType type, int position);
	public void onServiceUnbound(ServiceType type, int position);
	public void onValueChanged(String newValue, ServiceType type, int position);

}
