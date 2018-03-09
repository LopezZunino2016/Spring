package es.altair.inventario.controller;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.altair.inventario.bean.Articulo;
import es.altair.inventario.bean.Usuario;
import es.altair.inventario.dao.ArticuloDAO;
import es.altair.inventario.dao.UsuarioDAO;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO; 
	
	@Autowired
	private ArticuloDAO articuloDAO; 
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView inicio(@RequestParam(value="fallo",required=false,defaultValue="") String fallo,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje,Model model) {
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("fallo",fallo);

		return new ModelAndView("home","usuario",new Usuario());
	}
	
	@RequestMapping(value="/Registrar", method=RequestMethod.POST)
	public String registrarUsuario(@RequestParam("idUsuario") String idUsuario, @ModelAttribute Usuario Usu) {
		
		int filas = 0; 
		String mensaje = ""; 
		
		if(!usuarioDAO.comprobarAlias(Usu)) {
			filas = usuarioDAO.registrar(Usu);
			if(filas == 1) {
				mensaje="Usuario Registrado"; 
				return "redirect:/?mensaje="+ mensaje; 
			}else {
				mensaje="No se ha registrado al usuario"; 
				return "redirect:/?mensaje="+ mensaje; 
			}
		}else {
			mensaje="Usuario ya se encuentra registrado"; 
			return "redirect:/?mensaje="+ mensaje; 
		}
	}
	
	@RequestMapping(value="/Principal", method=RequestMethod.GET)
	public ModelAndView principal(@ModelAttribute Usuario usu, Model model,HttpSession sesion) {
		model.addAttribute("listaArticulo",articuloDAO.listar());	
		model.addAttribute("listaUsuario", usuarioDAO.listar()); 
		model.addAttribute("idUsuario", usu.getNombre()); 
		return new ModelAndView("Principal");

	}
	@RequestMapping(value="/PrincipalNormal", method=RequestMethod.GET)
	public ModelAndView principalUsuario(Model model,HttpSession sesion) {
		model.addAttribute("listaArticulo",articuloDAO.listar());	

		return new ModelAndView("PrincipalNormal");

	}
	@RequestMapping(value="/inicioSesion",method=RequestMethod.POST)
	public String iniciarSesion(@ModelAttribute Usuario usu,HttpSession sesion) {
		usu = usuarioDAO.comprobarUsuario(usu.getAlias(), usu.getPassword());
		System.out.println(usu);
		if(usu != null) {
			sesion.setAttribute("usuarioLogeado", usu);
			sesion.setAttribute("tipoUsuario", usu.getTipo());
			sesion.setAttribute("idUsuario", usu.getIdUsuario());

			if(usu.getTipo() == 1) 
				return "redirect:/Principal";
			else 				
				return "redirect:/PrincipalNormal";
			
		}else {
			String mensaje="Usuario y/o Password Incorrecto"; 

			return "redirect:/?mensajeL=" + mensaje;
		}
		

	}
	
	@RequestMapping(value="/addArticulo", method=RequestMethod.GET)
	public ModelAndView addArticulo(@RequestParam(value="fallo",required=false,defaultValue="") String fallo,@RequestParam(value="mensaje",required=false,defaultValue="") String mensaje,Model model) {
		
		return new ModelAndView("anadirArticulo","articulo",new Articulo());

	}
	
	@RequestMapping(value="/AnadirA", method=RequestMethod.POST)
	public String anadirArticulo(@ModelAttribute Usuario usu,HttpSession sesion, @ModelAttribute Articulo art) {
		int tipo = (Integer) sesion.getAttribute("tipoUsuario"); 
		
		int filas = 0; 
		String mensaje = "";
		if(!articuloDAO.comprobarNombre(art.getNombre())) {
			filas = articuloDAO.anadirArticulo(art);
			if(filas == 1) {
				if(tipo == 1) {
					mensaje="Articulo Registrado"; 
					return "redirect:/Principal?mensaje="+ "Articulo Registrado";
				}else {
					mensaje="Articulo Registrado"; 
					return "redirect:/PrincipalNormal?mensaje="+ "Articulo Registrado";
				}
				
			}else {
				mensaje="No se ha guardado el articulo"; 
				return "redirect:/addArticulo?mensaje="+ mensaje; 
			}
		}else {
			mensaje="el articulo ya se encuentra registradro"; 
			return "redirect:/addArticulo?mensaje="+ mensaje; 
		}
		
		
	}
	
	@RequestMapping(value ="/BorrarArticulo", method = RequestMethod.GET)
	public String eliminaArticulo(@ModelAttribute Usuario usu,HttpSession sesion,@RequestParam("idArticulo") String idArticulo,HttpServletRequest request) {
		int tipo = (Integer) sesion.getAttribute("tipoUsuario"); 

		int idArt = Integer.parseInt(request.getParameter("idArticulo"));
		articuloDAO.borrar(idArt);
		if(tipo == 1)
			return "redirect:/Principal?mensaje="+ "Articulo borrado";
		else
			return "redirect:/PrincipalNormal?mensaje="+ "Articulo borrado";
	}
	
	@RequestMapping(value ="/BorrarUsuario", method = RequestMethod.GET)
	public String eliminarUsuario(@ModelAttribute Usuario usu,HttpSession sesion,@RequestParam("idUsuario") String idArticulo,HttpServletRequest request) {
		int idUsuBorrar = Integer.parseInt(request.getParameter("idUsuario")); 
		int idUsu = (Integer) sesion.getAttribute("idUsuario"); 
		System.out.println("USUARIO MIO: " + idUsu + " USUARIO BORRAR: "+ idUsuBorrar );
		if(idUsu == idUsuBorrar ) {
			return "redirect:/Principal?mensaje="+ "No puedes borrar tu usuario";

		}else {
			System.out.println("Entramos en borrar");
			usuarioDAO.borrar(idUsuBorrar);
			return "redirect:/Principal?mensaje="+ "Usuario Borrado";

		}
			
			
		
	}
	@RequestMapping(value="/EditarArticulo",method=RequestMethod.GET)
	public ModelAndView modelEditarVid(@RequestParam("idArticulo") String idArticulo,HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("idArticulo"));		
		
		Articulo art = articuloDAO.obtenerArticuloPorId(id);
		System.out.println(art);
		return new ModelAndView("EditarArticulo","articulo", art);
	}
	
	@RequestMapping(value="/editarArticulo", method = RequestMethod.POST)
	public String editarArticulo (@ModelAttribute Articulo art){
		System.out.println("Antes del dao");
		articuloDAO.ActualizarArticulo(art);
		return "redirect:/Principal?mensaje="+ "Articulo editado";
	}
	
	@RequestMapping(value="/cerrarSesion",method=RequestMethod.GET)
	public String cerrarSesion(HttpSession sesion) {
		sesion.setAttribute("usuLogeado", null);
		return "redirect:/"; 
	}
}
