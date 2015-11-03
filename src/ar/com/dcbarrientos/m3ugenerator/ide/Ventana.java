package ar.com.dcbarrientos.m3ugenerator.ide;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.beaglebuddy.mp3.MP3;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

import ar.com.dcbarrientos.m3ugenerator.FileManager;
import ar.com.dcbarrientos.m3ugenerator.M3UGenerator;
import ar.com.dcbarrientos.m3ugenerator.Track;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;;

public class Ventana extends JFrame {
	private final String FILE_EXT = ".m3u";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblTemas;
	private JTextField textDestino;
	private JCheckBox chIncluirPath;
	private Vector<Track> lista;
	private String[] header =  {"Track", "T\u00EDtulo", "Album", "Artista", "Path"};
	private tblTemasModel tm = null;
	private JTextField textPath;
	private boolean extendido = true;
	private String pathOriginal;
	private boolean pathChanged;
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setTitle(M3UGenerator.TITLE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel contenido = new JPanel();
		contenido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), " Contenido ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(contenido, BorderLayout.CENTER);
		contenido.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contenido.add(scrollPane);
		
		tblTemas = new JTable();
		tblTemas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblTemas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			header
		));
		scrollPane.setViewportView(tblTemas);
		
		JPanel botonera = new JPanel();
		contenido.add(botonera, BorderLayout.EAST);
		botonera.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAgregarCarpeta = new JButton();
		btnAgregarCarpeta.setContentAreaFilled(false);
		btnAgregarCarpeta.setBorder(null);
		btnAgregarCarpeta.setBorderPainted(false);
		btnAgregarCarpeta.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Add_Folder.png")));
		
		btnAgregarCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectFolder();
			}
		});
		botonera.add(btnAgregarCarpeta);
		
		JButton btnAgregarArchivo = new JButton();
		btnAgregarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregarArchivo.setContentAreaFilled(false);
		btnAgregarArchivo.setBorderPainted(false);
		btnAgregarArchivo.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Add_File.png")));
		botonera.add(btnAgregarArchivo);
		
		JButton btnSubirTodo = new JButton();
		btnSubirTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveTop();
			}
		});
		btnSubirTodo.setContentAreaFilled(false);
		btnSubirTodo.setBorderPainted(false);
		btnSubirTodo.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Top.png")));
		botonera.add(btnSubirTodo);
		
		JButton btnSubirUno = new JButton();
		btnSubirUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveUp();
			}
		});
		btnSubirUno.setContentAreaFilled(false);
		btnSubirUno.setBorderPainted(false);
		btnSubirUno.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Up.png")));
		botonera.add(btnSubirUno);
		
		JButton btnBajarUno = new JButton();
		btnBajarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveDown();
			}
		});
		btnBajarUno.setContentAreaFilled(false);
		btnBajarUno.setBorderPainted(false);
		btnBajarUno.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Down.png")));
		botonera.add(btnBajarUno);
		
		JButton btnBajarTodo = new JButton();
		btnBajarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveBottom();
			}
		});
		btnBajarTodo.setBorderPainted(false);
		btnBajarTodo.setContentAreaFilled(false);
		btnBajarTodo.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Bottom.png")));
		botonera.add(btnBajarTodo);
		
		JButton btnBorrarUno = new JButton();
		btnBorrarUno.setContentAreaFilled(false);
		btnBorrarUno.setBorderPainted(false);
		btnBorrarUno.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Delete_Row.png")));
		btnBorrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarElemento();
			}
		});
		botonera.add(btnBorrarUno);
		
		JButton btnBorrarTodo = new JButton();
		btnBorrarTodo.setContentAreaFilled(false);
		btnBorrarTodo.setBorderPainted(false);
		btnBorrarTodo.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Delete.png")));
		btnBorrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarLista();
			}
		});
		botonera.add(btnBorrarTodo);
		
		JPanel toolBar = new JPanel();
		toolBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolBar.setBackground(Color.WHITE);
		FlowLayout fl_toolBar = (FlowLayout) toolBar.getLayout();
		fl_toolBar.setAlignment(FlowLayout.LEFT);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBorder(null);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Click en Nuevo");
			}
		});
		btnNuevo.setContentAreaFilled(false);
		btnNuevo.setBorderPainted(false);
		btnNuevo.setIcon(new ImageIcon(Ventana.class.getResource("/resources/New.png")));
		toolBar.add(btnNuevo);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.setBorderPainted(false);
		btnAbrir.setContentAreaFilled(false);
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Click en abrir");
			}
		});
		btnAbrir.setIcon(new ImageIcon(Ventana.class.getResource("/resources/open_file.png")));
		toolBar.add(btnAbrir);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Settings.png")));
		btnSettings.setContentAreaFilled(false);
		btnSettings.setBorderPainted(false);
		toolBar.add(btnSettings);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBorderPainted(false);
		btnHelp.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Help.png")));
		btnHelp.setDefaultCapable(false);
		btnHelp.setContentAreaFilled(false);
		toolBar.add(btnHelp);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbout.setContentAreaFilled(false);
		btnAbout.setBorderPainted(false);
		btnAbout.setIcon(new ImageIcon(Ventana.class.getResource("/resources/About.png")));
		toolBar.add(btnAbout);
		
		JPanel createPlaylist = new JPanel();
		contentPane.add(createPlaylist, BorderLayout.SOUTH);
		createPlaylist.setBorder(new TitledBorder(null, " Creaci\u00F3n de la lista ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblPath = new JLabel("Path:");
		
		textPath = new JTextField();
		textPath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				pathChanged = true;
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					changePath(textPath.getText());
					pathChanged = false;
				}
			}
		});
		textPath.setColumns(45);
		
		chIncluirPath = new JCheckBox("Incluir path");
		
		textDestino = new JTextField();
		textDestino.setColumns(45);
		
		JLabel lblDestino = new JLabel("Destino:");
		
		JButton button = new JButton("...");
		
		JButton btnGuardarLista = new JButton("Guardar lista");
		btnGuardarLista.setContentAreaFilled(false);
		btnGuardarLista.setBorderPainted(false);
		btnGuardarLista.setIcon(new ImageIcon(Ventana.class.getResource("/resources/Save.png")));
		
		btnGuardarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		GroupLayout gl_createPlaylist = new GroupLayout(createPlaylist);
		gl_createPlaylist.setHorizontalGroup(
			gl_createPlaylist.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_createPlaylist.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_createPlaylist.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_createPlaylist.createSequentialGroup()
							.addComponent(lblPath)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chIncluirPath))
						.addGroup(gl_createPlaylist.createSequentialGroup()
							.addComponent(lblDestino)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardarLista)))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_createPlaylist.setVerticalGroup(
			gl_createPlaylist.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_createPlaylist.createSequentialGroup()
					.addGroup(gl_createPlaylist.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPath)
						.addComponent(textPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chIncluirPath))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_createPlaylist.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestino)
						.addComponent(textDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(btnGuardarLista)))
		);
		createPlaylist.setLayout(gl_createPlaylist);
		pathChanged = false;
	}
	
	private void loadTable(){
		tm = new tblTemasModel();
		tm.setHeader(header);
		tm.setData(lista);

		tblTemas.setModel(tm);
		
	}
	
	private void selectFolder(){
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int resultado = fc.showOpenDialog(this);
		if(resultado == JFileChooser.APPROVE_OPTION){
			pathOriginal = fc.getSelectedFile().getAbsolutePath();
			loadFolder(pathOriginal);
			loadTable();
			textPath.setText(pathOriginal);
			pathChanged = false;
		}
	}
	
	private void loadFolder(String path){
		lista = new Vector<Track>();
		
		File dir = new File(path);
		textDestino.setText(path + System.getProperty("file.separator") + getNombreArchivo(path));
		
		int contador = 1;
		
		if(dir.exists()){
			File[] archivos = dir.listFiles();
			
			for(int i = 0; i < archivos.length; i++){
				try {
					String ext = archivos[i].getPath().substring(archivos[i].getPath().lastIndexOf('.')+1);
					if(ext.equals("mp3")){
						MP3 mp3 = new MP3(archivos[i].getPath());
						if(mp3.hasErrors()){
							int resu = JOptionPane.showConfirmDialog(null, "El archivo " + archivos[i].getName() + " tiene un error.\n¿Desea corregirlo?", "Error", JOptionPane.YES_NO_OPTION);
							if(resu == JOptionPane.YES_OPTION){
								mp3.save();
							}
						}
						if(!mp3.hasErrors()){
							Track t = new Track(mp3, extendido);
							t.setOrden(contador);
							t.setPath(archivos[i].getParent());
							t.setFileName(archivos[i].getName());
							
							lista.addElement(t);
							contador++;
						}else{
							extendido = false;
							System.out.println(mp3.getErrors());
						}
					}
				} catch (IOException e) {
					System.out.println(archivos[i].getPath());
					e.printStackTrace();
				}				
			}
		}		
	}
	
	private void changePath(String path){
		if(path.charAt(path.length()-1) != File.separatorChar){
			path += File.separator; 
		}
		
		for(int fila = 0; fila < tm.getRowCount(); fila++){
			lista.elementAt(fila).setPath(path);			
		}
		tm.setData(lista);
		
	}
	
	private String getNombreArchivo(String path){				
		return path.substring(path.lastIndexOf('\\')+1, path.length()) + FILE_EXT;
	}
	
	private void eliminarElemento(){
		tm.removeRow(tblTemas.getSelectedRow());		
	}
	
	private void vaciarLista(){
		tm.vaciarDatos();
	}
	
	private void saveFile(){
		if(pathChanged && chIncluirPath.isSelected()){
			int r = JOptionPane.showConfirmDialog(	null, 
					"El path ha sido modificado. ¿Desea actualizar la lista de temas?", 
					M3UGenerator.TITLE, 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE);
			if(r == JOptionPane.YES_OPTION){
				changePath(textPath.getText());
				pathChanged = false;
			}				
		}
		
		if(textDestino.getText().length() > 0){
			FileManager fm = new FileManager(textDestino.getText(), tm.getDatos(), chIncluirPath.isSelected());
			fm.save();
		}
	}
	
	private void moveUp(){
		if(tblTemas.getSelectedRow() > 0){
			int seleccion = tblTemas.getSelectedRow(); 
			lista = new Vector<Track>(tm.moveUp(tblTemas.getSelectedRow()));
			tblTemas.getSelectionModel().setSelectionInterval(seleccion-1, seleccion-1);
		}
	}
	
	private void moveDown(){
		if(tblTemas.getSelectedRow() < tm.getRowCount()-1){
			int seleccion = tblTemas.getSelectedRow(); 
			lista = new Vector<Track>(tm.moveDown(tblTemas.getSelectedRow()));
			tblTemas.getSelectionModel().setSelectionInterval(seleccion+1, seleccion+1);
		}		
	}
	
	private void moveBottom(){
		if(tblTemas.getSelectedRow() < tm.getRowCount()-1){
			int seleccion = tblTemas.getSelectedRow();
			lista = new Vector<Track>(tm.moveBottom(seleccion));
			tblTemas.getSelectionModel().setSelectionInterval(tm.getRowCount()-1, tm.getRowCount()-1);
		}
	}
	
	private void moveTop(){
		if(tblTemas.getSelectedRow() > 0){
			int seleccion = tblTemas.getSelectedRow();
			lista = new Vector<Track>(tm.moveTop(seleccion));
			tblTemas.getSelectionModel().setSelectionInterval(0, 0);
		}
	}
}
