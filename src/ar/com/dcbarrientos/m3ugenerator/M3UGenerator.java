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
 * M3UGenerator.java
 *
 * Description:	    <Descripcion>
 * @author			Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 11 de ago. de 2015, 11:24:12 a.�m. 
 */

package ar.com.dcbarrientos.m3ugenerator;

import java.awt.EventQueue;

import ar.com.dcbarrientos.m3ugenerator.ide.Ventana;

/**
 * @author Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class M3UGenerator {
	public static final String TITLE = "M3U Generator";

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		
		try {
			MP3 mp3 = new MP3("G:/Musica/Ori and the Blind Forest (Original Soundtrack) (2015)/02 Naru, Embracing the Light (feat..mp3");
			
			//Verifico si hay errores
			if(mp3.hasErrors()){
				mp3.displayErrors(System.out);		//Muestra el error encontrado
				mp3.save();							//descarto informaci�n inv�lida y grabo s�lo
			}										//la v�lida en el archivo mp3
			
			if(mp3.getAudioDuration()==0){			//Verifico si se especific� la duraci�n en el archivo.
				mp3.setAudioDuration();				//Calcula la duraci�n del audio.
			}
			
			System.out.println(mp3);
			
			System.out.println("#EXTM3U\n");

			System.out.println("#EXTINF:" + mp3.getAudioDuration() + "," + mp3.getTitle());
			System.out.println(mp3.getPath());
			
			System.out.printf("\tN�: " + mp3.getTrack() + "\n\tNombre: " + mp3.getTitle() + "\n\tAlbum: " + mp3.getAlbum() + "\n\tArtista: " + mp3.getLeadPerformer());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
