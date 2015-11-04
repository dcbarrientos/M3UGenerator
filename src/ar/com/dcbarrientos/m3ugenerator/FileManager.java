/*
 *  Copyright (C) 2015 Diego Barrientos <dc_barrientos@yahoo.com.ar>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/** 
 * FileManager.java
 *
 * Description:	    <Descripcion>
 * @author			Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 12 de ago. de 2015, 1:34:29 p. m. 
 */

package ar.com.dcbarrientos.m3ugenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * Generador de archivo m3u en base a la lista de temas.
 * @author Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class FileManager {
	private String nombreArchivo = null;
	private Vector<Track> datos = null;
	private boolean includePath = false;
	
	public FileManager(String nombreArchivo, Vector<Track> datos, boolean includePath){
		this.nombreArchivo = nombreArchivo;
		this.datos = new Vector<Track>(datos);
		this.includePath = includePath;
	}

	public boolean save(){
		boolean resu = true;
		File archivo = new File(nombreArchivo);
		if(archivo.exists()){
			int r = JOptionPane.showConfirmDialog(	null, 
													"El archivo ya existe. ¿Desea sobreescribir?", 
													M3UGenerator.TITLE, 
													JOptionPane.YES_NO_OPTION, 
													JOptionPane.QUESTION_MESSAGE);
			if(r == JOptionPane.NO_OPTION){
				return false;
			}
		}
		
		try {
			BufferedWriter buffer = new BufferedWriter(new FileWriter(archivo));
			buffer.write("#EXTM3U\n\n");
			Iterator<Track> it = datos.iterator();
			while(it.hasNext()){
				Track dato = it.next();
				
				buffer.write("#EXTINF:");
				buffer.write(Integer.toString(dato.getDuracion()));
				buffer.write(",");
				buffer.write(dato.getNombre());
				buffer.write('\n');
				
				if(includePath)
					buffer.write(dato.getPath());
				buffer.write(dato.getFileName()+ '\n');
				
			}
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return resu;
	}
}
