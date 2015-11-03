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
 * Track.java
 *
 * Description:	    <Descripcion>
 * @author			Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 11 de ago. de 2015, 1:00:35 p. m. 
 */

package ar.com.dcbarrientos.m3ugenerator;

import com.beaglebuddy.mp3.MP3;


/**
 * @author Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class Track {
	int numero;
	String nombre;
	String album;
	String artista;
	int duracion;
	String path;
	String fileName;
	int orden;
	boolean extendido;
	public static final int CANTIDAD_DATOS = 6;
	
	public Track(){
		
	}
	
	public Track(MP3 mp3, boolean extendido){
		this.extendido = extendido;
		loadInfo(mp3);

	}
	
	public Track(int numero, String nombre, String album, String artista, int duracion, String path, int orden, boolean extendido) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.album = album;
		this.artista = artista;
		this.duracion = duracion;
		this.path = path;		
		this.orden = orden;
		this.extendido = extendido;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String carpeta) {
		this.path = carpeta;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return fileName;
	}
	/*
	public String getFileName(){
		return path.substring(path.lastIndexOf('\\')+1);
	}
	*/
	public int getOrden(){
		return orden;
	}
	
	public void setOrden(int orden){
		this.orden = orden;
	}
	
	
	public boolean getExtendido(){
		return extendido;
	}
	
	public void setExtendido(boolean extendido){
		this.extendido = extendido;
	}
	
	private void loadInfo(MP3 mp3){
		//this.path = mp3.getPath();
		if(extendido){
			this.numero = mp3.getTrack();
			this.nombre = mp3.getTitle();
			this.album = mp3.getAlbum();
			this.artista = mp3.getLeadPerformer();
			if(mp3.getAudioDuration()==0){			//Verifico si se especificó la duración en el archivo.
				mp3.setAudioDuration();				//Calcula la duración del audio.
			}
			this.duracion = mp3.getAudioDuration();
		}
	}
	
}
