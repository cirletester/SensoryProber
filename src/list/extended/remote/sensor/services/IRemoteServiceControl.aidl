/*
 * License to CMU
 */

package list.extended.remote.sensor.services;

/**
 * Scondary interface associated with a service.  (Note that the interface 
 * itself doesn't impact, it is just a matter of how you retrieve it from 
 * the service.)
 */
interface IRemoteServiceControl {
    /**
     * Request the PID of this service, to do evil things with it.
     */
    int getPid();
    
}
