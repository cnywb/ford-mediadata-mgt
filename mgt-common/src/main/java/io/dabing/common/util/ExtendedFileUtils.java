package io.dabing.common.util;

import org.apache.commons.io.FileUtils;

import java.io.File;


public class ExtendedFileUtils extends FileUtils{
	
	
	public  static void makeDirs(String dirs){
		File saveDirFile = new File(dirs);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
	}

	public static Integer countFile(String dirs){
		File saveDirFile = new File(dirs);
		if (saveDirFile.exists()) {
		  return saveDirFile.listFiles().length;
		}
		return 0;
	}
	public static Integer countFile(String dirs,String extendFileName){
		File saveDirFile = new File(dirs);
		Integer counter=0;
		if (saveDirFile.exists()) {
			File[] files=saveDirFile.listFiles();
			for(File file :files){
				if(file.getName().endsWith(extendFileName)){
					counter=counter+1;
				}
			}


		}
		return counter;
	}

}
