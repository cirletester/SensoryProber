/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Enrique\\OfficeWork9\\SensorProber\\src\\list\\extended\\remote\\sensor\\services\\IRemoteServiceControl.aidl
 */
package list.extended.remote.sensor.services;
/**
 * Scondary interface associated with a service.  (Note that the interface 
 * itself doesn't impact, it is just a matter of how you retrieve it from 
 * the service.)
 */
public interface IRemoteServiceControl extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements list.extended.remote.sensor.services.IRemoteServiceControl
{
private static final java.lang.String DESCRIPTOR = "list.extended.remote.sensor.services.IRemoteServiceControl";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an list.extended.remote.sensor.services.IRemoteServiceControl interface,
 * generating a proxy if needed.
 */
public static list.extended.remote.sensor.services.IRemoteServiceControl asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof list.extended.remote.sensor.services.IRemoteServiceControl))) {
return ((list.extended.remote.sensor.services.IRemoteServiceControl)iin);
}
return new list.extended.remote.sensor.services.IRemoteServiceControl.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getPid:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getPid();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements list.extended.remote.sensor.services.IRemoteServiceControl
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Request the PID of this service, to do evil things with it.
     */
@Override public int getPid() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPid, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getPid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * Request the PID of this service, to do evil things with it.
     */
public int getPid() throws android.os.RemoteException;
}
