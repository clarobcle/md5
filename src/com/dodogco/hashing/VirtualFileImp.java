package com.dodogco.hashing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VirtualFileImp extends UnicastRemoteObject
implements VirtualFile {

	private static final long serialVersionUID = -4842526464612109139L;
	private File file;
	private String hash;

	public VirtualFileImp(String path) throws RemoteException {
		super();
		this.file = new File(path);
		try {
			loadHash();
		} catch (NoSuchAlgorithmException | IOException e) {
			System.err.println("Failure on loading Hash");
			e.printStackTrace();
			this.hash = "null";
		}
	}

	private void loadHash() throws NoSuchAlgorithmException, IOException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = null;
		DigestInputStream dis = null;
		
		try {
			fis = new FileInputStream(file);
			dis = new DigestInputStream(fis, md);
			
			byte[] mdbytes = md.digest();
			
			StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	        	// look he's using and bitwise yep where there is a bitwise
	        	//                                   v here
	          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        hash = sb.toString().toUpperCase();
			
		} finally {
			if(dis != null) dis.close();
			if(fis != null) fis.close();
		}
		
	}

	@Override
	public File getFile() throws RemoteException {
		return file;
	}

	@Override
	public String getMD5() throws RemoteException {
		return hash;
	}

	@Override
	public String toString() {
		return "VirtualFileImp(" + file.getPath() +", hash: "+ hash+")";
	}

}
