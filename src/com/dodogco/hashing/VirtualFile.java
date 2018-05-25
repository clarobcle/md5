package com.dodogco.hashing;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface VirtualFile extends Remote {
	
	File getFile() throws RemoteException;
	String getMD5() throws RemoteException;
}
