package fifa20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {
	private Connection conexion;

	public BDController() {
		super();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fifa", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en el constructor vacio del BDController");
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Jugador> dameJugadores() {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			String cadena = "Select * from jugadores;";
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(cadena);
			System.out.println(cadena);
			while (rs.next()) {
				jugadores.add(new Jugador(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getInt("cod_equipo"),
						rs.getString("pierna"), rs.getInt("altura"), rs.getString("pais")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameJugadores");
			e.printStackTrace();
		}
		return jugadores;
	}

	public ArrayList<Jugador> dameJugadoresEquipo(int cod_equipo) {
		// Creo el objeto tipo statement para poder hacer la consulta
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			String cadena = "Select * from jugadores where cod_equipo=?;";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_equipo);
			ResultSet rs = consultapre.executeQuery();
			System.out.println(cadena);
			while (rs.next()) {
				jugadores.add(new Jugador(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getInt("cod_equipo"),
						rs.getString("pierna"), rs.getInt("altura"), rs.getString("pais")));
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en listadoJugadoresEquipo");
			e.printStackTrace();
		}
		return jugadores;
	}

	public ArrayList<Liga> dameLigas() {
		ArrayList<Liga> ligas = new ArrayList<Liga>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			String cadena = "Select * from ligas;";
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(cadena);
			System.out.println(cadena);
			while (rs.next()) {
				ligas.add(new Liga(rs.getInt("cod_liga"), rs.getString("nombre"), rs.getString("pais")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameLigas");
			e.printStackTrace();
		}
		return ligas;
	}

	public ArrayList<Equipo> dameEquipos() {
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		// Creo el objeto tipo statement para poder hacer la consulta
		try {
			String cadena = "Select * from equipos;";
			Statement miStatement = this.conexion.createStatement();
			ResultSet rs = miStatement.executeQuery(cadena);
			System.out.println(cadena);
			while (rs.next()) {
				equipos.add(new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre"), rs.getInt("cod_liga")));
			}
			miStatement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameEquipos");
			e.printStackTrace();
		}
		return equipos;
	}

	public ArrayList<Equipo> dameEquiposLiga(int cod_liga) {
		// Creo el objeto tipo statement para poder hacer la consulta
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		try {
			String cadena = "Select * from equipos where cod_liga=?;";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_liga);
			ResultSet rs = consultapre.executeQuery();
			System.out.println(cadena);
			while (rs.next()) {
				equipos.add(new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre"), rs.getInt("cod_liga")));
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameEquiposLiga");
			e.printStackTrace();
		}
		return equipos;
	}

	public Jugador dameJugador(int cod_jugador) {
		// Creo el objeto tipo statement para poder hacer la consulta
		Jugador jugador = new Jugador();
		try {
			String cadena = "Select * from jugadores where cod_jugador=?;";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_jugador);
			ResultSet rs = consultapre.executeQuery();
			System.out.println(cadena);
			while (rs.next()) {
				jugador.setCod_jugador(rs.getInt("cod_jugador"));
				jugador.setNombre(rs.getString("nombre"));
				jugador.setCod_equipo(rs.getInt("cod_equipo"));
				jugador.setPierna(rs.getString("pierna"));
				jugador.setAltura(rs.getInt("altura"));
				jugador.setPais("pais");
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameJugador");
			e.printStackTrace();
		}
		return jugador;
	}

	public Liga dameLiga(int cod_liga) {
		// Creo el objeto tipo statement para poder hacer la consulta
		Liga liga = new Liga();
		try {
			String cadena = "Select * from ligas where cod_liga=?;";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_liga);
			ResultSet rs = consultapre.executeQuery();
			System.out.println(cadena);
			while (rs.next()) {
				liga.setCod_liga(rs.getInt("cod_liga"));
				liga.setNombre(rs.getString("nombre"));
				liga.setPais("pais");
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameLiga");
			e.printStackTrace();
		}
		return liga;
	}

	public Equipo dameEquipo(int cod_equipo) {
		// Creo el objeto tipo statement para poder hacer la consulta
		Equipo equipo = new Equipo();
		try {
			String cadena = "Select * from equipos where cod_equipo=?;";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_equipo);
			ResultSet rs = consultapre.executeQuery();
			System.out.println(cadena);
			while (rs.next()) {
				equipo.setCod_equipo(rs.getInt("cod_equipo"));
				equipo.setCod_liga(rs.getInt("cod_liga"));
				equipo.setNombre(rs.getString("nombre"));
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameEquipo");
			e.printStackTrace();
		}
		return equipo;
	}
	
	public Carta dameCarta(int cod_jug) {
		// Creo el objeto tipo statement para poder hacer la consulta
		Carta carta = new Carta();
		try {
			String cadena = "Select * from cartas where cod_jugador=? and nombre='SIMPLE';";
			PreparedStatement consultapre = conexion.prepareStatement(cadena);
			consultapre.setInt(1, cod_jug);
			System.out.println(cadena);
			ResultSet rs = consultapre.executeQuery();			
			while (rs.next()) {
				carta.setNombre(rs.getString("nombre"));
				carta.setCod_jugador(rs.getInt("cod_jugador"));
				carta.setRat(rs.getInt("rat"));
				carta.setPos(rs.getString("pos"));
				carta.setPrecio(rs.getInt("precio"));
				carta.setPac(rs.getInt("pac"));
				carta.setSho(rs.getInt("sho"));
				carta.setPas(rs.getInt("pas"));
				carta.setDri( rs.getInt("dri"));
				carta.setDef(rs.getInt("def"));
				carta.setPhy(rs.getInt("phy"));
				carta.setPierna_mala(rs.getInt("pierna_mala"));
				carta.setFiligranas(rs.getInt("filigranas"));
			}
			consultapre.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en dameCarta");
			e.printStackTrace();
		}
		return carta;
	}
}
