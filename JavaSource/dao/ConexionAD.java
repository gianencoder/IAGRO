package dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.codec.digest.DigestUtils;

import models.Rol;
import models.Usuario;

@Stateless
@LocalBean
public class ConexionAD {
	private String domain;
	private String ldapHost;
	private String searchBaseExperto;
	private String searchBaseAdmin;
	private String searchBaseComun;

	@Inject
	UsuarioBean dao;

	public ConexionAD() {
		this.domain = "IAGRO";
		this.ldapHost = "ldap://192.168.1.13:389";
		this.searchBaseExperto = "OU=Usuario.Experto,dc=iagro,dc=local";
		this.searchBaseAdmin = "OU=Usuario.Administrador,dc=iagro,dc=local";
		this.searchBaseComun = "OU=Usuario.Comun,dc=iagro,dc=local";
	}

	public ConexionAD(String domain, String host, String dn) {
		this.domain = domain;
		this.ldapHost = host;
		this.searchBaseExperto = dn;
	}

	public Map authenticateExperto(String user, String pass) {
		String returnedAtts[] = { "sn", "givenName", "mail" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + user + "))";

		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, pass);

		LdapContext ctxGC = null;

		try {
			ctxGC = new InitialLdapContext(env, null);

			NamingEnumeration answer = ctxGC.search(searchBaseExperto, searchFilter, searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				Map amap = null;
				if (attrs != null) {
					amap = new HashMap();
					NamingEnumeration ne = attrs.getAll();
					while (ne.hasMore()) {
						Attribute attr = (Attribute) ne.next();
						amap.put(attr.getID(), attr.get());
					}
					ne.close();
				}

				Rol r = new Rol();
				r.setId((long) 3);

				String clave = DigestUtils.md5Hex(pass);
				Usuario u = dao.obtenerUsername(user);
				try {

					if (u == null) {
						Usuario usuario = new Usuario();
						usuario.setNombre(attrs.get("givenName").get().toString());
						usuario.setApellido(attrs.get("sn").get().toString());
						usuario.setUsername(user);
						usuario.setRol(r);
						usuario.setEnabled(true);
						usuario.setMail(user + "@iagro.com");
						usuario.setContrasena(clave);

						dao.crear(usuario);
					} else {
						Usuario u1 = dao.obtenerUsername(user);
						u1.setNombre(attrs.get("givenName").get().toString());
						u1.setApellido(attrs.get("sn").get().toString());
						u1.setRol(r);
						u1.setUsername(user);
						u1.setContrasena(clave);
						u1.setMail(user + "@iagro.com");
						dao.editarUsuario(u1);
					}
					System.out.println("me conecte");
					return amap;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
			System.out.println("No me conecte");

		}
		return null;

	}

	public Map authenticateAdmin(String user, String pass) {
		String returnedAtts[] = { "sn", "givenName", "mail" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + user + "))";
		// Create the search controls
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);

		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, pass);

		LdapContext ctxGC = null;

		try {
			ctxGC = new InitialLdapContext(env, null);

			NamingEnumeration answer = ctxGC.search(searchBaseAdmin, searchFilter, searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				Map amap = null;
				if (attrs != null) {
					amap = new HashMap();
					NamingEnumeration ne = attrs.getAll();
					while (ne.hasMore()) {
						Attribute attr = (Attribute) ne.next();
						amap.put(attr.getID(), attr.get());
					}
					ne.close();
				}

				Rol r = new Rol();
				r.setId((long) 1);

				String clave = DigestUtils.md5Hex(pass);
				Usuario u = dao.obtenerUsername(user);
				try {

					if (u == null) {
						Usuario usuario = new Usuario();
						usuario.setNombre(attrs.get("givenName").get().toString());
						usuario.setApellido(attrs.get("sn").get().toString());
						usuario.setUsername(user);
						usuario.setRol(r);
						usuario.setEnabled(true);
						usuario.setMail(user + "@iagro.com");
						usuario.setContrasena(clave);

						dao.crear(usuario);
					} else {
						Usuario u1 = dao.obtenerUsername(user);

						u1.setNombre(attrs.get("givenName").get().toString());
						u1.setApellido(attrs.get("sn").get().toString());
						u1.setRol(r);
						u1.setUsername(user);
						u1.setContrasena(clave);
						u1.setMail(user + "@iagro.com");
						dao.editarUsuario(u1);
					}
					System.out.println("me conecte");
					return amap;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (NamingException ex) {
			ex.printStackTrace();
			System.out.println("No me conecte");
		}

		return null;
	}

	public Map authenticateComun(String user, String pass) {
		String returnedAtts[] = { "sn", "givenName", "mail" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + user + "))";
		// Create the search controls
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);

		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapHost);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, user + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, pass);

		LdapContext ctxGC = null;

		try {
			ctxGC = new InitialLdapContext(env, null);

			NamingEnumeration answer = ctxGC.search(searchBaseComun, searchFilter, searchCtls);
			while (answer.hasMoreElements()) {
				SearchResult sr = (SearchResult) answer.next();
				Attributes attrs = sr.getAttributes();
				Map amap = null;
				if (attrs != null) {
					amap = new HashMap();
					NamingEnumeration ne = attrs.getAll();
					while (ne.hasMore()) {
						Attribute attr = (Attribute) ne.next();
						amap.put(attr.getID(), attr.get());
					}
					ne.close();
				}

				Rol r = new Rol();
				r.setId((long) 2);

				String clave = DigestUtils.md5Hex(pass);
				Usuario u = dao.obtenerUsername(user);
				try {

					if (u == null) {
						Usuario usuario = new Usuario();
						usuario.setNombre(attrs.get("givenName").get().toString());
						usuario.setApellido(attrs.get("sn").get().toString());
						usuario.setUsername(user);
						usuario.setRol(r);
						usuario.setEnabled(true);
						usuario.setMail(user + "@iagro.com");
						usuario.setContrasena(clave);

						dao.crear(usuario);
					} else {
						Usuario u1 = dao.obtenerUsername(user);
						u1.setNombre(attrs.get("givenName").get().toString());
						u1.setApellido(attrs.get("sn").get().toString());
						u1.setRol(r);
						u1.setUsername(user);
						u1.setContrasena(clave);
						u1.setMail(user + "@iagro.com");
						dao.editarUsuario(u1);
					}
					System.out.println("me conecte");
					return amap;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		} catch (NamingException ex) {
			ex.printStackTrace();
			System.out.println("No me conecte");
		}

		return null;
	}
}