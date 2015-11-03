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
 * tblTemasModel.java
 *
 * Description:	    <Descripcion>
 * @author			Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 * Created on 12 de ago. de 2015, 12:07:31 p. m. 
 */

package ar.com.dcbarrientos.m3ugenerator.ide;

import ar.com.dcbarrientos.m3ugenerator.Track;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * @author Diego Barrientos <dc_barrientos@yahoo.com.ar>
 *
 */
public class tblTemasModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int COLUMNA_NUMERO = 0;
	public static final int COLUMNA_NOMBRE = 1;
	public static final int COLUMNA_ALBUM = 2;
	public static final int COLUMNA_ARTISTA = 3;
	public static final int COLUMNA_CARPETA = 4;
	public static final int COLUMNA_DURACION = 5;
	
	
	String header[] = null;
	Vector<Track> datos = null;
	
	public void setData(Vector<Track> v){
		datos = new Vector<Track>(v);
		this.fireTableDataChanged();
	}
	
	public void setHeader(String[] h){
		header = new String[h.length];
		System.arraycopy(h, 0, header, 0, h.length);
		this.fireTableChanged(null);
	}
	
	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Object d = null;
		if(columna==COLUMNA_NUMERO)
			d = datos.elementAt(fila).getNumero();
		else if(columna==COLUMNA_NOMBRE)
			d = datos.elementAt(fila).getNombre();
		else if(columna==COLUMNA_ALBUM)
			d = datos.elementAt(fila).getAlbum();
		else if(columna==COLUMNA_ARTISTA)
			d = datos.elementAt(fila).getArtista();
		else if(columna==COLUMNA_CARPETA)
			d = datos.elementAt(fila).getPath();

		return d;
	}
		
	@Override
	public String getColumnName(int column){
		return header[column];
	}
	
	public void removeRow(int fila){
		datos.remove(fila);
		this.fireTableDataChanged();
	}
	
	public void vaciarDatos(){
		datos.removeAllElements();
		this.fireTableDataChanged();
	}
	
	public Vector<Track> getDatos()
	{
		return datos;
	}
	
	public void swapRow(int origen, int destino){
		datos.elementAt(origen).setOrden(destino);
		datos.elementAt(destino).setOrden(origen);
		sortLista();
		
	}
	
	public Vector<Track> moveUp(int origen){
		if(origen > 0){
			swapRow(origen, origen-1);
			this.fireTableDataChanged();
			return datos;
		}
		
		return null;
	}
	
	public Vector<Track> moveDown(int origen){
		if(origen < getRowCount()-1){
			swapRow(origen, origen+1);
			this.fireTableDataChanged();
			return datos;
		}
		
		return null;
	}
	
	public Vector<Track> moveTop(int origen){
		if(origen > 0){
			for(int i = origen; i > 0; i--){
				swapRow(i, i-1);
			}
			this.fireTableDataChanged();
			return datos;
		}
		return null;
	}
	
	public Vector<Track> moveBottom(int origen){
		if(origen < getRowCount()-1){
			for(int i = origen; i < getRowCount() - 1; i++){
				swapRow(i, i+1);
			}
			this.fireTableDataChanged();
			return datos;
		}
		return null;
	}
	
	private void sortLista(){
		Collections.sort(datos, new Comparator<Track>(){
			public int compare(Track t1, Track t2){
				if(t1.getOrden() > t2.getOrden()) return 1;
				if(t1.getOrden() < t2.getOrden()) return -1;
				return 0;
			}
		});
	}		
}
